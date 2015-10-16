package convenientadditions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedstonePulse extends Block {

	public BlockRedstonePulse() {
		super(Material.fire);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.tempLightBlockName)
		.setTickRandomly(true)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.textureNoneBlockName);
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(world.isRemote&&random.nextInt(8)==0){
			world.spawnParticle("reddust", x+.25+(random.nextDouble()/2), y+.25+(random.nextDouble()/2), z+.25+(random.nextDouble()/2), 1D, 0D, 0D);
		}
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world,int x,int y,int z,int side){
		return 15;
	}
	
	@Override
	public boolean isAir(IBlockAccess world,int x,int y,int z){
		return true;
	}
	
	@Override
	public void updateTick(World world,int x,int y,int z,Random r){
		world.setBlockToAir(x, y, z);
	}

	public int getRenderType()
    {
        return -1;
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
