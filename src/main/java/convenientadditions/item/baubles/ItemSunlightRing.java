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
import net.minecraft.world.WorldServer;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.api.ItemChargable;
import convenientadditions.api.ItemSunlightChargable;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSunlightRing extends ItemSunlightChargable implements IBauble {
	public static ItemStack FULLY_CHARGED;
    
	public ItemSunlightRing(){
		super(60000,true,true,21);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightRingItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightRingItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if(player.worldObj.isRemote)
			return;
		
		WorldServer world=(WorldServer)player.worldObj;
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
		super.addInformation(stack,player,list,par4);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }

	@Override
	public boolean isSunlightChargable(ItemStack item,int slot) {
		return slot>=-4&&slot<=9;
	}
}
