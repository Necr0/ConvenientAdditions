package convenientadditions.api.provider.itemnetwork;

import convenientadditions.api.IMatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemNetworkProvider {
    private static ArrayList<Tuple<World, BlockPos>> entryList = new ArrayList<>();

    static {
        MinecraftForge.EVENT_BUS.register(new ItemNetworkProvider());
    }

    public static void addEntry(World world, BlockPos provider) {
        if(world.isRemote)
            return;
        for (Tuple<World, BlockPos> t : entryList) {
            if (world == t.getFirst() && t.getSecond().equals(provider))
                return;
        }
        entryList.add(new Tuple<>(world, provider));
    }

    public static ArrayList<Tuple<World, BlockPos>> getEntries(IMatcher matcher) {
        ArrayList<Tuple<World, BlockPos>> ret = new ArrayList<>();
        for (Tuple<World, BlockPos> t : entryList) {
            IItemProvider p = getProvider(t.getFirst(), t.getSecond());
            if (p != null) {
                for (IMatcher m : p.getAccess()) {
                    if (m.matches(matcher) || matcher.matches(m)) {
                        ret.add(t);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public static ArrayList<Tuple<World, BlockPos>> getEntries() {
        return entryList;
    }

    public static IItemProvider getProvider(World w, BlockPos p) {
        if(!w.isBlockLoaded(p))
            return null;
        TileEntity te = w.getTileEntity(p);
        return (te != null && te instanceof IItemProvider) ? (IItemProvider) te : null;
    }

    @SubscribeEvent
    public void onServerTick(ServerTickEvent e) {
        Iterator<Tuple<World, BlockPos>> iter = entryList.iterator();
        while (iter.hasNext()) {
            Tuple<World, BlockPos> t = iter.next();

            if (!t.getFirst().isBlockLoaded(t.getSecond(), true)) {
                iter.remove();
            } else {
                TileEntity te = t.getFirst().getTileEntity(t.getSecond());
                if (te == null || !(te instanceof IItemProvider)) {
                    iter.remove();
                }
            }
        }
    }
}
