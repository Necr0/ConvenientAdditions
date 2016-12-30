package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.base.ItemBlockMetadata;
import convenientadditions.block.BlockBlastPad;
import convenientadditions.block.BlockTreeBox;
import convenientadditions.block.BlockTreeTap;
import convenientadditions.block.compostSoil.BlockCompostSoil;
import convenientadditions.block.compostSoil.BlockCompostSoilTilled;
import convenientadditions.block.composter.BlockComposter;
import convenientadditions.block.hoverPad.BlockHoverPad;
import convenientadditions.block.inventoryProxy.BlockInventoryProxyNormal;
import convenientadditions.block.inventoryProxy.BlockInventoryProxySided;
import convenientadditions.block.inventoryProxy.filtered.BlockInventoryProxyFiltered;
import convenientadditions.block.itemReceiver.BlockItemReceiver;
import convenientadditions.block.itemTransmitter.BlockItemTransmitter;
import convenientadditions.block.platform.BlockPlatform;
import convenientadditions.block.platform.BlockSemiSolid;
import convenientadditions.block.playerInterface.BlockPlayerInterface;
import convenientadditions.block.powderkeg.BlockPowderKeg;
import convenientadditions.block.proximitySensor.BlockProximitySensor;
import convenientadditions.block.seedbox.BlockSeedBox;
import convenientadditions.block.setProvider.BlockSetProvider;
import convenientadditions.block.storageMatrix.BlockStorageMatrix;
import convenientadditions.block.technical.BlockPhantomPlatform;
import convenientadditions.block.technical.BlockTempLight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ModConstants.Mod.MODID)
public class ModBlocks {
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
    public static final BlockInventoryProxyNormal inventoryProxyBlock = new BlockInventoryProxyNormal();
    public static final BlockInventoryProxySided inventoryProxySidedBlock = new BlockInventoryProxySided();
    public static final BlockInventoryProxyFiltered inventoryProxyFilteredBlock = new BlockInventoryProxyFiltered();
    public static final BlockTreeTap treetapBlock = new BlockTreeTap();
    public static final BlockHoverPad hoverPadBlock = new BlockHoverPad();
    public static final BlockBlastPad blastPadBlock = new BlockBlastPad();
    public static final BlockPlatform platformBlock = new BlockPlatform();
    public static final BlockSemiSolid semiSolidBlock = new BlockSemiSolid();
    public static final BlockTreeBox treeBoxBlock = new BlockTreeBox();
    public static final BlockStorageMatrix storageMatrixBlock = new BlockStorageMatrix();

    public static void init() {
        registerBlock(composterBlock);
        registerBlock(powderKegBlock);
        registerBlock(playerInterfaceBlock);
        registerBlock(proximitySensorBlock);
        registerBlock(tempLightBlock);
        registerBlock(phantomPlatformBlock);
        registerBlock(seedBoxBlock);
        registerBlock(setProviderBlock);
        registerBlock(itemTransmitterBlock);
        registerBlock(itemReceiverBlock);
        registerBlock(inventoryProxyBlock);
        registerBlock(inventoryProxySidedBlock);
        registerBlock(inventoryProxyFilteredBlock);
        registerBlock(treetapBlock);
        registerBlock(hoverPadBlock);
        registerBlock(blastPadBlock);
        registerBlock(platformBlock, new ItemCloth(platformBlock));
        registerBlock(semiSolidBlock, new ItemCloth(semiSolidBlock));
        registerBlock(treeBoxBlock);
        registerBlock(compostSoilBlock, new ItemBlockMetadata(compostSoilBlock));
        registerBlock(compostSoilTilledBlock);
        //registerBlock(storageMatrixBlock, new ItemBlockStorageMatrix(storageMatrixBlock));
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader() {
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
        ModItems.registerItemBlockModel(hoverPadBlock);
        ModItems.registerItemBlockModel(blastPadBlock);
        ModelLoader.setCustomMeshDefinition(ItemBlock.getItemFromBlock(platformBlock), stack -> new ModelResourceLocation(platformBlock.getRegistryName(),BlockPlatform.COLOR.getName() + "=" + EnumDyeColor.byMetadata(stack.getItemDamage()).getName()));
        ModelLoader.setCustomMeshDefinition(ItemBlock.getItemFromBlock(semiSolidBlock), stack -> new ModelResourceLocation(semiSolidBlock.getRegistryName(),BlockSemiSolid.COLOR.getName() + "=" + EnumDyeColor.byMetadata(stack.getItemDamage()).getName()));
        ModItems.registerItemBlockModel(treeBoxBlock);
        //ModItems.registerItemBlockModel(storageMatrixBlock);
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilBlock), new ModelResourceLocation(compostSoilBlock.getRegistryName(), "inventory"));
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilTilledBlock), new ModelResourceLocation(compostSoilBlock.getRegistryName(), "inventory"));
    }

    public static void registerBlock(Block block) {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    public static void registerBlock(Block block, ItemBlock item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }
}
