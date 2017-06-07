package convenientadditions.block.misc.composter;

import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockComposter extends CABlockContainer {

    public BlockComposter() {
        super(ModConstants.BlockNames.composter,Material.WOOD);
        this.setHardness(2F).setResistance(3F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.getTileEntity(pos) instanceof TileEntityComposter && !player.isSneaking()) {
            TileEntityComposter t = (TileEntityComposter) world.getTileEntity(pos);
            ItemStack held = player.getHeldItem(hand);
            if (!held.isEmpty()) {
                ItemStack insert = held.splitStack(1);
                insert = t.insertStack(insert);
                if (!insert.isEmpty())
                    held.grow(insert.getCount());
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityComposter();
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return side != EnumFacing.UP;
    }


    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
