package convenientadditions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.init.ModBlocks;

public class BlockCompostSoil extends Block {

	public BlockCompostSoil() {
		super(Material.ground);
		this.setTickRandomly(true).setHardness(0.5F).setStepSound(soundTypeGravel).setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilBlockName).setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.compostSoilBlockName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
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
            case Crop:   return false;
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
    	if(current==null||!(current.getItem() instanceof ItemHoe))
        	return false;
    	if(!world.isRemote&&world.getBlock(x,y+1,z).isAir(world, x, y+1, z)){
    		current.damageItem(1, player);
    		world.setBlock(x, y, z, ModBlocks.compostSoilTilledBlock, 0, 3);
    	}
    	world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.stepSound.getStepResourcePath(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    	return true;
    }
	
    public boolean isFertile(World world, int x, int y, int z)
    {
        return true;
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
		}
	}
}
