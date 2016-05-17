package convenientadditions.api.item.charge;

import java.util.List;

import convenientadditions.api.util.EnchantmentUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class ItemSunlightChargeable extends ItemChargeable implements ISunlightChargeable {

	protected int chargeRate;
	
	public ItemSunlightChargeable(int capacity, boolean showDurabilityBar,boolean showTooltips, int sunlightChargeRate) {
		super(capacity, showDurabilityBar, showTooltips);
		chargeRate=sunlightChargeRate;
	}
	
	@Override
	public int getSunlightChargeRate(ItemStack item,int slot) {
		int lvl=EnchantmentHelper.getEnchantmentLevel(EnchantmentUtil.drain, item);
		double amp=(EnchantmentUtil.enchantmentScaleFactor[lvl]-1)+1;
		return (int)(chargeRate*amp);
	}

	@Override
	public boolean isSunlightChargeable(ItemStack item,int slot) {
		return true;
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(item, player, list, par4);
		list.add(TextFormatting.DARK_GRAY+I18n.translateToLocal("tooltip.convenientadditions:sunstoneDrained"));
	}
	
	@Override
	public boolean canApplyDrain(ItemStack item){
		return true;
	}
}
