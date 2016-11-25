package convenientadditions.item.adventurersPickaxe;

import convenientadditions.api.util.Helper;
import convenientadditions.init.ModItems;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EventHandlerVeinMiner {

    @SubscribeEvent
    public void veinminer(HarvestDropsEvent e) {
        //e.getState().getBlock().getDrops(e.getWorld(), e.getPos(), e.getState(), 3);
        if (e.getHarvester() != null && !e.getHarvester().isSneaking() && e.getHarvester().getHeldItemMainhand() != null && e.getHarvester().getHeldItemMainhand().getItem() == ModItems.itemAdventurersPickaxe) {
            int veins = (int) ModItems.itemAdventurersPickaxe.getToolProperty(e.getHarvester().getHeldItemMainhand(), "mining_veins");
            if (Helper.doesOreDictMatch(e.getState(), "ore", true) && veins > 0) {
                List<BlockPos> l = Helper.FloodFill.floodFill(e.getWorld(), e.getPos(), e.getState(), 2, veins, true, true);
                if (l.size() > 0) {
                    e.getWorld().setBlockState(e.getPos(), e.getWorld().getBlockState(l.get(l.size() - 1)));
                    e.getWorld().setBlockToAir(l.get(l.size() - 1));
                }
            }
        }
    }
}
