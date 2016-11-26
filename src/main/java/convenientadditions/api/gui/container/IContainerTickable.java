package convenientadditions.api.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

public interface IContainerTickable {
	void tickContainer(EntityPlayer p,Side side);
}
