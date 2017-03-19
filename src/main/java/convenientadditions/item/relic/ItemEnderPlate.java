package convenientadditions.item.relic;

import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.block.technical.BlockPhantomPlatform;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class ItemEnderPlate extends CAItem implements IPlayerInventoryTick {

    public ItemEnderPlate() {
        super(ModConstants.ItemNames.enderPlate);
        this.setDefaultJoke(true).setMaxStackSize(1);
        this.setCategory(EnumItemCategory.RELIC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack=player.getHeldItem(hand);
        int dmg = itemStack.getItemDamage();
        if (!world.isRemote)
            if (dmg == 0) {
                itemStack.setItemDamage(1);
            } else
                itemStack.setItemDamage(0);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    public boolean isActive(ItemStack stack) {
        return stack.getItemDamage() == 1;
    }

    @Override
    public void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player) {
        if (player.getEntityWorld().isRemote || !slot.isCommonActive())
            return;
        WorldServer world = (WorldServer) player.getEntityWorld();
        if (isActive(item)) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        BlockPos player_pos = player.getPosition();
                        BlockPos pos = new BlockPos(x - 1 + player_pos.getX(), y - 2 + player_pos.getY(), z - 1 + player_pos.getZ());
                        IBlockState state = world.getBlockState(pos);
                        Block b = state.getBlock();
                        if (b.isAir(state, world, pos) && b != ModBlocks.phantomPlatformBlock) {
                            world.setBlockState(pos, ModBlocks.phantomPlatformBlock.getDefaultState().withProperty(BlockPhantomPlatform.DESPAWN, true), 3 + 4);
                            world.scheduleBlockUpdate(pos, ModBlocks.phantomPlatformBlock, 1, 0);
                        }
                    }
                }
            }
        }
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (isActive(stack))
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.enderPlate + ".active"));
        else
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.enderPlate + ".inactive"));
    }
}
