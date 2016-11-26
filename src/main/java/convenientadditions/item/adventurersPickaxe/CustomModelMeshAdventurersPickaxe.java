package convenientadditions.item.adventurersPickaxe;

import convenientadditions.init.ModItems;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class CustomModelMeshAdventurersPickaxe implements ItemMeshDefinition {

    public static void initVariants() {
        ArrayList<ModelResourceLocation> locs = new ArrayList<>();
        for (int head = 0; head < 4; head++) {
            for (int handle = 0; handle < 3; handle++) {
                locs.add(new ModelResourceLocation(ModItems.itemAdventurersPickaxe.getRegistryName(), "handle=" + handle + ",head=" + head));
            }
        }
        ModelLoader.registerItemVariants(ModItems.itemAdventurersPickaxe, locs.toArray(new ModelResourceLocation[locs.size()]));
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        int head = (int) ModItems.itemAdventurersPickaxe.getToolProperty(stack, "mining_level");
        int dur = (int) ModItems.itemAdventurersPickaxe.getToolProperty(stack, "durability");
        int handle = ((dur < 1000) ? 0 : (dur < 2500 ? 1 : 2));
        return new ModelResourceLocation(ModItems.itemAdventurersPickaxe.getRegistryName(), "handle=" + handle + ",head=" + head);
    }
}
