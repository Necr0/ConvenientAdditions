package convenientadditions.api;

import java.util.List;

import convenientadditions.Reference;
import convenientadditions.item.enchantments.EnchantmentUtil;
import net.minecraft.block.BlockAnvil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemSunlightChargable extends ItemChargable implements ISunlightChargable {

	private int chargeRate;
	
	protected ItemSunlightChargable(int capacity, boolean showDurabilityBar,
			boolean showTooltips, int sunlightChargeRate) {
		super(capacity, showDurabilityBar, showTooltips);
		chargeRate=sunlightChargeRate;
	}

	@Override
	public int getSunlightChargeRate(ItemStack item,int slot) {
		int lvl=EnchantmentHelper.getEnchantmentLevel(Reference.enchantmentDrainId+Reference.enchantmentIdBase, item);
		return (int)(chargeRate*EnchantmentUtil.enchantmentScaleFactor[lvl]);
	}

	@Override
	public boolean isSunlightChargable(ItemStack item,int slot) {
		return true;
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(item, player, list, par4);
		list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip.convenientadditions:sunstoneDrained"));
	}
	
	@Override
	public boolean canApplyDrain(ItemStack item){
		return true;
	}
}
