package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockCub3dAssembler;
import convenientadditions.block.BlockCub3dFrame;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemDirtChunk;
import convenientadditions.item.ItemFertilizer;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemDirtChunk itemDirtChunk = new ItemDirtChunk();

    public static void init()
    {
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemDirtChunk,Reference.dirtChunkItemName);
    }
}
