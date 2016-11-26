package convenientadditions.block.setProvider;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.ModGuiHandler;
import convenientadditions.api.block.BlockMachineConfigurable;
import convenientadditions.block.setProvider.TileEntitySetProvider.EnumOutletMode;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSetProvider extends BlockMachineConfigurable {

    public static final PropertyEnum<EnumOutletMode> OUTLET_TOP = PropertyEnum.<EnumOutletMode>create("outlet_top", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_BOTTOM = PropertyEnum.<EnumOutletMode>create("outlet_bottom", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_NORTH = PropertyEnum.<EnumOutletMode>create("outlet_north", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_EAST = PropertyEnum.<EnumOutletMode>create("outlet_east", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_SOUTH = PropertyEnum.<EnumOutletMode>create("outlet_south", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_WEST = PropertyEnum.<EnumOutletMode>create("outlet_west", EnumOutletMode.class);

    public BlockSetProvider() {
        super(Material.IRON);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.setProviderBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OUTLET_TOP, EnumOutletMode.disabled).withProperty(OUTLET_BOTTOM, EnumOutletMode.disabled).withProperty(OUTLET_NORTH, EnumOutletMode.disabled).withProperty(OUTLET_EAST, EnumOutletMode.disabled).withProperty(OUTLET_SOUTH, EnumOutletMode.disabled).withProperty(OUTLET_WEST, EnumOutletMode.disabled));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySetProvider();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_SET_PROVIDER_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos from) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (t == null || worldIn.isRemote)
            return;
        if (t instanceof TileEntitySetProvider) {
            TileEntitySetProvider te = (TileEntitySetProvider) t;
            te.updateRS(te.getWorld().isBlockIndirectlyGettingPowered(pos) > 0);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        IBlockState ret = state;
        if (t != null && t instanceof TileEntitySetProvider) {
            TileEntitySetProvider s = (TileEntitySetProvider) t;
            ret = ret.withProperty(OUTLET_TOP, s.outletSides.get(EnumFacing.UP));
            ret = ret.withProperty(OUTLET_BOTTOM, s.outletSides.get(EnumFacing.DOWN));
            ret = ret.withProperty(OUTLET_NORTH, s.outletSides.get(EnumFacing.NORTH));
            ret = ret.withProperty(OUTLET_EAST, s.outletSides.get(EnumFacing.EAST));
            ret = ret.withProperty(OUTLET_SOUTH, s.outletSides.get(EnumFacing.SOUTH));
            ret = ret.withProperty(OUTLET_WEST, s.outletSides.get(EnumFacing.WEST));
        }
        return ret;
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, OUTLET_TOP, OUTLET_BOTTOM, OUTLET_NORTH, OUTLET_EAST, OUTLET_SOUTH, OUTLET_WEST);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        dropItems(world, pos);
        return super.dismantleBlock(player, world, pos, returnDrops);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    private void dropItems(World world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntitySetProvider && !world.isRemote) {
            TileEntitySetProvider p = (TileEntitySetProvider) world.getTileEntity(pos);
            for (ItemStack item : p.input.getStacks()) {
                if (item != null) {
                    float rx = world.rand.nextFloat() * 0.8F + 0.1F;
                    float ry = world.rand.nextFloat() * 0.8F + 0.1F;
                    float rz = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, item);
                    float factor = 0.05F;
                    entityItem.motionX = world.rand.nextGaussian() * factor;
                    entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
                    entityItem.motionZ = world.rand.nextGaussian() * factor;
                    world.spawnEntity(entityItem);
                }
            }
            p.input.setStacks(NonNullList.withSize(p.input.getSlots(),ItemStack.EMPTY));
            for (ItemStack item : p.filter.getStacks()) {
                if (item != null) {
                    float rx = world.rand.nextFloat() * 0.8F + 0.1F;
                    float ry = world.rand.nextFloat() * 0.8F + 0.1F;
                    float rz = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, item);
                    float factor = 0.05F;
                    entityItem.motionX = world.rand.nextGaussian() * factor;
                    entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
                    entityItem.motionZ = world.rand.nextGaussian() * factor;
                    world.spawnEntity(entityItem);
                }
            }
            p.filter.setStacks(NonNullList.withSize(p.input.getSlots(),ItemStack.EMPTY));
            for (ItemStack item : p.output.getStacks()) {
                if (item != null) {
                    float rx = world.rand.nextFloat() * 0.8F + 0.1F;
                    float ry = world.rand.nextFloat() * 0.8F + 0.1F;
                    float rz = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, item);
                    float factor = 0.05F;
                    entityItem.motionX = world.rand.nextGaussian() * factor;
                    entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
                    entityItem.motionZ = world.rand.nextGaussian() * factor;
                    world.spawnEntity(entityItem);
                }
            }
            p.output.setStacks(NonNullList.withSize(p.input.getSlots(),ItemStack.EMPTY));
        }
    }
}
