package convenientadditions.item.tools;

import convenientadditions.ModConstants;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.base.CAItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIronWrench extends CAItem {
    public ItemIronWrench() {
        super(ModConstants.ItemNames.ironWrenchItemName);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        Block b = world.getBlockState(pos).getBlock();
        if (b != null) {
            if (!player.isSneaking()) {
                if (!world.isRemote) {
                    b.rotateBlock(world, pos, side);
                    return EnumActionResult.SUCCESS;
                }
                player.swingArm(hand);
                return EnumActionResult.PASS;
            } else {
                if (b instanceof IDismantleable) {
                    IDismantleable d = (IDismantleable) b;
                    if (d.canDismantle(player, world, pos) && !world.isRemote) {
                        d.dismantleBlock(player, world, pos, false);
                        return EnumActionResult.SUCCESS;
                    }
                    player.swingArm(hand);
                    return EnumActionResult.PASS;
                }
            }
        }
        return EnumActionResult.FAIL;
    }
}
