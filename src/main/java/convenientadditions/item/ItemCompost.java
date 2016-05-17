package convenientadditions.item;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.init.ModBlocks;
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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCompost extends Item implements IModelResourceLocationProvider {
	public ItemCompost(){
		super();
		this.setHasSubtypes(true).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		IBlockState state=world.getBlockState(pos);
		Block b=state.getBlock();
		if(!(b==Blocks.dirt||b==Blocks.farmland||b==Blocks.grass||((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&b.getMetaFromState(state)!=0)))
    		return EnumActionResult.FAIL;
		if(!world.isRemote){
			if(b==Blocks.dirt)
				world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState(), 3);
			else if(b==Blocks.farmland)
				world.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getDefaultState(), 3);
			else if(b==Blocks.grass){
				if(itemStack.getItemDamage()==1&&new Random().nextInt(64)==0)
					world.setBlockState(pos, Blocks.mycelium.getDefaultState(), 3);
				else
					world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState(), 3);
			}else if(b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock){
				world.setBlockState(pos,b.getDefaultState(), 2+4);
			}
			itemStack.stackSize--;
		}
        world.playSound(
        		(double)((float)pos.getX() + 0.5F),
        		(double)((float)pos.getY() + 0.5F),
        		(double)((float)pos.getZ() + 0.5F),
        		Blocks.grass.getStepSound().getStepSound(),
        		SoundCategory.BLOCKS,
        		(Blocks.grass.getStepSound().getVolume() + 1.0F) / 2.0F,
        		Blocks.grass.getStepSound().getPitch() * 0.8F,
        		false);
		return EnumActionResult.SUCCESS;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(I18n.translateToLocal("tooltip."+ConvenientAdditionsMod.MODID+":"+Reference.compostItemName));
		if(stack.getItemDamage()==1)
			list.add(TextFormatting.DARK_GRAY+I18n.translateToLocal("tooltip."+ConvenientAdditionsMod.MODID+":"+Reference.compostItemName+"Spores"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(new ItemStack(i, 1, 1));
    }
}
