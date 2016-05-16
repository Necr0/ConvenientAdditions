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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCompostSoilTilled extends Block {

	public BlockCompostSoilTilled() {
		super(Material.ground);
		this.setTickRandomly(true).setHardness(0.5F).setStepSound(soundTypeGravel).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilTilledBlockName).setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
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
            case Crop:   return true;
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
				else{
					world.setBlock(x, y, z, Blocks.farmland);
					return;
				}
			}
			if(b.getMaterial().isSolid())
				world.setBlock(x, y, z, ModBlocks.compostSoilBlock, meta, 2);
		}
	}
	
    public boolean isFertile(World world, int x, int y, int z)
    {
        return true;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random r, int p_149650_3_)
    {
        return ItemBlock.getItemFromBlock(ModBlocks.compostSoilBlock);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos)
    {
        return ItemBlock.getItemFromBlock(ModBlocks.compostSoilBlock);
    }
	
	@Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float height)
    {
        if (!world.isRemote && world.rand.nextFloat() < height - 0.5F)
        {
            if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, ModBlocks.compostSoilBlock, 0, 3);
        }
    }
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return AxisAlignedBB.fromBounds((double)(p_149668_2_ + 0), (double)(p_149668_3_ + 0), (double)(p_149668_4_ + 0), (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }
}
