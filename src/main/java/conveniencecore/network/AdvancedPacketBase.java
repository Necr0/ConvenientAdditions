package conveniencecore.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public abstract class AdvancedPacketBase<REQ extends IMessage,REP extends IMessage> implements IMessage, IMessageHandler<REQ, REP> {}
