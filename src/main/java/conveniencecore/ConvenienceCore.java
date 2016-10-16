package conveniencecore;

import conveniencecore.entity.behaviour.BehaviourRegistry;
import conveniencecore.gui.container.ContainerTickHandler;
import conveniencecore.item.fuelitem.FuelItemFuelHandler;
import conveniencecore.item.invtick.PlayerInventoryTickHandler;
import conveniencecore.item.soulbound.EventHandlerSoulbound;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConvenienceCore {
	
    public void preinit(FMLPreInitializationEvent event)
    {
    	FuelItemFuelHandler.init();
    	PlayerInventoryTickHandler.init();
    	EventHandlerSoulbound.init();
    	BehaviourRegistry.init();
    	ContainerTickHandler.init();
    }
}
