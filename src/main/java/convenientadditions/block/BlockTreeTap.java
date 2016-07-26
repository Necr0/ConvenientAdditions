package convenientadditions.block;

import java.util.Arrays;
import java.util.Random;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTreeTap extends Block implements IModelResourceLocationProvider {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyEnum<EnumBottleState> BOTTLE_STATE = PropertyEnum.<EnumBottleState>create("bottle_state", EnumBottleState.class);
	
	public BlockTreeTap() {
		super(Material.WOOD);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.treetapBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditions.CREATIVETAB).setTickRandomly(true);
		this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BOTTLE_STATE, EnumBottleState.empty));
	}
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (Arrays.asList(EnumFacing.HORIZONTALS).contains(facing))
        {
            return this.getDefaultState().withProperty(FACING, facing.getOpposite());
        }
        else
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (worldIn.isSideSolid(pos.offset(enumfacing.getOpposite()), enumfacing, true))
                {
                    return this.getDefaultState().withProperty(FACING, enumfacing.getOpposite());
                }
            }

            return this.getDefaultState();
        }
    }
    
    @Override
    public boolean isSideSolid(IBlockState base_state,IBlockAccess world,BlockPos pos,EnumFacing side){
    	return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(world.isRemote)
    		return true;
    	EnumBottleState s=state.getValue(BOTTLE_STATE);
    	if(s==EnumBottleState.empty){
    		if(held.getItem()==ModItems.itemSapBottle){
    			world.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.values()[held.getItemDamage()+1]));
    			player.setHeldItem(hand, null);
    		}
    	}else{
			world.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.empty));
    		EntityItem e=new EntityItem(world, pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5, new ItemStack(ModItems.itemSapBottle, 1, s.ordinal()-1));
    		e.setVelocity( (player.posX-pos.getX()+.5)/8, (player.posY-pos.getY()+.5)/8, (player.posZ-pos.getZ()+.5)/8 );
    		world.spawnEntityInWorld(e);
    	}
    	return true;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote&&rand.nextInt(8)==0)
        {
            IBlockState log=worldIn.getBlockState(pos.add(state.getValue(FACING).getDirectionVec()));
            if(log.getBlock()==Blocks.LOG||log.getBlock()==Blocks.LOG2){
            	if(state.getValue(BOTTLE_STATE)==EnumBottleState.bottle)
            		worldIn.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.bottle_half));
            	else if(state.getValue(BOTTLE_STATE)==EnumBottleState.bottle_half)
            		worldIn.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.bottle_full));
            }
        }
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    //RENDERING
    
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
    
    //BLOCKSTATE

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(BOTTLE_STATE, EnumBottleState.values()[(meta&12)>>2]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex()+
        		(((EnumBottleState)state.getValue(BOTTLE_STATE)).ordinal()<<2);
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING,BOTTLE_STATE});
    }
    
    public static enum EnumBottleState implements IStringSerializable{
    	empty,
    	bottle,
    	bottle_half,
    	bottle_full;
	
		@Override
		public String getName() {
			return this.name();
		}
	}
}
