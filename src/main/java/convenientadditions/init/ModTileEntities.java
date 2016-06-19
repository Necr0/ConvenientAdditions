package convenientadditions.init;

import convenientadditions.Reference;
import convenientadditions.block.charge.chargeAccumulator.TileEntityChargeAccumulator;
import convenientadditions.block.charge.chargeTube.TileEntityChargeTube;
import convenientadditions.block.charge.sunlightCollector.TileEntitySunlightCollector;
import convenientadditions.block.composter.TileEntityComposter;
import convenientadditions.block.playerInterface.TileEntityPlayerInterface;
import convenientadditions.block.powderkeg.TileEntityPowderKeg;
import convenientadditions.block.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.seedbox.TileEntitySeedBox;
import convenientadditions.block.setProvider.TileEntitySetProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	public static void init(){
	    GameRegistry.registerTileEntity(TileEntityComposter.class, Reference.composterBlockName);
	    GameRegistry.registerTileEntity(TileEntityPowderKeg.class, Reference.powderKegBlockName);
	    GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, Reference.playerInterfaceBlockName);
	    GameRegistry.registerTileEntity(TileEntityProximitySensor.class, Reference.proximitySensorBlockName);
	    GameRegistry.registerTileEntity(TileEntitySunlightCollector.class, Reference.sunlightCollectorBlockName);
	    GameRegistry.registerTileEntity(TileEntityChargeAccumulator.class, Reference.chargeAccumulatorBlockName);
	    GameRegistry.registerTileEntity(TileEntityChargeTube.class, Reference.chargeTubeBlockName);
	    GameRegistry.registerTileEntity(TileEntitySeedBox.class, Reference.seedBoxBlockName);
	    GameRegistry.registerTileEntity(TileEntitySetProvider.class, Reference.setProviderBlockName);
	}
}
