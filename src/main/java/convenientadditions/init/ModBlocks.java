package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.block.BlockPhantomPlatform;
import convenientadditions.block.BlockTempLight;
import convenientadditions.block.BlockTitaniumOre;
import convenientadditions.block.charge.chargeAccumulator.BlockChargeAccumulator;
import convenientadditions.block.charge.chargeTube.BlockChargeTube;
import convenientadditions.block.charge.sunlightCollector.BlockSunlightCollector;
import convenientadditions.block.compostSoil.BlockCompostSoil;
import convenientadditions.block.compostSoil.BlockCompostSoilTilled;
import convenientadditions.block.compostSoil.ItemBlockCompostSoil;
import convenientadditions.block.composter.BlockComposter;
import convenientadditions.block.playerInterface.BlockPlayerInterface;
import convenientadditions.block.powderkeg.BlockPowderKeg;
import convenientadditions.block.proximitySensor.BlockProximitySensor;
import convenientadditions.block.seedbox.BlockSeedBox;
import convenientadditions.block.setProvider.BlockSetProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ConvenientAdditions.MODID)
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
    public static final BlockPhantomPlatform phantomPlatformBlock = new BlockPhantomPlatform();
    public static final BlockSunlightCollector sunlightCollectorBlock = new BlockSunlightCollector();
    public static final BlockChargeAccumulator chargeAccumulatorBlock = new BlockChargeAccumulator();
    public static final BlockChargeTube chargeTubeBlock = new BlockChargeTube();
    public static final BlockSeedBox seedBoxBlock = new BlockSeedBox();
    public static final BlockSetProvider setProviderBlock = new BlockSetProvider();

    public static void init()
    {
        registerBlock(oreTitaniumBlock,Reference.oreTitaniumBlockName);
        registerBlock(composterBlock,Reference.composterBlockName);
        registerBlock(compostSoilTilledBlock,Reference.compostSoilTilledBlockName);
        registerBlock(powderKegBlock,Reference.powderKegBlockName);
        registerBlock(playerInterfaceBlock,Reference.playerInterfaceBlockName);
        registerBlock(proximitySensorBlock,Reference.proximitySensorBlockName);
        registerBlock(tempLightBlock,Reference.tempLightBlockName);
        registerBlock(phantomPlatformBlock,Reference.phantomPlatformBlockName);
        //registerBlock(sunlightCollectorBlock,Reference.sunlightCollectorBlockName);
        //registerBlock(chargeAccumulatorBlock,Reference.chargeAccumulatorBlockName);
        //registerBlock(chargeTubeBlock,Reference.chargeTubeBlockName);
        registerBlock(seedBoxBlock,Reference.seedBoxBlockName);
        registerBlock(setProviderBlock,Reference.setProviderBlockName);
        registerBlock(compostSoilBlock,new ItemBlockCompostSoil(compostSoilBlock),Reference.compostSoilBlockName);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader()
    {
        ModItems.registerItemBlockModel(oreTitaniumBlock,oreTitaniumBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(composterBlock,composterBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(powderKegBlock,powderKegBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(playerInterfaceBlock,playerInterfaceBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(proximitySensorBlock,proximitySensorBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(seedBoxBlock,seedBoxBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(setProviderBlock,setProviderBlock.getModelResourceLocation());
        ModItems.registerItemBlockModel(phantomPlatformBlock,phantomPlatformBlock.getModelResourceLocation());
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilBlock), compostSoilBlock.getModelResourceLocation());
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilTilledBlock), compostSoilTilledBlock.getModelResourceLocation());
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
