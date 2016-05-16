package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockCompostSoil extends Block {

	public BlockCompostSoil() {
		super(Material.ground);
		this.setTickRandomly(true).setHardness(0.5F).setStepSound(soundTypeGravel).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilBlockName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable)
    {
		BlockPos plantPos = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
        IBlockState plant = plantable.getPlant(world, plantPos);
        EnumPlantType plantType = plantable.getPlantType(world, plantPos);

        if (plantable instanceof BlockBush)
            return true;

        switch (plantType)
        {
            case Desert: return true;
            case Nether: return false;
            case Crop:   return false;
            case Cave:   return true;
            case Plains: return true;
            case Water:  return false;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water ||
                world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water);
                return hasWater;
        }

        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
    	if(current==null||!(current.getItem() instanceof ItemHoe))
        	return false;
    	if(world.getBlock(x,y+1,z).isAir(world, x, y+1, z)){
    		if(!world.isRemote){
				current.damageItem(1, player);
	    		world.setBlock(x, y, z, ModBlocks.compostSoilTilledBlock, world.getBlockMetadata(x, y, z), 3);
    		}
        	world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.stepSound.getStepResourcePath(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    	}
    	return true;
    }
	
    public boolean isFertile(World world, int x, int y, int z)
    {
        return true;
    }

	@Override
	public void updateTick(World world,BlockPos pos,IBlockState state,Random r){
		if(!world.isRemote){
			Block b=world.getBlock(x,y+1,z);
			int meta=world.getBlockMetadata(x, y, z);
			if(b!=null&&(b instanceof IPlantable||b instanceof IGrowable)){
				b.updateTick(world, x, y+1, z, r);
				if((r.nextDouble()*3)<=((.2*meta)+3))
					b.updateTick(world, x, y+1, z, r);
			}
			if(r.nextBoolean()){
				if(meta<10)
					world.setBlockMetadataWithNotify(x, y, z, meta+1, 2+4);
				else
					world.setBlock(x, y, z, Blocks.dirt);
			}
		}
	}
	
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }
}
