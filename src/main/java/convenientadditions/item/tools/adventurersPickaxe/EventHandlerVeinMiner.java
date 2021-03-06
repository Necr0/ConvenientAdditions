package convenientadditions.item.tools.adventurersPickaxe;

import convenientadditions.api.util.FloodFill;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModItems;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EventHandlerVeinMiner {

    @SubscribeEvent
    public void veinminer(HarvestDropsEvent e) {
        if(e.getWorld().isRemote)
            return;
        if (e.getHarvester() != null && !e.getHarvester().isSneaking() && !e.getHarvester().getHeldItemMainhand().isEmpty() && e.getHarvester().getHeldItemMainhand().getItem() == ModItems.itemAdventurersPickaxe) {
            int veins = (int) ModItems.itemAdventurersPickaxe.getToolProperty(e.getHarvester().getHeldItemMainhand(), "mining_veins");
            String ore=Helper.getOreDictMatch(e.getState(), "ore", true);
            if (ore!=null && veins > 0) {
                List<BlockPos> l = FloodFill.floodFill(e.getWorld(), e.getPos(), new FloodFill.BlockMatcher(0).set(ore), 2, veins, true, true);
                if (l.size() > 0) {
                    e.getWorld().setBlockState(e.getPos(), e.getWorld().getBlockState(l.get(l.size() - 1)));
                    e.getWorld().setBlockToAir(l.get(l.size() - 1));
                }
            }
        }
    }
}
