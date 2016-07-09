package convenientadditions.item.charge.baubles;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.item.charge.ItemSunlightChargeableBehaviour;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBreathAmulet extends ItemSunlightChargeableBehaviour implements IBauble, IModelResourceLocationProvider {
	public static ItemStack FULLY_CHARGED;
    
	public ItemBreathAmulet(){
		super(10000,true,true,5);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.breathAmuletItemName)
			.setCreativeTab(ConvenientAdditions.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if(player.worldObj.isRemote)
			return;
		if(getCharge(itemstack)>=2){
			EntityPlayer p=(EntityPlayer)player;
			int air=p.getAir();
			if(air<20){
				p.setAir(20);
	    		consumeCharge(itemstack, 2*(20-air));
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		list.add(Helper.localize("tooltip.convenientadditions:breathAmulet"));
		super.addInformation(stack,player,list,par4);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List<ItemStack> l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }

	@Override
	public boolean isSunlightChargeable(ItemStack item,int slot) {
		return slot>=-4&&slot<=8||slot==255||slot==-255;
	}
}
