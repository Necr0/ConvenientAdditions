package convenientadditions.item.baubles;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemSunlightChargeable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSaturationRing extends ItemSunlightChargeable implements IBauble {
	public static ItemStack FULLY_CHARGED;
    
	public ItemSaturationRing(){
		super(12000,true,true,2);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.saturationRingItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.saturationRingItemName)
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
		if(getCharge(itemstack)>=2){
			WorldServer world=(WorldServer)player.worldObj;
			EntityPlayer p=(EntityPlayer)player;
			Random random = new Random();
			if(random.nextInt(60)==0){
				if(p.getFoodStats().getSaturationLevel()<1.1f){
					p.getFoodStats().setFoodSaturationLevel(p.getFoodStats().getSaturationLevel()+.12f);
				}
			}
    		consumeCharge(itemstack, 9);
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
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:saturationRing"));
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
		return slot>=-4&&slot<=9;
	}
}
