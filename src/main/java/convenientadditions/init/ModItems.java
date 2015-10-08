package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockCub3dAssembler;
import convenientadditions.block.BlockCub3dFrame;
import convenientadditions.item.ItemCompost;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemCompost itemCompost = new ItemCompost();

    public static void init()
    {
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
    }
}
