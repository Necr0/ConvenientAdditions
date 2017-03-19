package convenientadditions.block.machine;

import convenientadditions.ModConstants;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.base.block.CABlock;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineBlock extends CABlock implements IDismantleable {
    public BlockMachineBlock() {
        super(ModConstants.BlockNames.machineBlock,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultInfo(false);
        this.setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
        return true;
    }

    @Override
    public NonNullList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops) {
        ItemStack stack = new ItemStack(this);
        world.spawnEntity(new EntityItem(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, stack));
        NonNullList<ItemStack> arr = NonNullList.create();
        arr.add(stack);
        world.setBlockToAir(pos);
        return arr;
    }
}
