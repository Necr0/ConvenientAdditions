package convenientadditions.item.charge;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.item.IFuelItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlazingRock extends ItemSunlightChargeableBehaviour implements IFuelItem {
    public static ItemStack FULLY_CHARGED;

    public ItemBlazingRock() {
        super(48000, true, true, 12);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.blazingRockItemName)
                .setCreativeTab(ConvenientAdditions.CREATIVETAB)
                .setHasSubtypes(true)
                .setMaxStackSize(1);
        FULLY_CHARGED = new ItemStack(this, 1, 0);
        chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (!hasContainerItem(itemStack))
            return null;
        ItemStack ret = itemStack.copy();
        consumeCharge(ret, getFuelTime(itemStack) * 4);
        ret.setCount(1);
        return ret;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isFuelItem(ItemStack item) {
        return hasContainerItem(item);
    }

    @Override
    public int getFuelTime(ItemStack item) {
        return Math.min(getCharge(item), 55);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
}
