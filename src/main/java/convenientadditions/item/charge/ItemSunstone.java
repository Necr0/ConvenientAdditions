package convenientadditions.item.charge;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSunstone extends ItemSunlightChargeableBehaviour implements IPlayerInventoryTick {
    public static ItemStack FULLY_CHARGED;

    public ItemSunstone() {
        super(60000, true, true, 20);
        this.setHasSubtypes(true)
                .setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstoneItemName)
                .setCreativeTab(ConvenientAdditions.CREATIVETAB)
                .setHasSubtypes(true)
                .setMaxStackSize(1);
        FULLY_CHARGED = new ItemStack(this, 1, 0);
        chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack=player.getHeldItem(hand);
        int dmg = itemStack.getItemDamage();
        if (!world.isRemote)
            if (dmg == 0 && getCharge(itemStack) != 0) {
                itemStack.setItemDamage(1);
            } else
                itemStack.setItemDamage(0);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstoneItemName));
        super.addInformation(stack, player, list, par4);
        if (isActive(stack))
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstoneItemName + "Active"));
        else
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.sunstoneItemName + "Inactive"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int consumeCharge(ItemStack item, int amount) {
        int ret = super.consumeCharge(item, amount);
        if (this.getCharge(item) == 0)
            item.setItemDamage(0);
        return ret;
    }

    public boolean isActive(ItemStack stack) {
        return (getCharge(stack)) > 0 && stack.getItemDamage() == 1;
    }

    @Override
    public void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player) {
        if (player.getEntityWorld().isRemote)
            return;
        WorldServer world = (WorldServer) player.getEntityWorld();
        if (isActive(item)) {
            consumeCharge(item, 1);
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
