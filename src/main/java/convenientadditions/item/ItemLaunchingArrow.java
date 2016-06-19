package convenientadditions.item;

import java.util.List;

import conveniencecore.item.resourceprovider.IModelVariantResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.entity.EntityLaunchingArrow;
import convenientadditions.entity.EntityLaunchingArrow.EnumLaunchingArrowVariant;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLaunchingArrow extends ItemArrow implements IModelVariantResourceLocationProvider {
	
    public ItemLaunchingArrow() {
        this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.launchingArrowItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for(EnumLaunchingArrowVariant e:EnumLaunchingArrowVariant.values()){
        	subItems.add(new ItemStack(this,1,e.ordinal()));
        }
    }

    @Override
    public EntityArrow makeTippedArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
    	EnumLaunchingArrowVariant var=stack.getItemDamage()<EnumLaunchingArrowVariant.values().length?EnumLaunchingArrowVariant.values()[stack.getItemDamage()]:EnumLaunchingArrowVariant.slime;
        return new EntityLaunchingArrow(worldIn, shooter, var);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocal("item."+ConvenientAdditions.MODID+":"+Reference.launchingArrowItemName+"_"+stack.getItemDamage()+".name");
    }

	@Override
	public ModelResourceLocation[] getModelResourceLocations() {
		return new ModelResourceLocation[]{new ModelResourceLocation(this.getResourceLocation()+"Creeper", "inventory"),new ModelResourceLocation(this.getResourceLocation()+"Blast", "inventory"),new ModelResourceLocation(this.getResourceLocation()+"Slime", "inventory")};
	}
}
