package convenientadditions.block.itemTransmitter;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.block.inventoryProxy.BlockInventoryProxy;
import convenientadditions.init.ModGuiHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockItemTransmitter extends BlockInventoryProxy {
	public BlockItemTransmitter() {
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.itemTransmitterBlockName);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemTransmitter();
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote)
    		player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_ITEM_TRANSMITTER_ID, world, pos.getX(), pos.getY(), pos.getZ());
    	return true;
    }
}
