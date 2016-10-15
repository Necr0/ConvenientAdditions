package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.block.BlockTreeTap;
import convenientadditions.block.compostSoil.BlockCompostSoil;
import convenientadditions.block.compostSoil.BlockCompostSoilTilled;
import convenientadditions.block.compostSoil.ItemBlockCompostSoil;
import convenientadditions.block.composter.BlockComposter;
import convenientadditions.block.gateway.BlockGateway;
import convenientadditions.block.inventoryProxy.BlockInventoryProxy;
import convenientadditions.block.inventoryProxy.BlockInventoryProxySided;
import convenientadditions.block.inventoryProxy.filtered.BlockInventoryProxyFiltered;
import convenientadditions.block.itemReceiver.BlockItemReceiver;
import convenientadditions.block.itemTransmitter.BlockItemTransmitter;
import convenientadditions.block.playerInterface.BlockPlayerInterface;
import convenientadditions.block.powderkeg.BlockPowderKeg;
import convenientadditions.block.proximitySensor.BlockProximitySensor;
import convenientadditions.block.seedbox.BlockSeedBox;
import convenientadditions.block.setProvider.BlockSetProvider;
import convenientadditions.block.technical.BlockPhantomPlatform;
import convenientadditions.block.technical.BlockTempLight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ModConstants.Mod.MODID)
public class ModBlocks
{
    public static final BlockComposter composterBlock = new BlockComposter();
    public static final BlockCompostSoil compostSoilBlock = new BlockCompostSoil();
    public static final BlockCompostSoilTilled compostSoilTilledBlock = new BlockCompostSoilTilled();
    public static final BlockPowderKeg powderKegBlock = new BlockPowderKeg();
    public static final BlockPlayerInterface playerInterfaceBlock = new BlockPlayerInterface();
    public static final BlockProximitySensor proximitySensorBlock = new BlockProximitySensor();
    public static final BlockTempLight tempLightBlock = new BlockTempLight(.5F);
    public static final BlockPhantomPlatform phantomPlatformBlock = new BlockPhantomPlatform();
    public static final BlockSeedBox seedBoxBlock = new BlockSeedBox();
    public static final BlockSetProvider setProviderBlock = new BlockSetProvider();
    public static final BlockItemTransmitter itemTransmitterBlock = new BlockItemTransmitter();
    public static final BlockItemReceiver itemReceiverBlock = new BlockItemReceiver();
    public static final BlockInventoryProxy inventoryProxyBlock = new BlockInventoryProxy();
    public static final BlockInventoryProxySided inventoryProxySidedBlock = new BlockInventoryProxySided();
    public static final BlockInventoryProxyFiltered inventoryProxyFilteredBlock = new BlockInventoryProxyFiltered();
    public static final BlockTreeTap treetapBlock = new BlockTreeTap();
    public static final BlockGateway gatewayBlock = new BlockGateway();

    public static void init()
    {
        registerBlock(composterBlock,ModConstants.BlockNames.composterBlockName);
        registerBlock(compostSoilTilledBlock,ModConstants.BlockNames.compostSoilTilledBlockName);
        registerBlock(powderKegBlock,ModConstants.BlockNames.powderKegBlockName);
        registerBlock(playerInterfaceBlock,ModConstants.BlockNames.playerInterfaceBlockName);
        registerBlock(proximitySensorBlock,ModConstants.BlockNames.proximitySensorBlockName);
        registerBlock(tempLightBlock,ModConstants.BlockNames.tempLightBlockName);
        registerBlock(phantomPlatformBlock,ModConstants.BlockNames.phantomPlatformBlockName);
        registerBlock(seedBoxBlock,ModConstants.BlockNames.seedBoxBlockName);
        registerBlock(setProviderBlock,ModConstants.BlockNames.setProviderBlockName);
        registerBlock(itemTransmitterBlock,ModConstants.BlockNames.itemTransmitterBlockName);
        registerBlock(itemReceiverBlock,ModConstants.BlockNames.itemReceiverBlockName);
        registerBlock(inventoryProxyBlock,ModConstants.BlockNames.inventoryProxyBlockName);
        registerBlock(inventoryProxySidedBlock,ModConstants.BlockNames.inventoryProxySidedBlockName);
        registerBlock(inventoryProxyFilteredBlock,ModConstants.BlockNames.inventoryProxyFilteredBlockName);
        registerBlock(treetapBlock,ModConstants.BlockNames.treetapBlockName);
        registerBlock(gatewayBlock,ModConstants.BlockNames.gatewayBlockName);
        registerBlock(compostSoilBlock,new ItemBlockCompostSoil(compostSoilBlock),ModConstants.BlockNames.compostSoilBlockName);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader()
    {
        ModItems.registerItemBlockModel(composterBlock);
        ModItems.registerItemBlockModel(powderKegBlock);
        ModItems.registerItemBlockModel(playerInterfaceBlock);
        ModItems.registerItemBlockModel(proximitySensorBlock);
        ModItems.registerItemBlockModel(seedBoxBlock);
        ModItems.registerItemBlockModel(setProviderBlock);
        ModItems.registerItemBlockModel(itemTransmitterBlock);
        ModItems.registerItemBlockModel(itemReceiverBlock);
        ModItems.registerItemBlockModel(inventoryProxyBlock);
        ModItems.registerItemBlockModel(inventoryProxySidedBlock);
        ModItems.registerItemBlockModel(inventoryProxyFilteredBlock);
        ModItems.registerItemBlockModel(treetapBlock);
        ModItems.registerItemBlockModel(phantomPlatformBlock);
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilBlock),new ModelResourceLocation(compostSoilBlock.getRegistryName(),"inventory"));
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilTilledBlock),new ModelResourceLocation(compostSoilBlock.getRegistryName(),"inventory"));
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
