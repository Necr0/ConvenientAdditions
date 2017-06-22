package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.block.machine.autoWorkStation.TileEntityAutoWorkStation;
import convenientadditions.block.machine.hoverPad.TileEntityHoverPad;
import convenientadditions.block.machine.ironFarm.TileEntityIronFarm;
import convenientadditions.block.machine.itemReceiver.TileEntityItemReceiver;
import convenientadditions.block.machine.itemTransmitter.TileEntityItemTransmitter;
import convenientadditions.block.machine.jumpPad.TileEntityJumpPad;
import convenientadditions.block.machine.playerInterface.TileEntityPlayerInterface;
import convenientadditions.block.machine.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.machine.remoteInventoryProxy.TileEntityRemoteInventoryProxy;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider;
import convenientadditions.block.machine.storageMatrix.TileEntityStorageMatrix;
import convenientadditions.block.misc.composter.TileEntityComposter;
import convenientadditions.block.misc.displayCase.TileEntityDisplayCase;
import convenientadditions.block.misc.inventoryProxy.TileEntityInventoryProxy;
import convenientadditions.block.misc.inventoryProxy.filtered.TileEntityInventoryProxyFiltered;
import convenientadditions.block.misc.powderkeg.TileEntityPowderKeg;
import convenientadditions.block.misc.seedbox.TileEntitySeedBox;
import convenientadditions.block.misc.storagecrate.TileEntityStorageCrate;
import convenientadditions.block.misc.workStation.TileEntityWorkStation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityComposter.class, ModConstants.BlockNames.composter);
        GameRegistry.registerTileEntity(TileEntityPowderKeg.class, ModConstants.BlockNames.powderKeg);
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, ModConstants.BlockNames.playerInterface);
        GameRegistry.registerTileEntity(TileEntityProximitySensor.class, ModConstants.BlockNames.proximitySensor);
        GameRegistry.registerTileEntity(TileEntitySeedBox.class, ModConstants.BlockNames.seedBox);
        GameRegistry.registerTileEntity(TileEntitySetProvider.class, ModConstants.BlockNames.setProvider);
        GameRegistry.registerTileEntity(TileEntityItemTransmitter.class, ModConstants.BlockNames.itemTransmitter);
        GameRegistry.registerTileEntity(TileEntityItemReceiver.class, ModConstants.BlockNames.itemReceiver);
        GameRegistry.registerTileEntity(TileEntityInventoryProxy.class, ModConstants.BlockNames.inventoryProxy);
        GameRegistry.registerTileEntity(TileEntityInventoryProxyFiltered.class, ModConstants.BlockNames.inventoryProxyFiltered);
        GameRegistry.registerTileEntity(TileEntityHoverPad.class, ModConstants.BlockNames.hoverPad);
        GameRegistry.registerTileEntity(TileEntityJumpPad.class, ModConstants.BlockNames.jumpPad);
        GameRegistry.registerTileEntity(TileEntityStorageMatrix.class, ModConstants.BlockNames.storageMatrix);
        GameRegistry.registerTileEntity(TileEntityDisplayCase.class, ModConstants.BlockNames.displayCase);
        GameRegistry.registerTileEntity(TileEntityRemoteInventoryProxy.class, ModConstants.BlockNames.remoteInventoryProxy);
        GameRegistry.registerTileEntity(TileEntityIronFarm.class, ModConstants.BlockNames.ironFarm);
        GameRegistry.registerTileEntity(TileEntityWorkStation.class, ModConstants.BlockNames.workStation);
        GameRegistry.registerTileEntity(TileEntityAutoWorkStation.class, ModConstants.BlockNames.autoWorkStation);
        GameRegistry.registerTileEntity(TileEntityStorageCrate.class, ModConstants.BlockNames.storageCrate);
    }
}
