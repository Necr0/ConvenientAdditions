package convenientadditions.item.relic;

import convenientadditions.ModConstants;
import convenientadditions.api.item.IFuelItem;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlazingRock extends CAItem implements IFuelItem {

    public ItemBlazingRock() {
        super(ModConstants.ItemNames.blazingRock);
        this.setDefaultJoke(true).setDefaultAdditionalInfo(true).setMaxStackSize(1);
        this.setCategory(EnumItemCategory.RELIC);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack ret = new ItemStack(this, itemStack.isEmpty()?1:itemStack.getCount(), itemStack.getItemDamage());
        ret.setCount(1);
        return ret;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isFuelItem(ItemStack item) {
        return true;
    }

    @Override
    public int getFuelTime(ItemStack item) {
        return (System.currentTimeMillis()/1000)%22<=3?200:0;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return (System.currentTimeMillis()/1000)%22<=3;
    }
}
