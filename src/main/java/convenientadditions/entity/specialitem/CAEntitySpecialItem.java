package convenientadditions.entity.specialitem;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import convenientadditions.api.entity.EntitySpecialItem;
import convenientadditions.api.network.PacketEntitySpecialItemBehaviours;
import convenientadditions.init.ModNetworking;
import convenientadditions.network.CAEntitySpecialItemBehavioursPacket;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CAEntitySpecialItem extends EntitySpecialItem {
	public CAEntitySpecialItem(World p_i1711_1_) {
		super(p_i1711_1_);
	}

	public CAEntitySpecialItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
		super(p_i1709_1_, p_i1709_2_, p_i1709_4_, p_i1709_6_);
	}

	public CAEntitySpecialItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
		super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
	}

	@Override
	public SimpleNetworkWrapper getSimpleNetworkWrapper() {
		return ModNetworking.INSTANCE;
	}

	@Override
	public PacketEntitySpecialItemBehaviours getCleanBehaviourPacket() {
		return new CAEntitySpecialItemBehavioursPacket();
	}
}
