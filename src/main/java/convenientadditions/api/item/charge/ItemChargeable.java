package convenientadditions.api.item.charge;

import java.util.List;
import java.util.Random;

import convenientadditions.api.util.EnchantmentUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ItemChargeable extends Item implements IChargeable {
	
	private int capacity;
	private boolean showDurBar;
	
	public ItemChargeable(int capacity,boolean showDurabilityBar,boolean showTooltips){
		super();
		this.capacity=capacity;
		this.showDurBar=showDurabilityBar;
	}
	
	@Override
	public int getCharge(ItemStack item) {
		if(item.hasTagCompound()){
			NBTTagCompound nbt=item.getTagCompound();
			if(nbt.hasKey("CHARGE")){
				return nbt.getInteger("CHARGE");
			}else{
				nbt.setInteger("CHARGE", 0);
				return 0;
			}
		}else{
			NBTTagCompound nbt=new NBTTagCompound();
			nbt.setInteger("CHARGE", 0);
			item.setTagCompound(nbt);
			return 0;
		}
	}
	
	@Override
	public void setItemCharge(ItemStack item, int amount) {
		if(item.hasTagCompound()){
			NBTTagCompound nbt=item.getTagCompound();
			nbt.setInteger("CHARGE", amount);
		}else{
			NBTTagCompound nbt=new NBTTagCompound();
			nbt.setInteger("CHARGE", amount);
			item.setTagCompound(nbt);
		}
	}

	@Override
	public int getChargeCapacity(ItemStack item) {
		int lvl=EnchantmentHelper.getEnchantmentLevel(EnchantmentUtil.capacity, item);
		return (int)(capacity*EnchantmentUtil.enchantmentScaleFactor[lvl]);
	}

	@Override
	public boolean isChargeable(ItemStack item) {
		return true;
	}

	@Override
	public int chargeItem(ItemStack item, int amount) {
		int ret=ItemChargeable.overflow(getCharge(item), amount, getChargeCapacity(item));
		setItemCharge(item, getCharge(item)+amount-ret);
		return ret;
	}
	
	@Override
	public int dischargeItem(ItemStack item, int amount) {
		int ret=ItemChargeable.drain(getCharge(item), amount);
		setItemCharge(item, getCharge(item)-ret);
		return ret;
	}
	
	public int consumeCharge(ItemStack item, int amount){
		int lvl=EnchantmentHelper.getEnchantmentLevel(EnchantmentUtil.chargeEfficiency, item);
		double propFactor=(EnchantmentUtil.enchantmentScaleFactor[lvl]-1)+1;
		double prop=new Random().nextDouble()*propFactor;
		if(prop<=1)
			return dischargeItem(item, amount);
		return amount;
	}

	@Override
    public boolean showDurabilityBar(ItemStack item)
    {
        return showDurBar&&getDurabilityForDisplay(item)!=0D;
    }
    
	@Override
    public double getDurabilityForDisplay(ItemStack item)
    {
        return 1D-((double)getCharge(item)/(double)getChargeCapacity(item));
    }
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean par4)
	{
		list.add(ItemChargeable.localize("tooltip.convenientadditions:charge", "%c", ""+getCharge(item),"%C", ""+getChargeCapacity(item),"%p", ""+(int)((double)getCharge(item)/(double)getChargeCapacity(item)*100)));
		super.addInformation(item, player, list, par4);
	}
	
	@Override
	public boolean canApplyCapacity(ItemStack item){
		return true;
	}
	
	@Override
	public boolean canApplyChargeEfficiency(ItemStack item){
		return true;
	}
	
	@Override
	public boolean isItemTool(ItemStack item){
		return true;
	}
	
	@Override
	public int getItemEnchantability(){
		return 0;
	}
	
	@Override
	public int getItemEnchantability(ItemStack item){
		return 1;
	}
	
	public static String localize(String in,String... replace){
		String tmp=I18n.format(in, new Object[0]);
		if(tmp.startsWith("Format error: "))
			tmp=tmp.replaceFirst("Format error: ", "");
		String match = null;
		for(String s:replace){
			if(match==null){
				match=s;
			}else{
				tmp=tmp.replace(match, s);
				match=null;
			}
		}
		return tmp;
	}
	
	public static int drain(int base,int decrement){
		return (((base-decrement)<0)?(decrement+(base-decrement)):decrement);
	}
	
	public static int overflow(int base,int increment,int capacity){
		return ((base+increment)>capacity)?(base+increment-capacity ):(((base+increment)<0)?(base+increment):0);
	}
}
