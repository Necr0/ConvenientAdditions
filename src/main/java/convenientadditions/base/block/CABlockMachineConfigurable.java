package convenientadditions.base.block;

import convenientadditions.api.block.tileentity.IConfigurable;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CABlockMachineConfigurable extends CABlockMachine {
    public CABlockMachineConfigurable(String name, Material materialIn) {
        super(name,materialIn);
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        dropItems(world,pos);
        return super.dismantleBlock(player,world,pos,returnDrops);
    }

    public void dropItems(World world, BlockPos pos){}

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

    @Override
    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing axis) {
        TileEntity te = worldObj.getTileEntity(pos);
        if (te != null && te instanceof IConfigurable) {
            return ((IConfigurable) te).configureSide(axis);
        }
        return super.rotateBlock(worldObj, pos, axis);
    }
}
