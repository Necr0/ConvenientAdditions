package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.charge.TileEntityChargeTube;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockChargeTube extends BlockMachine {

	public BlockChargeTube() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.chargeTubeBlockName).setHardness(1F).setResistance(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeTube();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    /*public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
    	ArrayList<AxisAlignedBB> temp=new ArrayList<AxisAlignedBB>();
        temp.add(AxisAlignedBB.fromBounds(x+this.maxX/2-(1d/16d),y+this.maxY/2-(1d/16d),z+this.maxZ/2-(1d/16d),x+this.maxX/2+(1d/16d),y+this.maxY/2+(1d/16d),z+this.maxZ/2+(1d/16d)));
        
        TileEntity te=world.getTileEntity(new BlockPos(x, y, z));
        if(te!=null&&te instanceof TileEntityChargeTube){
	        for(EnumFacing d:EnumFacing.VALUES){
	        	if(((TileEntityChargeTube)te).isConnected(d)){
	            	double offsetX=(d.getFrontOffsetX()!=-1?-(1d/16d):-(.5d));
	            	double offsetY=(d.getFrontOffsetY()!=-1?-(1d/16d):-(.5d));
	            	double offsetZ=(d.getFrontOffsetZ()!=-1?-(1d/16d):-(.5d));
	            	double offsetX_1=(d.getFrontOffsetX()!=1?(1d/16d):(.5d));
	            	double offsetY_1=(d.getFrontOffsetX()!=1?(1d/16d):(.5d));
	            	double offsetZ_1=(d.getFrontOffsetX()!=1?(1d/16d):(.5d));
	        		temp.add(AxisAlignedBB.fromBounds(x+.5d+offsetX,y+.5d+offsetY,z+.5d+offsetZ,x+.5d+offsetX_1,y+.5d+offsetY_1,z+.5d+offsetZ_1));
	        	}
	        }
        }
        
        for(AxisAlignedBB bb:temp){
	        if (bb != null && mask.intersectsWith(bb))
	        {
	            list.add(bb);
	        }
        }
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        TileEntity te=world.getTileEntity(new BlockPos(x, y, z));
        if(te!=null&&te instanceof TileEntityChargeTube){
        	TileEntityChargeTube tube=(TileEntityChargeTube)te;
	    	this.minX=tube.isConnected(EnumFacing.WEST)?0:(6d/16d);
	    	this.minY=tube.isConnected(EnumFacing.DOWN)?0:(6d/16d);
	    	this.minZ=tube.isConnected(EnumFacing.NORTH)?0:(6d/16d);
	    	this.maxX=tube.isConnected(EnumFacing.EAST)?1:(10d/16d);
	    	this.maxY=tube.isConnected(EnumFacing.UP)?1:(10d/16d);
	    	this.maxZ=tube.isConnected(EnumFacing.SOUTH)?1:(10d/16d);
        }
    }*/
}
