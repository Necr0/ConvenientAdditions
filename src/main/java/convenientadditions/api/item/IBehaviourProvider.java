package convenientadditions.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface IBehaviourProvider {
    void getBehaviours(ItemStack stack, World world, List<Long> behaviours);

    void getBehaviours(ItemStack stack, List<Long> behaviours);
}
