package convenientadditions.api.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public abstract class PacketBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ> {}
