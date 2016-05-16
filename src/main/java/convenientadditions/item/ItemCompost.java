package convenientadditions.item;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCompost extends Item implements IModelResourceLocationProvider {
	public ItemCompost(){
		super();
		this.setHasSubtypes(true).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		Block b=world.getBlockState(pos).getBlock();
		if(!(b==Blocks.dirt||b==Blocks.farmland||b==Blocks.grass||((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&world.getBlockState(pos)METADATA!=0)))
    		return false;
		if(!world.isRemote){
			if(b==Blocks.dirt)
				world.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(0), 3);
			else if(b==Blocks.farmland)
				world.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getStateFromMeta(0), 3);
			else if(b==Blocks.grass){
				if(itemStack.getItemDamage()==1&&new Random().nextInt(64)==0)
					world.setBlockState(pos, Blocks.mycelium.getStateFromMeta(0), 3);
				else
					world.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(0), 3);
			}else if(b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock){
				world.setBlockState(METADATA 0, 2+4);
			}
			itemStack.stackSize--;
		}
        world.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), Blocks.grass.stepSound.getStepSound(), (Blocks.grass.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.grass.stepSound.getFrequency() * 0.8F);
		return true;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip."+ConvenientAdditionsMod.MODID+":"+Reference.compostItemName));
		if(stack.getItemDamage()==1)
			list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip."+ConvenientAdditionsMod.MODID+":"+Reference.compostItemName+"Spores"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(new ItemStack(i, 1, 1));
    }
}
