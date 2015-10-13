package convenientadditions.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSunstone extends Item {
    @SideOnly(Side.CLIENT)
    protected IIcon itemIconInactive;
    
	public ItemSunstone(){
		super();
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunstoneItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setMaxStackSize(1);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
		return damage==0?itemIconInactive:itemIcon;
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
    	if(!world.isRemote)
    		if(itemStack.getItemDamage()==0)
    			itemStack.setItemDamage(1);
    		else
    			itemStack.setItemDamage(0);
    	return itemStack;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstone"));
		if(stack.getItemDamage()==0)
			list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneInactive"));
		else
			list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneActive"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(new ItemStack(i, 1, 1));
    }
}
