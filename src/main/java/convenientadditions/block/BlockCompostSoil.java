package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockCompostSoil extends Block {
    public static final PropertyInteger DEGRADATION = PropertyInteger.create("degradation", 0, 10);

	public BlockCompostSoil() {
		super(Material.ground);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilBlockName).setTickRandomly(true).setHardness(0.5F).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setStepSound(SoundType.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DEGRADATION, Integer.valueOf(0)));
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state,IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable)
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
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.water ||
                world.getBlockState(pos.west()).getMaterial() == Material.water ||
                world.getBlockState(pos.north()).getMaterial() == Material.water ||
                world.getBlockState(pos.south()).getMaterial() == Material.water);
            	return hasWater;
        }

        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
    	if(current==null||!(current.getItem() instanceof ItemHoe))
        	return false;
    	BlockPos posU=new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
    	IBlockState stateU=world.getBlockState(posU);
    	if(stateU.getBlock().isAir(stateU,world, posU)){
    		if(!world.isRemote){
				current.damageItem(1, player);
	    		world.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getStateFromMeta(this.getMetaFromState(state)), 3);
    		}
            world.playSound(
            		(double)((float)pos.getX() + 0.5F),
            		(double)((float)pos.getY() + 0.5F),
            		(double)((float)pos.getZ() + 0.5F),
            		Blocks.grass.getStepSound().getStepSound(),
            		SoundCategory.BLOCKS,
            		(Blocks.grass.getStepSound().getVolume() + 1.0F) / 2.0F,
            		Blocks.grass.getStepSound().getPitch() * 0.8F,
            		false);
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
			IBlockState newB=world.getBlockState(posU);
			int deg=((Integer)state.getValue(DEGRADATION)).intValue();
			if(b!=null&&(b instanceof IPlantable||b instanceof IGrowable)){
				b.updateTick(world, posU, world.getBlockState(posU), r);
				int i=deg;
				if(r.nextInt(15)>i)
					b.updateTick(world, posU, world.getBlockState(posU), r);
				i++;
				if(r.nextInt(15)>i)
					b.updateTick(world, posU, world.getBlockState(posU), r);
				i++;
				if(r.nextInt(15)>i)
					b.updateTick(world, posU, world.getBlockState(posU), r);
			}
			if(r.nextInt(4)==0){
				if(deg<10)
					world.setBlockState(pos,state.withProperty(DEGRADATION, deg+1));
				else
					world.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
	}
	
	@Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    //
    // BLOCKSTATE STUFF
    //
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DEGRADATION, Integer.valueOf(meta));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(DEGRADATION)).intValue();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{DEGRADATION});
    }
}
