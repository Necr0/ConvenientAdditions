package convenientadditions.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSunstone extends Item {
	public static ItemStack FULLY_CHARGED;
    @SideOnly(Side.CLIENT)
    protected IIcon itemIconInactive;
    
	public ItemSunstone(){
		super();
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getMaxItemCharge());
	}

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dmg)
    {
		return dmg==0?itemIconInactive:itemIcon;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName+"_active");
        this.itemIconInactive = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName+"_inactive");
    }
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		int dmg=itemStack.getItemDamage();
    	if(!world.isRemote)
    		if(dmg==0&&getItemCharge(itemStack)!=0){
    			itemStack.setItemDamage(1);
    		}else
    			itemStack.setItemDamage(0);
    	return itemStack;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstone"));
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneCharge").replace("%c", ""+getItemCharge(stack)).replace("%C", ""+getMaxItemCharge()).replace("%p", ""+(int)((double)getItemCharge(stack)/(double)getMaxItemCharge()*100)));
		if(isActive(stack))
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneActive"));
		else if(getItemCharge(stack)==0)
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneDrained"));
		else
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneInactive"));
	}
	
	public int getItemCharge(ItemStack stack){
		if(stack.hasTagCompound()){
			NBTTagCompound nbt=stack.getTagCompound();
			if(nbt.hasKey("CHARGE")){
				return nbt.getInteger("CHARGE");
			}else{
				nbt.setInteger("CHARGE", 0);
			}
		}else{
			NBTTagCompound nbt=new NBTTagCompound();
			nbt.setInteger("CHARGE", 0);
			stack.setTagCompound(nbt);
		}
		return 0;
	}
	
	public int getMaxItemCharge(){
		return 60000;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
	
	public boolean isActive(ItemStack stack){
		return (getItemCharge(stack))>0&&stack.getItemDamage()==1;
	}
	
	public void chargeItem(ItemStack stack,int amount){
		if(stack.hasTagCompound()){
			NBTTagCompound nbt=stack.getTagCompound();
			if(nbt.hasKey("CHARGE")){
				int val=nbt.getInteger("CHARGE")+amount;
				nbt.setInteger("CHARGE", val>0?(val>getMaxItemCharge()?getMaxItemCharge():val):0);
			}else{
				nbt.setInteger("CHARGE", amount>0?(amount>getMaxItemCharge()?getMaxItemCharge():amount):0);
			}
		}else{
			NBTTagCompound nbt=new NBTTagCompound();
			nbt.setInteger("CHARGE", amount>0?(amount>getMaxItemCharge()?getMaxItemCharge():amount):0);
			stack.setTagCompound(nbt);
		}
		if(stack.getTagCompound().getInteger("CHARGE")==0)
			stack.setItemDamage(0);
	}
	
	@Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
    
	@Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return 1D-((double)(getItemCharge(stack))/(double)(getMaxItemCharge()));
    }
}
