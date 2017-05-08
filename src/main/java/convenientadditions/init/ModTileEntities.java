package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.block.composter.TileEntityComposter;
import convenientadditions.block.displayCase.TileEntityDisplayCase;
import convenientadditions.block.machine.ironFarm.TileEntityIronFarm;
import convenientadditions.block.machine.hoverPad.TileEntityHoverPad;
import convenientadditions.block.inventoryProxy.TileEntityInventoryProxy;
import convenientadditions.block.inventoryProxy.filtered.TileEntityInventoryProxyFiltered;
import convenientadditions.block.machine.itemReceiver.TileEntityItemReceiver;
import convenientadditions.block.machine.itemTransmitter.TileEntityItemTransmitter;
import convenientadditions.block.machine.jumpPad.TileEntityJumpPad;
import convenientadditions.block.machine.playerInterface.TileEntityPlayerInterface;
import convenientadditions.block.machine.remoteInventoryProxy.TileEntityRemoteInventoryProxy;
import convenientadditions.block.powderkeg.TileEntityPowderKeg;
import convenientadditions.block.machine.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.seedbox.TileEntitySeedBox;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider;
import convenientadditions.block.machine.storageMatrix.TileEntityStorageMatrix;
import convenientadditions.block.workStation.TileEntityWorkStation;
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
    }
}
