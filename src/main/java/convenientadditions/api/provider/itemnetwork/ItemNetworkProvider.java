package convenientadditions.api.provider.itemnetwork;

import convenientadditions.api.IMatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemNetworkProvider {
    private static ArrayList<Tuple<Integer, BlockPos>> entryList = new ArrayList<>();

    static {
        MinecraftForge.EVENT_BUS.register(new ItemNetworkProvider());
    }

    public static void addEntry(World world, BlockPos provider) {
        if(world.isRemote)
            return;
        int dimension=world.provider.getDimension();
        for (Tuple<Integer, BlockPos> t : entryList) {
            if (dimension == t.getFirst() && t.getSecond().equals(provider))
                return;
        }
        entryList.add(new Tuple<>(dimension, provider));
    }

    public static ArrayList<Tuple<Integer, BlockPos>> getEntries(IMatcher matcher) {
        ArrayList<Tuple<Integer, BlockPos>> ret = new ArrayList<>();
        Iterator<Tuple<Integer, BlockPos>> iter = entryList.iterator();
        while (iter.hasNext()) {
            Tuple<Integer, BlockPos> t = iter.next();
            try {
                IItemProvider p = getProvider(DimensionManager.getWorld(t.getFirst()), t.getSecond());
                if (p != null) {
                    for (IMatcher m : p.getAccess()) {
                        if (m.matches(matcher) || matcher.matches(m)) {
                            ret.add(t);
                            break;
                        }
                    }
                }
            }catch (NullPointerException e){
                iter.remove();
            }
        }
        return ret;
    }

    public static ArrayList<Tuple<Integer, BlockPos>> getEntries() {
        return entryList;
    }

    public static IItemProvider getProvider(World w, BlockPos p) {
        if(!w.isBlockLoaded(p))
            return null;
        TileEntity te = w.getTileEntity(p);
        return (te != null && te instanceof IItemProvider) ? (IItemProvider) te : null;
    }

    public static IItemProvider getProvider(int dim, BlockPos p) {
        try {
            World w=DimensionManager.getWorld(dim);
            if(!w.isBlockLoaded(p))
                return null;
            TileEntity te = w.getTileEntity(p);
            return (te != null && te instanceof IItemProvider) ? (IItemProvider) te : null;
        }catch (NullPointerException e){
            return null;
        }
    }

    @SubscribeEvent
    public void onServerTick(ServerTickEvent event) {
        Iterator<Tuple<Integer, BlockPos>> iter = entryList.iterator();
        while (iter.hasNext()) {
            Tuple<Integer, BlockPos> t = iter.next();
            try {
                World w=DimensionManager.getWorld(t.getFirst());

                if (!w.isBlockLoaded(t.getSecond(), true)) {
                    iter.remove();
                } else {
                    TileEntity te = w.getTileEntity(t.getSecond());
                    if (te == null || !(te instanceof IItemProvider)) {
                        iter.remove();
                    }
                }
            }catch (NullPointerException e){
                iter.remove();
            }
        }
    }
}
