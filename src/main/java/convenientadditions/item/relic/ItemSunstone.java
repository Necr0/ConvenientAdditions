package convenientadditions.item.relic;

import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
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

import java.util.List;

public class ItemSunstone extends CAItem implements IPlayerInventoryTick {

    public ItemSunstone() {
        super(ModConstants.ItemNames.sunstone);
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


    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (isActive(stack))
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstone + ".active"));
        else
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstone + ".inactive"));
    }

    public boolean isActive(ItemStack stack) {
        return stack.getItemDamage() == 1;
    }

    @Override
    public void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player) {
        if (player.getEntityWorld().isRemote)
            return;
        World world = player.getEntityWorld();
        if (isActive(item)) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    for (int z = 0; z < 9; z++) {
                        if(world.rand.nextBoolean()) {
                            BlockPos pos = new BlockPos(x - 4 + (int) player.posX, y - 4 + (int) player.posY, z - 4 + (int) player.posZ);
                            IBlockState state = world.getBlockState(pos);
                            Block b = state.getBlock();
                            if (b.isAir(state, world, pos) && b != ModBlocks.tempLightBlock && b != ModBlocks.phantomPlatformBlock) {
                                world.setBlockState(pos, ModBlocks.tempLightBlock.getDefaultState(), 3);
                                world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 80 + world.rand.nextInt(140), 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
