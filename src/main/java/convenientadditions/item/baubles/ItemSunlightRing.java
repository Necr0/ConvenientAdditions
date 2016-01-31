package convenientadditions.item.baubles;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSunlightRing extends Item implements IBauble {
	public static ItemStack FULLY_CHARGED;
    
	public ItemSunlightRing(){
		super();
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightRingItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightRingItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getMaxItemCharge());
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		World world=player.worldObj;
		if(world.isRemote)
			return;
		
		Random random = new Random();
		ModItems.itemSunlightRing.chargeItem(itemstack, -1);;
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				for(int z=0;z<9;z++){
					int 	x1=x-4+(int)player.posX,
							y1=y-4+(int)player.posY,
							z1=z-4+(int)player.posZ;
					Block b=world.getBlock(x1, y1, z1);
					if(b.isAir(world,x1,y1,z1)&&b!=ModBlocks.tempLightBlock){
						world.setBlock(x1, y1, z1, ModBlocks.tempLightBlock, 0, 3);
						world.scheduleBlockUpdate(x1, y1, z1, ModBlocks.tempLightBlock, 20+random.nextInt(20));
					}
        		}
    		}
		}
		if(!world.provider.hasNoSky&&!world.isRaining()&&world.isDaytime()&&world.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
			ModItems.itemSunlightRing.chargeItem(itemstack, 21);
		}
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstone"));
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneCharge").replace("%c", ""+getItemCharge(stack)).replace("%C", ""+getMaxItemCharge()).replace("%p", ""+(int)((double)getItemCharge(stack)/(double)getMaxItemCharge()*100)));
		list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneDrained"));
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
	}

}
