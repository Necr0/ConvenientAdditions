package convenientadditions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompostSoilTilled extends Block {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTop;

	public BlockCompostSoilTilled() {
		super(Material.ground);
		this.setTickRandomly(true).setHardness(0.5F).setStepSound(soundTypeGravel).setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilTilledBlockName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

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
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return hasWater;
        }

        return false;
    }
	
	@Override
	public void updateTick(World world,int x,int y,int z,Random r){
		if(!world.isRemote){
			Block b=world.getBlock(x,y+1,z);
			if(b!=null&&(b instanceof IPlantable||b instanceof IGrowable)){
				b.updateTick(world, x, y+1, z, r);
				if(r.nextInt(3)==0)
					b.updateTick(world, x, y+1, z, r);
			}
			if(b.getMaterial().isSolid())
				world.setBlock(x, y, z, ModBlocks.compostSoilBlock, 0, 2);
		}
	}
	
    public boolean isFertile(World world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
		return side==1?blockIconTop:blockIcon;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilBlockName);
        this.blockIconTop = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilTilledBlockName);
    }

	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ItemBlock.getItemFromBlock(ModBlocks.compostSoilBlock);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return ItemBlock.getItemFromBlock(ModBlocks.compostSoilBlock);
    }
	
	@Override
    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_)
    {
        if (!p_149746_1_.isRemote && p_149746_1_.rand.nextFloat() < p_149746_6_ - 0.5F)
        {
            if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, ModBlocks.compostSoilBlock, 0, 3);
        }
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return AxisAlignedBB.getBoundingBox((double)(p_149668_2_ + 0), (double)(p_149668_3_ + 0), (double)(p_149668_4_ + 0), (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
