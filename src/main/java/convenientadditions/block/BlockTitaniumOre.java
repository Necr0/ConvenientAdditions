package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTitaniumOre extends BlockOre {
	public BlockTitaniumOre() {
		super();
		this.setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.oreTitaniumBlockName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setHarvestLevel("pickaxe", 2);
    }
}
