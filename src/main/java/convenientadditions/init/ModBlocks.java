package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.block.BlockCompostSoil;
import convenientadditions.block.BlockCompostSoilTilled;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockPlayerInterface;
import convenientadditions.block.BlockPowderKeg;
import convenientadditions.block.BlockProximitySensor;
import convenientadditions.block.BlockSeedBox;
import convenientadditions.block.BlockTempLight;
import convenientadditions.block.BlockTitaniumOre;
import convenientadditions.block.charge.BlockChargeAccumulator;
import convenientadditions.block.charge.BlockChargeTube;
import convenientadditions.block.charge.BlockSunlightCollector;
import convenientadditions.block.item.ItemBlockCompostSoil;
import net.minecraft.block.Block;
import net.minecraft.client.main.GameConfiguration.GameInformation;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModBlocks
{
    public static final BlockTitaniumOre oreTitaniumBlock = new BlockTitaniumOre();
    public static final BlockComposter composterBlock = new BlockComposter();
    public static final BlockCompostSoil compostSoilBlock = new BlockCompostSoil();
    public static final BlockCompostSoilTilled compostSoilTilledBlock = new BlockCompostSoilTilled();
    public static final BlockPowderKeg powderKegBlock = new BlockPowderKeg();
    public static final BlockPlayerInterface playerInterfaceBlock = new BlockPlayerInterface();
    public static final BlockProximitySensor proximitySensorBlock = new BlockProximitySensor();
    public static final BlockTempLight tempLightBlock = new BlockTempLight(.5F);
    public static final BlockSunlightCollector sunlightCollectorBlock = new BlockSunlightCollector();
    public static final BlockChargeAccumulator chargeAccumulatorBlock = new BlockChargeAccumulator();
    public static final BlockChargeTube chargeTubeBlock = new BlockChargeTube();
    public static final BlockSeedBox seedBoxBlock = new BlockSeedBox();

    public static void init()
    {
        registerBlock(oreTitaniumBlock,Reference.oreTitaniumBlockName);
        registerBlock(composterBlock,Reference.composterBlockName);
        registerBlock(compostSoilTilledBlock,Reference.compostSoilTilledBlockName);
        registerBlock(powderKegBlock,Reference.powderKegBlockName);
        registerBlock(playerInterfaceBlock,Reference.playerInterfaceBlockName);
        registerBlock(proximitySensorBlock,Reference.proximitySensorBlockName);
        registerBlock(tempLightBlock,Reference.tempLightBlockName);
        registerBlock(sunlightCollectorBlock,Reference.sunlightCollectorBlockName);
        registerBlock(chargeAccumulatorBlock,Reference.chargeAccumulatorBlockName);
        registerBlock(seedBoxBlock,Reference.seedBoxBlockName);
        registerBlock(compostSoilBlock,new ItemBlockCompostSoil(compostSoilBlock),Reference.compostSoilBlockName);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader()
    {
        /*registerBlock(oreTitaniumBlock,Reference.oreTitaniumBlockName);
        registerBlock(composterBlock,Reference.composterBlockName);
        registerBlock(compostSoilTilledBlock,Reference.compostSoilTilledBlockName);
        registerBlock(powderKegBlock,Reference.powderKegBlockName);
        registerBlock(playerInterfaceBlock,Reference.playerInterfaceBlockName);
        registerBlock(proximitySensorBlock,Reference.proximitySensorBlockName);
        registerBlock(tempLightBlock,Reference.tempLightBlockName);
        registerBlock(sunlightCollectorBlock,Reference.sunlightCollectorBlockName);
        registerBlock(chargeAccumulatorBlock,Reference.chargeAccumulatorBlockName);
        registerBlock(seedBoxBlock,Reference.seedBoxBlockName);
        registerBlock(compostSoilBlock,new ItemBlockCompostSoil(compostSoilBlock),Reference.compostSoilBlockName);*/
    }
    
    public static void registerBlock(Block block,String registryName)
    {
    	if(block.getRegistryName()==null)
    		block.setRegistryName(registryName);
    	GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(registryName));
    }
    
    public static void registerBlock(Block block,ItemBlock item,String registryName)
    {
    	if(block.getRegistryName()==null)
    		block.setRegistryName(registryName);
    	GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(registryName));
    }
}
