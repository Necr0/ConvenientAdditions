package convenientadditions.handler;

import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.block.machine.autoWorkStation.ContainerAutoWorkStation;
import convenientadditions.block.machine.autoWorkStation.GuiAutoWorkStation;
import convenientadditions.block.machine.autoWorkStation.TileEntityAutoWorkStation;
import convenientadditions.block.machine.ironFarm.ContainerIronFarm;
import convenientadditions.block.machine.ironFarm.TileEntityIronFarm;
import convenientadditions.block.machine.itemReceiver.ContainerItemReceiver;
import convenientadditions.block.machine.itemReceiver.TileEntityItemReceiver;
import convenientadditions.block.machine.itemTransmitter.ContainerItemTransmitter;
import convenientadditions.block.machine.itemTransmitter.TileEntityItemTransmitter;
import convenientadditions.block.machine.jumpPad.ContainerJumpPad;
import convenientadditions.block.machine.jumpPad.GuiJumpPad;
import convenientadditions.block.machine.jumpPad.TileEntityJumpPad;
import convenientadditions.block.machine.proximitySensor.GuiProximitySensor;
import convenientadditions.block.machine.proximitySensor.TileEntityProximitySensor;
import convenientadditions.block.machine.remoteInventoryProxy.ContainerRemoteInventoryProxy;
import convenientadditions.block.machine.remoteInventoryProxy.TileEntityRemoteInventoryProxy;
import convenientadditions.block.machine.setProvider.ContainerSetProvider;
import convenientadditions.block.machine.setProvider.GuiSetProvider;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider;
import convenientadditions.block.misc.inventoryProxy.filtered.ContainerInventoryProxyFiltered;
import convenientadditions.block.misc.inventoryProxy.filtered.GuiInventoryProxyFiltered;
import convenientadditions.block.misc.inventoryProxy.filtered.TileEntityInventoryProxyFiltered;
import convenientadditions.block.misc.storagecrate.ContainerStorageCrate;
import convenientadditions.block.misc.storagecrate.TileEntityStorageCrate;
import convenientadditions.block.misc.workStation.ContainerWorkStation;
import convenientadditions.block.misc.workStation.GuiWorkStation;
import convenientadditions.block.misc.workStation.TileEntityWorkStation;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.item.misc.backpack.ContainerBackpack;
import convenientadditions.item.module.color.GuiColorChannelModule;
import convenientadditions.item.module.text.GuiTextChannelModule;
import convenientadditions.item.relic.transmutationTome.ContainerTransmutationTome;
import convenientadditions.item.relic.transmutationTome.GuiTransmutationTome;
import convenientadditions.item.relic.transmutationTome.GuiTransmutationTomeRecipeLookup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
    public static final int GUI_SET_PROVIDER_ID = 0;
    public static final int GUI_COLOR_MODULE_ID = 1;
    public static final int GUI_ITEM_TRANSMITTER_ID = 2;
    public static final int GUI_ITEM_RECEIVER_ID = 3;
    public static final int GUI_FILTERED_PROXY_ID = 4;
    public static final int GUI_TRANSMUTATION_TOME_ID = 5;
    public static final int GUI_TRANSMUTATION_TOME_LOOKUP_ID = 6;
    public static final int GUI_JUMP_PAD_ID = 7;
    public static final int GUI_REMOTE_PROXY_ID = 8;
    public static final int GUI_PROXIMITY_SENSOR_ID = 9;
    public static final int GUI_IRON_FARM_ID = 10;
    public static final int GUI_WORK_STATION_ID = 11;
    public static final int GUI_AUTO_WORK_STATION_ID = 12;
    public static final int GUI_STORAGE_CRATE_ID = 13;
    public static final int GUI_BACKPACK_ID = 14;
    public static final int GUI_TEXT_MODULE_ID = 15;


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_SET_PROVIDER_ID:
                return new ContainerSetProvider((TileEntitySetProvider) world.getTileEntity(new BlockPos(x, y, z)), player);
            case GUI_ITEM_TRANSMITTER_ID:
                return new ContainerItemTransmitter((TileEntityItemTransmitter) world.getTileEntity(new BlockPos(x, y, z)), player);
            case GUI_ITEM_RECEIVER_ID:
                return new ContainerItemReceiver((TileEntityItemReceiver) world.getTileEntity(new BlockPos(x, y, z)), player);
            case GUI_FILTERED_PROXY_ID:
                return new ContainerInventoryProxyFiltered((TileEntityInventoryProxyFiltered) world.getTileEntity(new BlockPos(x, y, z)), player);
            case GUI_TRANSMUTATION_TOME_ID:
                return new ContainerTransmutationTome(player);
            case GUI_JUMP_PAD_ID:
                return new ContainerJumpPad((TileEntityJumpPad) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_REMOTE_PROXY_ID:
                return new ContainerRemoteInventoryProxy((TileEntityRemoteInventoryProxy) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_IRON_FARM_ID:
                return new ContainerIronFarm((TileEntityIronFarm) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_WORK_STATION_ID:
                return new ContainerWorkStation((TileEntityWorkStation) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_AUTO_WORK_STATION_ID:
                return new ContainerAutoWorkStation((TileEntityAutoWorkStation) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_STORAGE_CRATE_ID:
                return new ContainerStorageCrate((TileEntityStorageCrate) world.getTileEntity(new BlockPos(x,y,z)),player);
            case GUI_BACKPACK_ID:
                return new ContainerBackpack(player);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_SET_PROVIDER_ID:
                return new GuiSetProvider(new ContainerSetProvider((TileEntitySetProvider) world.getTileEntity(new BlockPos(x, y, z)), player));
            case GUI_COLOR_MODULE_ID:
                return new GuiColorChannelModule(EnumHand.values()[x] == EnumHand.MAIN_HAND);
            case GUI_ITEM_TRANSMITTER_ID:
                return new CAGuiContainer(new ContainerItemTransmitter((TileEntityItemTransmitter) world.getTileEntity(new BlockPos(x, y, z)), player), ModImageResourceLocations.GUI_ITEM_TRANSMITTER);
            case GUI_ITEM_RECEIVER_ID:
                return new CAGuiContainer(new ContainerItemReceiver((TileEntityItemReceiver) world.getTileEntity(new BlockPos(x, y, z)), player), ModImageResourceLocations.GUI_ITEM_RECEIVER);
            case GUI_FILTERED_PROXY_ID:
                return new GuiInventoryProxyFiltered(new ContainerInventoryProxyFiltered((TileEntityInventoryProxyFiltered) world.getTileEntity(new BlockPos(x, y, z)), player));
            case GUI_TRANSMUTATION_TOME_ID:
                return new GuiTransmutationTome(new ContainerTransmutationTome(player),true);
            case GUI_TRANSMUTATION_TOME_LOOKUP_ID:
                return new GuiTransmutationTomeRecipeLookup(x);
            case GUI_JUMP_PAD_ID:
                return new GuiJumpPad(new ContainerJumpPad((TileEntityJumpPad) world.getTileEntity(new BlockPos(x,y,z)),player));
            case GUI_PROXIMITY_SENSOR_ID:
                return new GuiProximitySensor((TileEntityProximitySensor)world.getTileEntity(new BlockPos(x,y,z)));
            case GUI_REMOTE_PROXY_ID:
                return new CAGuiContainer(new ContainerRemoteInventoryProxy((TileEntityRemoteInventoryProxy) world.getTileEntity(new BlockPos(x,y,z)),player),ModImageResourceLocations.GUI_REMOTE_INVENTORY_PROXY);
            case GUI_IRON_FARM_ID:
                return new CAGuiContainer(new ContainerIronFarm((TileEntityIronFarm) world.getTileEntity(new BlockPos(x,y,z)),player), ModImageResourceLocations.GUI_IRON_FARM);
            case GUI_WORK_STATION_ID:
                return new GuiWorkStation(new ContainerWorkStation((TileEntityWorkStation) world.getTileEntity(new BlockPos(x,y,z)),player));
            case GUI_AUTO_WORK_STATION_ID:
                return new GuiAutoWorkStation(new ContainerAutoWorkStation((TileEntityAutoWorkStation) world.getTileEntity(new BlockPos(x,y,z)),player));
            case GUI_STORAGE_CRATE_ID:
                return new CAGuiContainer(new ContainerStorageCrate((TileEntityStorageCrate) world.getTileEntity(new BlockPos(x,y,z)),player), ModImageResourceLocations.GUI_STORAGE_CRATE);
            case GUI_BACKPACK_ID:
                return new CAGuiContainer(new ContainerBackpack(player), ModImageResourceLocations.GUI_BACKPACK);
            case GUI_TEXT_MODULE_ID:
                return new GuiTextChannelModule(player.getHeldItem(EnumHand.values()[x]),EnumHand.values()[x] == EnumHand.MAIN_HAND);
            default:
                return null;
        }
    }

}
