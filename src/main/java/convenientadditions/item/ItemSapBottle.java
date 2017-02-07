package convenientadditions.item;

import convenientadditions.ModConstants;
import convenientadditions.api.item.IModelVariantResourceLocationProvider;
import convenientadditions.base.CAItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSapBottle extends CAItem implements IModelVariantResourceLocationProvider {
    public ItemSapBottle() {
        super(ModConstants.ItemNames.sapBottle);
        this.setMaxStackSize(1).setHasSubtypes(true).setMaxDamage(0);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn));
        subItems.add(new ItemStack(itemIn, 1, 2));
    }

    @Override
    public ModelResourceLocation[] getModelResourceLocations() {
        return new ModelResourceLocation[]{new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "empty", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "half", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "full", "inventory")};
    }

    @Override
    public boolean showDurabilityBar(ItemStack item) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack item) {
        return 1D - (item.getItemDamage() / 2D);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        return MathHelper.hsvToRGB(Math.max(0.0F, (float)stack.getItemDamage() / 2) / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (!hasContainerItem(itemStack))
            return null;
        ItemStack ret = itemStack.copy();
        ret.setItemDamage(itemStack.getItemDamage() - 1);
        return ret;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getItemDamage() > 0;
    }
}
