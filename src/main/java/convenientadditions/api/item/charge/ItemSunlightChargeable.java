package convenientadditions.api.item.charge;

import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.util.EnchantmentUtil;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemSunlightChargeable extends ItemChargeable implements ISunlightChargeable {

    protected int chargeRate;

    public ItemSunlightChargeable(int capacity, boolean showDurabilityBar, boolean showTooltips, int sunlightChargeRate) {
        super(capacity, showDurabilityBar, showTooltips);
        chargeRate = sunlightChargeRate;
    }

    @Override
    public int getSunlightChargeRate(ItemStack item, SlotNotation slot, @Nullable EntityPlayer player) {
        int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentUtil.drain, item);
        double amp = (EnchantmentUtil.enchantmentScaleFactor[lvl] - 1) + 1;
        return (int) (chargeRate * amp);
    }

    @Override
    public boolean isSunlightChargeable(ItemStack item, SlotNotation slot, @Nullable EntityPlayer player) {
        return slot.isCommonChargable() && (player==null||(!player.isInsideOfMaterial(Material.WATER)&&!player.isInsideOfMaterial(Material.LAVA)));
    }

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(item, player, list, par4);
        list.add(TextFormatting.DARK_GRAY + ItemChargeable.localize("tooltip.convenientadditions:sunstoneDrained"));
    }

    @Override
    public boolean canApplyDrain(ItemStack item) {
        return true;
    }
}
