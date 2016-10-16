package conveniencecore;

import conveniencecore.entity.behaviour.BehaviourRegistry;
import conveniencecore.entity.behaviour.EntitySpecialItem;
import conveniencecore.gui.container.ContainerTickHandler;
import conveniencecore.item.fuelitem.FuelItemFuelHandler;
import conveniencecore.item.invtick.PlayerInventoryTickHandler;
import conveniencecore.item.soulbound.EventHandlerSoulbound;
import conveniencecore.network.ModNetworking;
import convenientadditions.ConvenientAdditions;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ConvenienceCore {
	
	public static final String SPECIALITEMENTITYNAME="specialItem";
    public static final int SPECIALITEMENTITYID = 255;
	
    public void preinit(FMLPreInitializationEvent event)
    {
    	ModNetworking.init();
    	FuelItemFuelHandler.init();
    	PlayerInventoryTickHandler.init();
    	EventHandlerSoulbound.init();
    	BehaviourRegistry.init();
    	ContainerTickHandler.init();
    	EntityRegistry.registerModEntity(EntitySpecialItem.class, SPECIALITEMENTITYNAME, SPECIALITEMENTITYID, ConvenientAdditions.INSTANCE, 128, 5, true);
    }
}
