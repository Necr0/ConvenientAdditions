package convenientadditions.item.charge.enderPlate;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.item.charge.ItemChargeable;
import convenientadditions.block.technical.BlockPhantomPlatform;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemEnderPlate extends ItemChargeable implements IPlayerInventoryTick {
    public static ItemStack FULLY_CHARGED;

    public ItemEnderPlate() {
        super(96000, true, true);//8
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.enderPlateItemName)
                .setCreativeTab(ConvenientAdditions.CREATIVETAB)
                .setHasSubtypes(true)
                .setMaxStackSize(1)
                .setRegistryName(ModConstants.ItemNames.enderPlateItemName);
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
    @SideOnly(Side.CLIENT)
    public int consumeCharge(ItemStack item, int amount) {
        int ret = super.consumeCharge(item, amount);
        if (this.getCharge(item) == 0)
            item.setItemDamage(0);
        return ret;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }

    public boolean isActive(ItemStack stack) {
        return (getCharge(stack)) > 0 && stack.getItemDamage() == 1;
    }

    @Override
    public void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player) {
        if (player.getEntityWorld().isRemote || !slot.isCommonActive())
            return;
        WorldServer world = (WorldServer) player.getEntityWorld();
        if (isActive(item)) {
            consumeCharge(item, 6);
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
        list.add(StringHelper.getJoke(stack));
        list.add(StringHelper.getInfo(stack));
        super.addInformation(stack, player, list, par4);
        list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":enderPlateDrained"));
        if (isActive(stack))
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":enderPlateActive"));
        else
            list.add(StringHelper.getHint("tooltip." + ModConstants.Mod.MODID + ":enderPlateInactive"));
    }
}
