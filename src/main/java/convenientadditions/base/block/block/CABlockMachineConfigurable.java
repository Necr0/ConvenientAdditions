package convenientadditions.base.block.block;

import convenientadditions.api.block.tileentity.IConfigurable;
import net.minecraft.block.material.Material;
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

    @Override
    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing axis) {
        TileEntity te = worldObj.getTileEntity(pos);
        if (te != null && te instanceof IConfigurable) {
            return ((IConfigurable) te).configureSide(axis);
        }
        return super.rotateBlock(worldObj, pos, axis);
    }
}
