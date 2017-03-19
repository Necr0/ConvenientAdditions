package convenientadditions.item.tools.mobCatcher;

import convenientadditions.ModConstants;
import convenientadditions.init.ModItems;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class CustomModelMeshMobCatcher implements ItemMeshDefinition {

    public static void initVariants() {
        ArrayList<ModelResourceLocation> locs = new ArrayList<>();
        for (int type = 0; type < 5; type++) {
            for (int empty = 0; empty < 2; empty++)
                locs.add(new ModelResourceLocation(ModConstants.Mod.MODID+":"+ModConstants.ItemNames.mobCatcher.toLowerCase(), "empty=" + empty + ",type=" + type));
        }
        ModelLoader.registerItemVariants(ModItems.itemMobCatcherRegular, locs.toArray(new ModelResourceLocation[locs.size()]));
        ModelLoader.registerItemVariants(ModItems.itemMobCatcherSuper, new ModelResourceLocation[0]);
        ModelLoader.registerItemVariants(ModItems.itemMobCatcherHyper, new ModelResourceLocation[0]);
        ModelLoader.registerItemVariants(ModItems.itemMobCatcherMega, new ModelResourceLocation[0]);
        ModelLoader.registerItemVariants(ModItems.itemMobCatcherMaster, new ModelResourceLocation[0]);
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        int type=((ItemMobCatcher)stack.getItem()).type.ordinal();
        int empty=stack.getItemDamage();
        return new ModelResourceLocation(ModConstants.Mod.MODID+":"+ModConstants.ItemNames.mobCatcher.toLowerCase(), "empty=" + empty + ",type=" + type);
    }
}
