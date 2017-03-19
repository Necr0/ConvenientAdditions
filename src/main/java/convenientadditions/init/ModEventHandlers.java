package convenientadditions.init;

import convenientadditions.handler.EventHandlerPlayerMovement;
import convenientadditions.handler.ContainerTickHandler;
import convenientadditions.handler.PlayerInventoryTickHandler;
import convenientadditions.handler.FuelItemFuelHandler;
import convenientadditions.item.tools.adventurersPickaxe.EventHandlerLuck;
import convenientadditions.item.tools.adventurersPickaxe.EventHandlerSoulbound;
import convenientadditions.item.tools.adventurersPickaxe.EventHandlerVeinMiner;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModEventHandlers {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new PlayerInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new ContainerTickHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerSoulbound());
        MinecraftForge.EVENT_BUS.register(new EventHandlerVeinMiner());
        MinecraftForge.EVENT_BUS.register(new EventHandlerLuck());
        MinecraftForge.EVENT_BUS.register(new EventHandlerPlayerMovement());
        MinecraftForge.EVENT_BUS.register(new ModLoot());
        GameRegistry.registerFuelHandler(new FuelItemFuelHandler());
    }
}
