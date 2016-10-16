package convenientadditions.proxy;

import conveniencecore.entity.behaviour.EntitySpecialItem;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import convenientadditions.item.charge.ChargeTickHandler;
import convenientadditions.item.charge.enderPlate.EnderPlateInventoryTickHandler;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public Side getSide(){
		return Side.SERVER;
	}
	
    public void registerEventHandlers()
    {
    	EnderPlateInventoryTickHandler.init();
		ChargeTickHandler.init();
    }
    
    public void registerRenderers()
    {
    }

    public void registerEntities()
    {
    	EntityRegistry.registerModEntity(EntityLaunchingArrow.class, ModConstants.Entities.launchingArrowEntityName, ModConstants.Entities.lauchingArrowEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    	EntityRegistry.registerModEntity(EntitySpecialItem.class, ModConstants.Entities.specialItemEntityName, ModConstants.Entities.specialItemEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    }

    public World getClientWorld()
    {
        return null;
    }

    public void InitRendering(){}
    public void InitModels(){}
}
