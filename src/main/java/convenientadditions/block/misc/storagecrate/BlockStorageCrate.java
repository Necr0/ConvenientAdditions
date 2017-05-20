package convenientadditions.block.misc.storagecrate;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.CABlockContainer;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Necro on 5/7/2017.
 */
public class BlockStorageCrate extends CABlockContainer {

    public BlockStorageCrate() {
        super(ModConstants.BlockNames.storageCrate, Material.WOOD);
        this.setSoundType(SoundType.WOOD).setCategory(EnumItemCategory.MISC).setHardness(5F).setResistance(2F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityStorageCrate();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_STORAGE_CRATE_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityStorageCrate) {
            dropItemHandler(world,pos,((TileEntityStorageCrate) te).inv,true);
        }
    }
}
