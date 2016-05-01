package convenientadditions.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.init.ModBlocks;

public class ItemCompost extends Item {
	public ItemCompost(){
		super();
		this.setHasSubtypes(true).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int no_idea, float no_clue, float what, float are_these)
    {
		Block b=world.getBlock(x, y, z);
		if(!(b==Blocks.dirt||b==Blocks.farmland||b==Blocks.grass||((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&world.getBlockMetadata(x, y, z)!=0)))
    		return false;
		if(!world.isRemote){
			if(b==Blocks.dirt)
				world.setBlock(x, y, z, ModBlocks.compostSoilBlock, 0, 3);
			else if(b==Blocks.farmland)
				world.setBlock(x, y, z, ModBlocks.compostSoilTilledBlock, 0, 3);
			else if(b==Blocks.grass){
				if(itemStack.getItemDamage()==1&&new Random().nextInt(64)==0)
					world.setBlock(x, y, z, Blocks.mycelium, 0, 3);
				else
					world.setBlock(x, y, z, ModBlocks.compostSoilBlock, 0, 3);
			}else if(b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock){
				world.setBlockMetadataWithNotify(x, y, z, 0, 2+4);
			}
			itemStack.stackSize--;
		}
		world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), Blocks.grass.stepSound.getStepResourcePath(), (Blocks.grass.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.grass.stepSound.getPitch() * 0.8F);
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
