package convenientadditions.base.block.block;

import convenientadditions.api.block.IDismantleable;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.material.Material;
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

    @Override
    public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
        return true;
    }
}
