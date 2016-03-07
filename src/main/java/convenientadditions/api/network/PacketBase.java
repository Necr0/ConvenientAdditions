package convenientadditions.api.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;

public abstract class PacketBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ> {}
