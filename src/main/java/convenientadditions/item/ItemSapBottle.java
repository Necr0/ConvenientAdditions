package convenientadditions.item;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.item.IModelVariantResourceLocationProvider;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSapBottle extends Item implements IModelVariantResourceLocationProvider {
    public ItemSapBottle() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sapBottleItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn));
        subItems.add(new ItemStack(itemIn, 1, 2));
    }

    @Override
    public ModelResourceLocation[] getModelResourceLocations() {
        return new ModelResourceLocation[]{new ModelResourceLocation(this.getRegistryName() + "Empty", "inventory"), new ModelResourceLocation(this.getRegistryName() + "Half", "inventory"), new ModelResourceLocation(this.getRegistryName() + "Full", "inventory")};
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
