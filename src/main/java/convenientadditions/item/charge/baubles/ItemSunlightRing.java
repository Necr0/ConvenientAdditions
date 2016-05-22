package convenientadditions.item.charge.baubles;

import java.util.List;
import java.util.Random;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.item.charge.ItemSunlightChargeableBehaviour;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSunlightRing extends ItemSunlightChargeableBehaviour implements IBauble,IModelResourceLocationProvider {
	public static ItemStack FULLY_CHARGED;
    
	public ItemSunlightRing(){
		super(60000,true,true,21);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightRingItemName)
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
		if(ModItems.itemSunlightRing.getCharge(itemstack)>0){
			WorldServer world=(WorldServer)player.worldObj;
			Random random = new Random();
    		consumeCharge(itemstack, 1);
    		for(int x=0;x<9;x++){
    			for(int y=0;y<9;y++){
    				for(int z=0;z<9;z++){
    					BlockPos pos=new BlockPos(x-4+(int)player.posX,y-4+(int)player.posY,z-4+(int)player.posZ);
    					IBlockState state=world.getBlockState(pos);
    					Block b=state.getBlock();
    					if(b.isAir(state,world,pos)&&b!=ModBlocks.tempLightBlock){
    						world.setBlockState(pos, ModBlocks.tempLightBlock.getStateFromMeta(0), 3);
    						world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 20+random.nextInt(20), 0);
    					}
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
		list.add(I18n.translateToLocal("tooltip.convenientadditions:sunstone"));
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
	public boolean isSunlightChargeable(ItemStack item,int slot) {
		return slot>=-4&&slot<=8||slot==255;
	}
}
