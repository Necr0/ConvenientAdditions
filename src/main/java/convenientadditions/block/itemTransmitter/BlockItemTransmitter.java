package convenientadditions.block.itemTransmitter;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.block.inventoryProxy.BlockInventoryProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockItemTransmitter extends BlockInventoryProxy implements IDismantleable {
    public BlockItemTransmitter() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.itemTransmitterBlockName).setRegistryName(ModConstants.BlockNames.itemTransmitterBlockName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityItemTransmitter();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_ITEM_TRANSMITTER_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    private void dropItems(World world, BlockPos pos) {
        if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityItemTransmitter && !world.isRemote) {
            TileEntityItemTransmitter p = (TileEntityItemTransmitter) world.getTileEntity(pos);
            for (ItemStack item : p.channels.getStacks()) {
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
            p.channels.setStacks(NonNullList.withSize(p.channels.getSlots(),ItemStack.EMPTY));
        }
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
        return true;
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        dropItems(world, pos);
        ItemStack stack = new ItemStack(this);
        world.spawnEntity(new EntityItem(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, stack));
        NonNullList<ItemStack> arr = NonNullList.create();
        arr.add(stack);
        world.setBlockToAir(pos);
        return arr;
    }
}
