package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlock;
import convenientadditions.base.block.block.CABlockCTGTransparentA;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.base.item.ItemBlockMetadata;
import convenientadditions.block.machine.BlockBlastPad;
import convenientadditions.block.machine.BlockMachineBlock;
import convenientadditions.block.machine.autoWorkStation.BlockAutoWorkStation;
import convenientadditions.block.machine.hoverPad.BlockHoverPad;
import convenientadditions.block.machine.ironFarm.BlockIronFarm;
import convenientadditions.block.machine.itemReceiver.BlockItemReceiver;
import convenientadditions.block.machine.itemTransmitter.BlockItemTransmitter;
import convenientadditions.block.machine.jumpPad.BlockJumpPad;
import convenientadditions.block.machine.playerInterface.BlockPlayerInterface;
import convenientadditions.block.machine.proximitySensor.BlockProximitySensor;
import convenientadditions.block.machine.remoteInventoryProxy.BlockRemoteInventoryProxy;
import convenientadditions.block.machine.setProvider.BlockSetProvider;
import convenientadditions.block.machine.storageMatrix.BlockStorageMatrix;
import convenientadditions.block.misc.enderProof.BlockEnderProof;
import convenientadditions.block.misc.BlockPunjiSticks;
import convenientadditions.block.misc.BlockTreeTap;
import convenientadditions.block.misc.compostSoil.BlockCompostSoil;
import convenientadditions.block.misc.compostSoil.BlockCompostSoilTilled;
import convenientadditions.block.misc.composter.BlockComposter;
import convenientadditions.block.misc.displayCase.BlockDisplayCase;
import convenientadditions.block.misc.enderProof.BlockEnderProofGlass;
import convenientadditions.block.misc.inventoryProxy.BlockInventoryProxyNormal;
import convenientadditions.block.misc.inventoryProxy.BlockInventoryProxySided;
import convenientadditions.block.misc.inventoryProxy.filtered.BlockInventoryProxyFiltered;
import convenientadditions.block.misc.platform.BlockPlatform;
import convenientadditions.block.misc.platform.BlockSemiSolid;
import convenientadditions.block.misc.powderkeg.BlockPowderKeg;
import convenientadditions.block.misc.seedbox.BlockSeedBox;
import convenientadditions.block.misc.storagecrate.BlockStorageCrate;
import convenientadditions.block.misc.workStation.BlockWorkStation;
import convenientadditions.block.technical.BlockPhantomPlatform;
import convenientadditions.block.technical.BlockTempLight;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public static final BlockTreeTap treetap = new BlockTreeTap();
    public static final BlockHoverPad hoverPad = new BlockHoverPad();
    public static final BlockBlastPad blastPad = new BlockBlastPad();
    public static final BlockJumpPad jumpPad = new BlockJumpPad();
    public static final BlockPlatform platform = new BlockPlatform();
    public static final BlockSemiSolid semiSolidBlock = new BlockSemiSolid();
    public static final BlockStorageMatrix storageMatrix = new BlockStorageMatrix();
    public static final BlockDisplayCase displayCase = new BlockDisplayCase();
    public static final BlockRemoteInventoryProxy remoteInventoryProxy = new BlockRemoteInventoryProxy();
    public static final BlockEnderProof enderProofBlock = new BlockEnderProof();
    public static final BlockEnderProofGlass enderProofGlass = new BlockEnderProofGlass();
    public static final BlockIronFarm ironFarm = new BlockIronFarm();
    public static final BlockWorkStation workStation = new BlockWorkStation();
    public static final BlockAutoWorkStation autoWorkStation = new BlockAutoWorkStation();
    public static final BlockPunjiSticks punjiSticks = new BlockPunjiSticks();
    public static final BlockStorageCrate storageCrate = new BlockStorageCrate();
    //dummy
    public static final BlockMachineBlock machineBlock = new BlockMachineBlock();
    public static final Block ironGolemBlock = new CABlock(ModConstants.BlockNames.ironGolemBlock, Material.IRON).setDefaultInfo(false).setCategory(EnumItemCategory.BUILDING_BLOCK).setSoundType(SoundType.METAL).setHardness(5.0F).setResistance(10.0F);
    public static final Block cheeseBlock = new CABlock(ModConstants.BlockNames.cheeseBlock, Material.CLAY).setDefaultInfo(false).setCategory(EnumItemCategory.BUILDING_BLOCK).setSoundType(SoundType.SLIME).setHardness(0.5F);
    public static final Block woodenTile = new CABlock(ModConstants.BlockNames.woodenTile, Material.WOOD).setDefaultInfo(false).setCategory(EnumItemCategory.BUILDING_BLOCK).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
    public static final Block clearGlass = new CABlockCTGTransparentA(ModConstants.BlockNames.clearGlass, Material.GLASS).setDefaultInfo(false).setCategory(EnumItemCategory.BUILDING_BLOCK).setSoundType(SoundType.GLASS).setHardness(0.3F);

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
        registerBlock(treetap);
        registerBlock(hoverPad);
        registerBlock(blastPad);
        registerBlock(jumpPad);
        registerBlock(platform, new ItemCloth(platform));
        registerBlock(semiSolidBlock, new ItemCloth(semiSolidBlock));
        registerBlock(compostSoilBlock, new ItemBlockMetadata(compostSoilBlock));
        registerBlock(compostSoilTilledBlock);
        registerBlock(storageMatrix);
        registerBlock(machineBlock);
        registerBlock(displayCase);
        registerBlock(remoteInventoryProxy);
        registerBlock(enderProofBlock);
        registerBlock(enderProofGlass);
        registerBlock(ironFarm);
        registerBlock(ironGolemBlock);
        registerBlock(workStation);
        registerBlock(autoWorkStation);
        registerBlock(cheeseBlock);
        registerBlock(punjiSticks);
        registerBlock(storageCrate);
        registerBlock(woodenTile);
        registerBlock(clearGlass);
        //registerBlock(passiveGenerator);
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
        ModItems.registerItemBlockModel(treetap);
        ModItems.registerItemBlockModel(phantomPlatformBlock);
        ModItems.registerItemBlockModel(hoverPad);
        ModItems.registerItemBlockModel(blastPad);
        ModItems.registerItemBlockModel(jumpPad);
        ModelLoader.setCustomMeshDefinition(ItemBlock.getItemFromBlock(platform), stack -> new ModelResourceLocation(platform.getRegistryName(),BlockPlatform.COLOR.getName() + "=" + EnumDyeColor.byMetadata(stack.getItemDamage()).getName()));
        ModelLoader.setCustomMeshDefinition(ItemBlock.getItemFromBlock(semiSolidBlock), stack -> new ModelResourceLocation(semiSolidBlock.getRegistryName(),BlockSemiSolid.COLOR.getName() + "=" + EnumDyeColor.byMetadata(stack.getItemDamage()).getName()));
        ModItems.registerItemBlockModel(storageMatrix);
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilBlock), new ModelResourceLocation(compostSoilBlock.getRegistryName(), "inventory"));
        ModItems.registerIndependentModelLocation(ItemBlock.getItemFromBlock(compostSoilTilledBlock), new ModelResourceLocation(compostSoilBlock.getRegistryName(), "inventory"));
        ModItems.registerItemBlockModel(machineBlock);
        ModItems.registerItemBlockModel(displayCase);
        ModItems.registerItemBlockModel(remoteInventoryProxy);
        ModItems.registerItemBlockModel(enderProofBlock);
        ModItems.registerItemBlockModel(enderProofGlass);
        ModItems.registerItemBlockModel(ironFarm);
        ModItems.registerItemBlockModel(ironGolemBlock);
        ModItems.registerItemBlockModel(workStation);
        ModItems.registerItemBlockModel(autoWorkStation);
        ModItems.registerItemBlockModel(cheeseBlock);
        ModItems.registerItemBlockModel(punjiSticks);
        ModItems.registerItemBlockModel(storageCrate);
        ModItems.registerItemBlockModel(woodenTile);
        ModItems.registerItemBlockModel(clearGlass);
    }

    public static void registerBlock(Block block) {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    public static void registerBlock(Block block, ItemBlock item) {
        GameRegistry.register(block);
        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidModel(Block block)
    {
        ModelLoader.setCustomMeshDefinition(ItemBlock.getItemFromBlock(block), stack -> new ModelResourceLocation(block.getRegistryName(), "fluid"));
        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(block.getRegistryName(), "fluid");
            }
        });
    }
}
