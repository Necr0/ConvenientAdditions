package convenientadditions.init;

import convenientadditions.api.network.PacketExtendedExplosion;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.block.machine.autoWorkStation.MessageAutoWorkStation;
import convenientadditions.block.machine.jumpPad.MessageJumpPad;
import convenientadditions.block.machine.proximitySensor.MessageProximitySensor;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.api.network.PacketParticle;
import convenientadditions.block.misc.inventoryProxy.filtered.MessageInventoryProxyFiltered;
import convenientadditions.block.machine.setProvider.MessageSetProvider;
import convenientadditions.item.module.color.MessageColorChannelModule;
import convenientadditions.item.module.text.MessageTextChannelModule;
import convenientadditions.item.trinket.doubleJump.PacketDoubleJump;
import convenientadditions.item.relic.transmutationTome.MessageTransmutationTome;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModConstants.Mod.MODID);

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ConvenientAdditions.INSTANCE, new ModGuiHandler());
        int i = 0;
        INSTANCE.registerMessage(MessageSetProvider.class, MessageSetProvider.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageColorChannelModule.class, MessageColorChannelModule.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageInventoryProxyFiltered.class, MessageInventoryProxyFiltered.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageTransmutationTome.class, MessageTransmutationTome.class, i++, Side.CLIENT);
        INSTANCE.registerMessage(PacketExtendedExplosion.class, PacketExtendedExplosion.class, i++, Side.CLIENT);
        INSTANCE.registerMessage(PacketParticle.class, PacketParticle.class, i++, Side.CLIENT);
        INSTANCE.registerMessage(MessageJumpPad.class, MessageJumpPad.class, i++, Side.SERVER);
        INSTANCE.registerMessage(PacketDoubleJump.class, PacketDoubleJump.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageProximitySensor.class, MessageProximitySensor.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageAutoWorkStation.class, MessageAutoWorkStation.class, i++, Side.SERVER);
        INSTANCE.registerMessage(MessageTextChannelModule.class, MessageTextChannelModule.class, i++, Side.SERVER);
    }

    public static void spawnParticle(World w, EnumParticleTypes t, double x, double y, double z, double xs, double ys, double zs){
        INSTANCE.sendToAllAround(new PacketParticle(t,x,y,z,xs,ys,zs),new NetworkRegistry.TargetPoint(w.provider.getDimension(),x,y,z,64));
    }

    public static void spawnParticle(World w, EnumParticleTypes t, double x, double y, double z, double xs, double ys, double zs, int... params){
        INSTANCE.sendToAllAround(new PacketParticle(t,x,y,z,xs,ys,zs,params),new NetworkRegistry.TargetPoint(w.provider.getDimension(),x,y,z,64));
    }
}
