package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.block.composter.TileEntityComposter;
import convenientadditions.block.gateway.TileEntityGateway;
import convenientadditions.block.hoverPad.TileEntityHoverPad;
import convenientadditions.block.inventoryProxy.TileEntityInventoryProxy;
import convenientadditions.block.inventoryProxy.filtered.TileEntityInventoryProxyFiltered;
import convenientadditions.block.itemReceiver.TileEntityItemReceiver;
import convenientadditions.block.itemTransmitter.TileEntityItemTransmitter;
import convenientadditions.block.playerInterface.TileEntityPlayerInterface;
import convenientadditions.block.powderkeg.TileEntityPowderKeg;
import convenientadditions.block.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.seedbox.TileEntitySeedBox;
import convenientadditions.block.setProvider.TileEntitySetProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityComposter.class, ModConstants.BlockNames.composterBlockName);
        GameRegistry.registerTileEntity(TileEntityPowderKeg.class, ModConstants.BlockNames.powderKegBlockName);
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, ModConstants.BlockNames.playerInterfaceBlockName);
        GameRegistry.registerTileEntity(TileEntityProximitySensor.class, ModConstants.BlockNames.proximitySensorBlockName);
        GameRegistry.registerTileEntity(TileEntitySeedBox.class, ModConstants.BlockNames.seedBoxBlockName);
        GameRegistry.registerTileEntity(TileEntitySetProvider.class, ModConstants.BlockNames.setProviderBlockName);
        GameRegistry.registerTileEntity(TileEntityItemTransmitter.class, ModConstants.BlockNames.itemTransmitterBlockName);
        GameRegistry.registerTileEntity(TileEntityItemReceiver.class, ModConstants.BlockNames.itemReceiverBlockName);
        GameRegistry.registerTileEntity(TileEntityInventoryProxy.class, ModConstants.BlockNames.inventoryProxyBlockName);
        GameRegistry.registerTileEntity(TileEntityInventoryProxyFiltered.class, ModConstants.BlockNames.inventoryProxyFilteredBlockName);
        GameRegistry.registerTileEntity(TileEntityGateway.class, ModConstants.BlockNames.gatewayBlockName);
        GameRegistry.registerTileEntity(TileEntityHoverPad.class, ModConstants.BlockNames.hoverPadBlockName);
    }
}
