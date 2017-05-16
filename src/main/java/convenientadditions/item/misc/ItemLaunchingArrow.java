package convenientadditions.item.misc;

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
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLaunchingArrow extends ItemArrow implements IModelVariantResourceLocationProvider {

    public ItemLaunchingArrow() {
        this.setRegistryName(ModConstants.ItemNames.launchingArrow).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.launchingArrow).setCreativeTab(ConvenientAdditions.CREATIVETAB).setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
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
        return net.minecraft.util.text.translation.I18n.translateToLocal("item." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.launchingArrow + "_" + stack.getItemDamage() + ".name");
    }

    @Override
    public ModelResourceLocation[] getModelResourceLocations() {
        return new ModelResourceLocation[]{new ModelResourceLocation(this.getRegistryName().toString().toLowerCase()  + "creeper", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "blast", "inventory"), new ModelResourceLocation(this.getRegistryName().toString().toLowerCase() + "slime", "inventory")};
    }
}