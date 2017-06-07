package convenientadditions.base.block.tileentity;

import convenientadditions.base.CAContainer;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CAContainerTileEntity extends CAContainer {
	public static final double MAX_DISTANCE=Math.pow(15,2);

	public CATileEntity tile;

	public CAContainerTileEntity(CATileEntity tile){
		super();
		this.tile=tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tile.getPos().distanceSq(playerIn.getPosition()) <= MAX_DISTANCE;
	}
}
