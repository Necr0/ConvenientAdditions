package convenientadditions.proxy;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.block.charge.chargeAccumulator.TileEntityChargeAccumulator;
import convenientadditions.block.charge.chargeTube.TileEntityChargeTube;
import convenientadditions.block.charge.sunlightCollector.TileEntitySunlightCollector;
import convenientadditions.block.composter.TileEntityComposter;
import convenientadditions.block.playerInterface.TileEntityPlayerInterface;
import convenientadditions.block.powderkeg.TileEntityPowderKeg;
import convenientadditions.block.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.seedbox.TileEntitySeedBox;
import convenientadditions.entity.EntityLaunchingArrow;
import convenientadditions.init.ModTileEntities;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public Side getSide(){
		return Side.SERVER;
	}
	
    public void registerRenderers()
    {
    }

    public void registerEntities()
    {
    	EntityRegistry.registerModEntity(EntityLaunchingArrow.class, Reference.launchingArrowEntityName, Reference.lauchingArrowEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    }

    public World getClientWorld()
    {
        return null;
    }

    public void InitRendering(){}
    public void InitModels(){}
}
