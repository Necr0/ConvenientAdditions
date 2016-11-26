package convenientadditions.entity.behaviour;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BehaviourCompost implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) {
        return true;
    }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w = item.getEntityWorld();
        BlockPos pos = new BlockPos(MathHelper.floor(item.posX), MathHelper.floor(item.posY) - 1, MathHelper.floor(item.posZ));
        IBlockState state = w.getBlockState(new BlockPos(pos));
        Block b = state.getBlock();
        if (item.onGround) {
            if (!(b == Blocks.DIRT || b == Blocks.FARMLAND || b == Blocks.GRASS || ((b == ModBlocks.compostSoilBlock || b == ModBlocks.compostSoilTilledBlock) && ModBlocks.compostSoilTilledBlock.getMetaFromState(state) != 0)))
                return;
            Item i = item.getEntityItem().getItem();
            if (i != ModItems.itemCompost)
                return;
            if (b == Blocks.DIRT || b == Blocks.GRASS)
                w.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState(), 2);
            else if (b == Blocks.FARMLAND)
                w.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getDefaultState(), 2);
            else if (b == ModBlocks.compostSoilBlock || b == ModBlocks.compostSoilTilledBlock)
                w.setBlockState(pos, b.getDefaultState(), 2 + 4);
            else
                return;
            item.getEntityItem().shrink(1);
            if (item.getEntityItem().isEmpty())
                item.setDead();
        }
    }

}
