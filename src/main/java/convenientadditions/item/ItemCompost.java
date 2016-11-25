package convenientadditions.item;

import convenientadditions.api.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCompost extends Item {
    public ItemCompost() {
        super();
        this.setHasSubtypes(true).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.compostItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
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
                if (itemStack.getItemDamage() == 1 && world.rand.nextFloat() < ModConfig.composter_sporesMyceliumChance)
                    world.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                else
                    world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState(), 3);
            } else if (b == ModBlocks.compostSoilBlock || b == ModBlocks.compostSoilTilledBlock) {
                world.setBlockState(pos, b.getDefaultState(), 3 + 4);
            }
            itemStack.stackSize--;
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
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.compostItemName));
        if (stack.getItemDamage() == 1)
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.compostItemName + "Spores"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List<ItemStack> l) {
        l.add(new ItemStack(i, 1, 0));
        l.add(new ItemStack(i, 1, 1));
    }
}
