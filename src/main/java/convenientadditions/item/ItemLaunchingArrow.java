package convenientadditions.item;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.item.IModelVariantResourceLocationProvider;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow.EnumLaunchingArrowVariant;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemLaunchingArrow extends ItemArrow implements IModelVariantResourceLocationProvider {

    public ItemLaunchingArrow() {
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.launchingArrowItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (EnumLaunchingArrowVariant e : EnumLaunchingArrowVariant.values()) {
            subItems.add(new ItemStack(this, 1, e.ordinal()));
        }
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        EnumLaunchingArrowVariant var = stack.getItemDamage() < EnumLaunchingArrowVariant.values().length ? EnumLaunchingArrowVariant.values()[stack.getItemDamage()] : EnumLaunchingArrowVariant.slime;
        return new EntityLaunchingArrow(worldIn, shooter, var);
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return net.minecraft.util.text.translation.I18n.translateToLocal("item." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.launchingArrowItemName + "_" + stack.getItemDamage() + ".name");
    }

    @Override
    public ModelResourceLocation[] getModelResourceLocations() {
        return new ModelResourceLocation[]{new ModelResourceLocation(this.getRegistryName().toString().toLowerCase()  + "creeper", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "blast", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "slime", "inventory")};
    }
}