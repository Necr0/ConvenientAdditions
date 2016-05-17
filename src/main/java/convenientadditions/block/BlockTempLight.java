package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTempLight extends Block {
	
	public BlockTempLight(float intensisty) {
		super(Material.fire);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.tempLightBlockName)
		.setLightLevel(intensisty)
		.setTickRandomly(true);
	}

	@Override
	public void updateTick(World world,BlockPos pos,IBlockState state,Random r){
		world.setBlockToAir(pos);
	}
	
	@Override
	public boolean isAir(IBlockState state,IBlockAccess world,BlockPos pos){
		return true;
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean canCollideCheck(IBlockState state,boolean hitIfLiquid)
    {
        return false;
    }
    
    @Override
    public void dropBlockAsItemWithChance(World worldIn,BlockPos pos,IBlockState state,float chance,int fortune){}
}
