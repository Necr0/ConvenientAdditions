package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTempLight extends Block {
	
	public BlockTempLight(float intensisty) {
		super(Material.fire);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.tempLightBlockName)
		.setLightLevel(intensisty)
		.setTickRandomly(true);
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	}

	@Override
	public void updateTick(World world,BlockPos pos,IBlockState state,Random r){
		world.setBlockToAir(pos);
	}
	
	@Override
	public boolean isAir(IBlockAccess world,BlockPos pos){
		return true;
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
    {
        return false;
    }
    
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}
}
