package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.block.BlockCompostSoil;
import convenientadditions.block.BlockCompostSoilTilled;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockCub3dAssembler;
import convenientadditions.block.BlockCub3dFrame;
import convenientadditions.block.BlockPlayerInterface;
import convenientadditions.block.BlockPowderKeg;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModBlocks
{
    public static final BlockCub3dAssembler assemblerBlock = new BlockCub3dAssembler();
    public static final BlockCub3dFrame frameBlock = new BlockCub3dFrame();
    public static final BlockComposter composterBlock = new BlockComposter();
    public static final BlockCompostSoil compostSoilBlock = new BlockCompostSoil();
    public static final BlockCompostSoilTilled compostSoilTilledBlock = new BlockCompostSoilTilled();
    public static final BlockPowderKeg powderKegBlock = new BlockPowderKeg();
    public static final BlockPlayerInterface playerInterfaceBlock = new BlockPlayerInterface();

    public static void init()
    {
        GameRegistry.registerBlock(assemblerBlock,Reference.assemblerBlockName);
        GameRegistry.registerBlock(frameBlock,Reference.frameBlockName);
        GameRegistry.registerBlock(composterBlock,Reference.composterBlockName);
        GameRegistry.registerBlock(compostSoilBlock,Reference.compostSoilBlockName);
        GameRegistry.registerBlock(compostSoilTilledBlock,Reference.compostSoilTilledBlockName);
        GameRegistry.registerBlock(powderKegBlock,Reference.powderKegBlockName);
        GameRegistry.registerBlock(playerInterfaceBlock,Reference.playerInterfaceBlockName);
    }
}
