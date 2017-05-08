package convenientadditions.base.block;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class CABlockContainer extends CABlock {

    public CABlockContainer(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(ConvenientAdditions.CREATIVETAB);
    }

    public CABlockContainer(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name);
    }

    @Override
    public boolean hasTileEntity(IBlockState state){ return true; }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    public void dropItems(World world, BlockPos pos){}

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
