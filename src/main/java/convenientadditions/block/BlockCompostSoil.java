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
    	BlockPos posU=new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
    	if(world.getBlockState(posU).getBlock().isAir(world, posU)){
    		if(!world.isRemote){
				current.damageItem(1, player);
	    		world.setBlockState(pos, ModBlocks.compostSoilTilledBlock METADATA, 3);
    		}
            world.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), Blocks.grass.stepSound.getStepSound(), (Blocks.grass.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.grass.stepSound.getFrequency() * 0.8F);
    	}
    	return true;
    }
	
    @Override
    public boolean isFertile(World world, BlockPos pos)
    {
        return true;
    }

	@Override
	public void updateTick(World world,BlockPos pos,IBlockState state,Random r){
		if(!world.isRemote){
	    	BlockPos posU=new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
			Block b=world.getBlockState(posU).getBlock();
			if(b!=null&&(b instanceof IPlantable||b instanceof IGrowable)){
				b.updateTick(world, posU, world.getBlockState(posU), r);
				if((r.nextDouble()*3)<=((.2*META)+3))
					b.updateTick(world, posU, world.getBlockState(posU), r);
			}
			if(r.nextBoolean()){
				if(META<10)
					world.setBlockMetadataWithNotify(METADATA, META+1, 2+4);
				else
					world.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
	}
	
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }
}
