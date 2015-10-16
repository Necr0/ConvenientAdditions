package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemDirtChunk;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.ItemRedstonePulseEmitter;
import convenientadditions.item.ItemSunstone;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemDirtChunk itemDirtChunk = new ItemDirtChunk();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemRedstonePulseEmitter itemRedstonePulseEmitter = new ItemRedstonePulseEmitter();

    public static void init()
    {
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemDirtChunk,Reference.dirtChunkItemName);
        GameRegistry.registerItem(itemSunstone,Reference.sunstoneItemName);
        GameRegistry.registerItem(itemRedstonePulseEmitter,Reference.redstonePulseEmitterItemName);
    }
}
