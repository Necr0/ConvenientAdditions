package convenientadditions.item.charge.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.charge.IChargeable;
import convenientadditions.api.util.EnchantmentUtil;
import convenientadditions.api.util.Helper;
import convenientadditions.item.charge.ItemSunlightChargeableBehaviour;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemChargingRing extends ItemSunlightChargeableBehaviour implements IBauble {
    public static final int chargingRingBaseCharge = 7;
    public static ItemStack FULLY_CHARGED;

    public ItemChargingRing() {
        super(120000, true, true, 5);
        this.setHasSubtypes(true)
                .setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.chargingRingItemName)
                .setCreativeTab(ConvenientAdditions.CREATIVETAB)
                .setHasSubtypes(true)
                .setMaxStackSize(1);
        FULLY_CHARGED = new ItemStack(this, 1, 0);
        chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote || getCharge(itemstack) <= 0 || !(player instanceof EntityPlayer))
            return;
        int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentUtil.chargeEfficiency, itemstack);
        int maxRemaining = (int) (chargingRingBaseCharge * EnchantmentUtil.enchantmentScaleFactor[lvl]);
        int chargeRemaining = Math.min(getCharge(itemstack), maxRemaining / 2);
        Iterable<SlotNotation> iter= InventoryIterator.getIterable((EntityPlayer) player, EnumInventory.BAUBLES, EnumInventory.MAIN);
        for (SlotNotation slot : iter) {
            if (chargeRemaining == 0)
                return;
            ItemStack target = slot.getItem();
            if (!target.isEmpty() && target.getItem() instanceof IChargeable && target.getItem() != this) {
                IChargeable tar = (IChargeable) target.getItem();
                if (tar.isChargeable(target)) {
                    int overflow = tar.chargeItem(target, chargeRemaining);
                    dischargeItem(itemstack, (chargeRemaining - overflow) * 2);
                    chargeRemaining = overflow;
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
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
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip.convenientadditions:chargingRing"));
        super.addInformation(stack, player, list, par4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
}
