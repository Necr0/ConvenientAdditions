package convenientadditions.block;

import java.util.List;
import java.util.Random;

import conveniencecore.item.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPhantomPlatform extends Block implements IModelResourceLocationProvider {
    public static final PropertyBool DESPAWN = PropertyBool.create("despawn");

	public BlockPhantomPlatform() {
		super(Material.fire);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.phantomPlatformBlockName);
        this.disableStats();
        this.translucent = true;
	}

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
    	if(Helper.getClientPlayer().getHeldItemOffhand()!=null)
    		if(Helper.getClientPlayer().getHeldItemOffhand().getItem()==ItemBlock.getItemFromBlock(state.getBlock()))
    			world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5, 0, 0, 0);
    }

	@Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB p_185477_4_, List<AxisAlignedBB> p_185477_5_, Entity ent)
    {
		if(ent instanceof EntityPlayer && ent.posY>=(pos.getY()+1) && !((EntityPlayer)ent).isSneaking())
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
