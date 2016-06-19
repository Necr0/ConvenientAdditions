package convenientadditions.api.item.charge;

import java.util.List;

import conveniencecore.entity.behaviour.BehaviourRegistry;
import conveniencecore.item.IBehaviourProvider;
import convenientadditions.api.entity.behaviour.BehaviourSunlightChargeable;
import convenientadditions.api.util.EnchantmentUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemSunlightChargeable extends ItemChargeable implements ISunlightChargeable, IBehaviourProvider {

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

	@Override
	public void getBehaviours(ItemStack stack,World world,List<Long> behaviours){
		behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("sunlightChargeable"));
	}

	@Override
	public void getBehaviours(ItemStack stack,List<Long> behaviours){
		behaviours.add(BehaviourSunlightChargeable.DISCRIMINATOR);
	}
}
