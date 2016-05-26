package convenientadditions.block;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPhantomPlatform extends Block {

	public BlockPhantomPlatform() {
		super(Material.fire);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.phantomPlatformBlockName);
	}

	@Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB p_185477_4_, List<AxisAlignedBB> p_185477_5_, Entity ent)
    {
		if(ent instanceof EntityPlayer && ent.posY>=(pos.getY()+1))
        	addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, state.getSelectedBoundingBox(worldIn, pos));
    }
    
    @Override
    public boolean isCollidable()
    {
        return false;
    }

	@Override
	public void updateTick(World world,BlockPos pos,IBlockState state,Random r){
		world.setBlockToAir(pos);
	}
	
	@Override
	public boolean isAir(IBlockState state,IBlockAccess world,BlockPos pos){
		return true;
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
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public void dropBlockAsItemWithChance(World worldIn,BlockPos pos,IBlockState state,float chance,int fortune){}
}
