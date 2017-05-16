package convenientadditions.item;

import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemCheeseBucket extends CAItem {
    public ItemCheeseBucket() {
        super(ModConstants.ItemNames.cheeseBucket);
        this.setDefaultInfo(false).setMaxStackSize(1);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
