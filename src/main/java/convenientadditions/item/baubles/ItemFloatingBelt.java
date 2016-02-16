package convenientadditions.item.baubles;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemSunlightChargeable;
import convenientadditions.item.enchantments.EnchantmentUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFloatingBelt extends ItemSunlightChargeable implements IBauble {
	public static ItemStack FULLY_CHARGED;
    
	public ItemFloatingBelt(){
		super(152000,true,true,16);
		this.setHasSubtypes(true)
			.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.floatingBeltItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.floatingBeltItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		int charge=getCharge(itemstack);
		EntityPlayer eplayer=(EntityPlayer)player;
		if(charge>=120){
			if(eplayer.capabilities.isFlying){
				if(!player.worldObj.isRemote)
					consumeCharge(itemstack, 160);
			}else if(charge<800){
				eplayer.capabilities.allowFlying=false;
				eplayer.capabilities.isFlying=false;
			}else{
				eplayer.capabilities.allowFlying=true;
			}
		}else{
			eplayer.capabilities.allowFlying=false;
			eplayer.capabilities.isFlying=false;
		}
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		boolean flag=false;
		EntityPlayer eplayer=(EntityPlayer)player;
		IInventory baublesInv=BaublesApi.getBaubles(eplayer);
		for(int i=0;i<4;i++){
			ItemStack stack=baublesInv.getStackInSlot(i);
			if(stack!=null&&stack.getItem()==this){
				flag=true;
				break;
			}
		}
		if(!flag){
			eplayer.capabilities.allowFlying=false;
			eplayer.capabilities.isFlying=false;
		}
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		boolean flag=false;
		EntityPlayer eplayer=(EntityPlayer)player;
		IInventory baublesInv=BaublesApi.getBaubles(eplayer);
		for(int i=0;i<4;i++){
			ItemStack stack=baublesInv.getStackInSlot(i);
			if(stack!=null&&stack.getItem()==this){
				flag=true;
				break;
			}
		}
		if(!flag){
			eplayer.capabilities.allowFlying=false;
			eplayer.capabilities.isFlying=false;
		}
	}

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
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:floatingBelt"));
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
