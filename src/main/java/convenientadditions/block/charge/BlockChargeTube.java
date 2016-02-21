package convenientadditions.block.charge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityChargeTube;

public class BlockChargeTube extends BlockMachine {

	public BlockChargeTube() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.chargeTubeBlockName).setHardness(1F).setResistance(1F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.chargeTubeBlockName+"Block");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeTube();
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
    	ArrayList<AxisAlignedBB> temp=new ArrayList<AxisAlignedBB>();
        temp.add(AxisAlignedBB.getBoundingBox(x+this.maxX/2-(1d/16d),y+this.maxY/2-(1d/16d),z+this.maxZ/2-(1d/16d),x+this.maxX/2+(1d/16d),y+this.maxY/2+(1d/16d),z+this.maxZ/2+(1d/16d)));
        
        TileEntity te=world.getTileEntity(x, y, z);
        if(te!=null&&te instanceof TileEntityChargeTube){
	        for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){
	        	if(((TileEntityChargeTube)te).isConnected(d)){
	            	double offsetX=(d.offsetX!=-1?-(1d/16d):-(.5d));
	            	double offsetY=(d.offsetY!=-1?-(1d/16d):-(.5d));
	            	double offsetZ=(d.offsetZ!=-1?-(1d/16d):-(.5d));
	            	double offsetX_1=(d.offsetX!=1?(1d/16d):(.5d));
	            	double offsetY_1=(d.offsetY!=1?(1d/16d):(.5d));
	            	double offsetZ_1=(d.offsetZ!=1?(1d/16d):(.5d));
	        		temp.add(AxisAlignedBB.getBoundingBox(x+this.maxX/2+offsetX,y+this.maxY/2+offsetY,z+this.maxZ/2+offsetZ,x+this.maxX/2+offsetX_1,y+this.maxY/2+offsetY_1,z+this.maxZ/2+offsetZ_1));
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
}
