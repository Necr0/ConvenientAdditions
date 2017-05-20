package convenientadditions.item.consumable;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.config.ModConfigMisc;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCompost extends CAItem {
    public ItemCompost() {
        super(ModConstants.ItemNames.compost);
        this.setHasSubtypes(true);
        this.setCategory(EnumItemCategory.CONSUMABLE);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        IBlockState state = world.getBlockState(pos);
        Block b = state.getBlock();
        if (!(b == Blocks.DIRT || b == Blocks.FARMLAND || b == Blocks.GRASS || ((b == ModBlocks.compostSoilBlock || b == ModBlocks.compostSoilTilledBlock) && b.getMetaFromState(state) != 0)))
            return EnumActionResult.FAIL;
        if (!world.isRemote) {
            if (b == Blocks.DIRT)
                world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState());
            else if (b == Blocks.FARMLAND)
                world.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getDefaultState());
            else if (b == Blocks.GRASS) {
                if (player.getHeldItem(hand).getItemDamage() == 1 && world.rand.nextFloat() < ModConfigMisc.composter_sporesMyceliumChance)
                    world.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                else
                    world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState(), 3);
            } else if (b == ModBlocks.compostSoilBlock || b == ModBlocks.compostSoilTilledBlock) {
                world.setBlockState(pos, b.getDefaultState(), 3 + 4);
            }
            player.getHeldItem(hand).shrink(1);
        }
        world.playSound(null,
                pos,
                Blocks.GRASS.getSoundType().getHitSound(),
                SoundCategory.BLOCKS,
                Blocks.GRASS.getSoundType().getVolume(),
                Blocks.GRASS.getSoundType().getPitch());
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
        super.addInformation(stack,player,list,advanced);
        if (stack.getItemDamage() == 1)
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.compost + ".spores"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(new ItemStack(i, 1, 1));
    }
}
