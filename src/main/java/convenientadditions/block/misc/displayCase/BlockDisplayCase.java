package convenientadditions.block.misc.displayCase;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.CABlockContainer;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by Necro on 3/9/2017.
 */
public class BlockDisplayCase extends CABlockContainer {
    public static final PropertyDirection FACING = BlockDirectional.FACING;

    public BlockDisplayCase() {
        super(ModConstants.BlockNames.displayCase, Material.GLASS);
        this.setSoundType(SoundType.GLASS).setCategory(EnumItemCategory.MISC).setHardness(2F).setResistance(.5F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack current = player.getHeldItem(hand);
        TileEntity tileEntity=world.getTileEntity(pos);
        if (!world.isRemote && tileEntity!=null && tileEntity instanceof TileEntityDisplayCase) {
            TileEntityDisplayCase te= (TileEntityDisplayCase) tileEntity;
            if(!current.isEmpty() && te.inventory.getStackInSlot(0).isEmpty()){
                player.setHeldItem(hand,te.inventory.insertItem(0,current,false));
            }
        }
        return true;
    }

    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player){
        TileEntity tileEntity=world.getTileEntity(pos);
        if (!world.isRemote && tileEntity!=null && tileEntity instanceof TileEntityDisplayCase) {
            Helper.insertOrDrop(player,((TileEntityDisplayCase)tileEntity).inventory.extractItem(0,1,false));
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityDisplayCase();
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityDisplayCase) {
            dropItemHandler(world,pos,((TileEntityDisplayCase) te).inventory,true);
        }
    }

    //BLOCKSTATE

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
}
