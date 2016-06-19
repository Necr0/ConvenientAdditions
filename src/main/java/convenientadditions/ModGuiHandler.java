package convenientadditions;

import convenientadditions.block.setProvider.ContainerSetProvider;
import convenientadditions.block.setProvider.GuiSetProvider;
import convenientadditions.block.setProvider.TileEntitySetProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	public static final int GUI_SET_PROVIDER_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GUI_SET_PROVIDER_ID:
			return new ContainerSetProvider((TileEntitySetProvider)world.getTileEntity(new BlockPos(x, y, z)),player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
			case GUI_SET_PROVIDER_ID:
				return new GuiSetProvider(new ContainerSetProvider((TileEntitySetProvider)world.getTileEntity(new BlockPos(x, y, z)),player));
			default:
				return null;
		}
	}

}
