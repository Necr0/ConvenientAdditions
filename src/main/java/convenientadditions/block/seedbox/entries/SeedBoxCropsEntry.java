package convenientadditions.block.seedbox.entries;

import convenientadditions.api.entity.specialitem.BehaviourRegistry;
import convenientadditions.api.registry.seedbox.ISeedBoxItemBehaviourRegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class SeedBoxCropsEntry implements ISeedBoxItemBehaviourRegistryEntry {

    @Override
    public boolean hasSpecialBehaviour(ItemStack stack) {
        Item i = stack.getItem();
        return (i instanceof IPlantable || (i instanceof ItemBlock && ((ItemBlock) i).getBlock() instanceof IPlantable));
    }

    @Override
    public void getDiscriminators(ItemStack stack, List<Long> behaviours) {
        behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("autoCrops"));
    }

}
