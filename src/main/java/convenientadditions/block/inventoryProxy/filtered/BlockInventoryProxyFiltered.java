package convenientadditions.block.inventoryProxy.filtered;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.block.inventoryProxy.BlockInventoryProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInventoryProxyFiltered extends BlockInventoryProxy {

    public BlockInventoryProxyFiltered() {
        super();
        this.setRegistryName(ModConstants.BlockNames.inventoryProxyFilteredBlockName).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxyFilteredBlockName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInventoryProxyFiltered();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_FILTERED_PROXY_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
