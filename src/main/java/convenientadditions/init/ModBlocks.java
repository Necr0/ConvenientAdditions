package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockCub3dAssembler;
import convenientadditions.block.BlockCub3dFrame;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModBlocks
{
    public static final BlockCub3dAssembler assemblerBlock = new BlockCub3dAssembler();
    public static final BlockCub3dFrame frameBlock = new BlockCub3dFrame();
    public static final BlockComposter composterBlock = new BlockComposter();

    public static void init()
    {
        GameRegistry.registerBlock(assemblerBlock,Reference.assemblerBlockName);
        GameRegistry.registerBlock(frameBlock,Reference.frameBlockName);
        GameRegistry.registerBlock(composterBlock,Reference.composterBlockName);
    }
}
