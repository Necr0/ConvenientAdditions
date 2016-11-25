package convenientadditions.proxy;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.entity.specialitem.EntitySpecialItem;
import convenientadditions.entity.launchingArrow.EntityLaunchingArrow;
import convenientadditions.init.ModConfig;
import convenientadditions.item.FuelItemFuelHandler;
import convenientadditions.item.PlayerInventoryTickHandler;
import convenientadditions.item.adventurersPickaxe.EventHandlerLuck;
import convenientadditions.item.adventurersPickaxe.EventHandlerSoulbound;
import convenientadditions.item.adventurersPickaxe.EventHandlerVeinMiner;
import convenientadditions.item.charge.ChargeTickHandler;
import convenientadditions.item.charge.enderPlate.EnderPlateInventoryTickHandler;
import convenientadditions.item.transmutationTome.ContainerTickHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public Side getSide() {
        return Side.SERVER;
    }

    public void registerEventHandlers() {
        if (ModConfig.enderPlate_crystalCharge)
            MinecraftForge.EVENT_BUS.register(new EnderPlateInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new ChargeTickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new ContainerTickHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerSoulbound());
        MinecraftForge.EVENT_BUS.register(new EventHandlerVeinMiner());
        MinecraftForge.EVENT_BUS.register(new EventHandlerLuck());
        GameRegistry.registerFuelHandler(new FuelItemFuelHandler());
    }

    public void initWaila(){}

    public void registerRenderers() {
    }

    public void registerEntities() {
        EntityRegistry.registerModEntity(EntityLaunchingArrow.class, ModConstants.Entities.launchingArrowEntityName, ModConstants.Entities.lauchingArrowEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(EntitySpecialItem.class, ModConstants.Entities.specialItemEntityName, ModConstants.Entities.specialItemEntityId, ConvenientAdditions.INSTANCE, 128, 5, true);
    }

    public World getClientWorld() {
        return null;
    }

    public void InitModels() {}
}
