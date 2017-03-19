package convenientadditions.base.block;

import convenientadditions.api.block.IDismantleable;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CABlockMachine extends CABlockContainer implements IDismantleable {
    public CABlockMachine(String name, Material materialIn) {
        super(name,materialIn);
        this.setCategory(EnumItemCategory.MACHINE);
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        dropItems(world,pos);
        ItemStack stack = new ItemStack(this);
        world.spawnEntity(new EntityItem(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, stack));
        NonNullList<ItemStack> arr = NonNullList.create();
        arr.add(stack);
        world.setBlockToAir(pos);
        return arr;
    }

    public void dropItems(World world, BlockPos pos){}

    @Override
    public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    public void dropItemHandler(World world, BlockPos pos, ItemStackHandlerAutoSave handler, boolean clearHandler){
        for (ItemStack item : handler.getStacks()) {
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
        if(clearHandler)
            handler.setStacks(NonNullList.withSize(handler.getSlots(),ItemStack.EMPTY));
    }
}
