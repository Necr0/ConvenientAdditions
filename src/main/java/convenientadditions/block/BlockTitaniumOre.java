package convenientadditions.block;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTitaniumOre extends Block {
	public BlockTitaniumOre() {
		super(Material.rock);
		this.setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.oreTitaniumBlockName).setBlockTextureName(ConvenientAdditionsMod.MODID+":titanium_ore").setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setHarvestLevel("pickaxe", 2);
    }
	
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
    
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }
    
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return this.quantityDropped(p_149679_2_);
    }
    
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }
    
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        return 0;
    }
    
    public int damageDropped(int p_149692_1_)
    {
        return 0;
    }
}
