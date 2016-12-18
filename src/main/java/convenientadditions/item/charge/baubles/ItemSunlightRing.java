package convenientadditions.item.charge.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.base.CAItemSunlightChargeable;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemSunlightRing extends CAItemSunlightChargeable implements IBauble {
    public static ItemStack FULLY_CHARGED;

    public ItemSunlightRing() {
        super(ModConstants.ItemNames.sunlightRingItemName, 60000, true, true, 21);
        FULLY_CHARGED = new ItemStack(this, 1, 0);
        chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        if (ModItems.itemSunlightRing.getCharge(itemstack) > 0) {
            WorldServer world = (WorldServer) player.getEntityWorld();
            Random random = new Random();
            consumeCharge(itemstack, 1);
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    for (int z = 0; z < 9; z++) {
                        BlockPos pos = new BlockPos(x - 4 + (int) player.posX, y - 4 + (int) player.posY, z - 4 + (int) player.posZ);
                        IBlockState state = world.getBlockState(pos);
                        Block b = state.getBlock();
                        if (b.isAir(state, world, pos) && b != ModBlocks.tempLightBlock) {
                            world.setBlockState(pos, ModBlocks.tempLightBlock.getDefaultState(), 3);
                            world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 20 + random.nextInt(20), 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(StringHelper.getJoke(stack));
        super.addInformation(stack, player, list, par4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
}
