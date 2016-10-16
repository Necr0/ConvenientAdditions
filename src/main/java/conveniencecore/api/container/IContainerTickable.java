package conveniencecore.api.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

public interface IContainerTickable {
	public void tickContainer(EntityPlayer p,Side side);
}
