package convenientadditions.init;

import convenientadditions.event.EventHandlerPlayerMovement;
import convenientadditions.handler.ChargeTickHandler;
import convenientadditions.handler.ContainerTickHandler;
import convenientadditions.handler.PlayerInventoryTickHandler;
import convenientadditions.item.FuelItemFuelHandler;
import convenientadditions.item.adventurersPickaxe.EventHandlerLuck;
import convenientadditions.item.adventurersPickaxe.EventHandlerSoulbound;
import convenientadditions.item.adventurersPickaxe.EventHandlerVeinMiner;
import convenientadditions.item.charge.enderPlate.EnderPlateInventoryTickHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModEventHandlers {
    public static void init() {
        if (ModConfig.enderPlate_crystalCharge)
            MinecraftForge.EVENT_BUS.register(new EnderPlateInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new ChargeTickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new ContainerTickHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerSoulbound());
        MinecraftForge.EVENT_BUS.register(new EventHandlerVeinMiner());
        MinecraftForge.EVENT_BUS.register(new EventHandlerLuck());
        MinecraftForge.EVENT_BUS.register(new EventHandlerPlayerMovement());
        GameRegistry.registerFuelHandler(new FuelItemFuelHandler());
    }
}
