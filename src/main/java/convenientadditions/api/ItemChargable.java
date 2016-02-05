package convenientadditions.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public abstract class ItemChargable extends Item implements IChargable {
	
	private int capacity;
	private boolean showDurBar;
	
	protected ItemChargable(int capacity,boolean showDurabilityBar,boolean showTooltips){
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
	public int getChargeCapacity(ItemStack item) {
		return capacity;
	}

	@Override
	public boolean isChargable(ItemStack item) {
		return true;
	}

	@Override
	public int chargeItem(ItemStack item, int amount) {
		int charge=getCharge(item);
		int cap=getChargeCapacity(item);
		if(charge+amount<=cap){
			if(charge+amount>=0){
				item.getTagCompound().setInteger("CHARGE", charge+amount);
				return 0;
			}else{
				item.getTagCompound().setInteger("CHARGE", 0);
				return charge+amount;
			}
		}else{
			item.getTagCompound().setInteger("CHARGE", cap);
			return charge+amount-cap;
		}
	}

	@Override
    public boolean showDurabilityBar(ItemStack item)
    {
        return showDurBar;
    }
    
	@Override
    public double getDurabilityForDisplay(ItemStack item)
    {
        return 1D-((double)getCharge(item)/(double)getChargeCapacity(item));
    }
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:charge").replace("%c", ""+getCharge(item)).replace("%C", ""+getChargeCapacity(item)).replace("%p", ""+(int)((double)getCharge(item)/(double)getChargeCapacity(item)*100)));
		super.addInformation(item, player, list, par4);
	}
}
