package convenientadditions.api.item;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;

public class ItemToolBase extends ItemTool {

	protected ItemToolBase(ToolMaterial material, EnumToolType type) {
		super(material, type.getEffectiveSet(type));
	}

	public enum EnumToolType{
		axe,
		pickaxe,
		spade;
		
		Set<Block> getEffectiveSet(EnumToolType type){
			switch(type){
				case axe:
					return Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder, Blocks.wooden_button, Blocks.wooden_pressure_plate});
				case spade:
					return Sets.newHashSet(new Block[] {Blocks.clay, Blocks.dirt, Blocks.farmland, Blocks.grass, Blocks.gravel, Blocks.mycelium, Blocks.sand, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand, Blocks.grass_path});
				case pickaxe:
					return Sets.newHashSet(new Block[] {Blocks.activator_rail, Blocks.coal_ore, Blocks.cobblestone, Blocks.detector_rail, Blocks.diamond_block, Blocks.diamond_ore, Blocks.double_stone_slab, Blocks.golden_rail, Blocks.gold_block, Blocks.gold_ore, Blocks.ice, Blocks.iron_block, Blocks.iron_ore, Blocks.lapis_block, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.mossy_cobblestone, Blocks.netherrack, Blocks.packed_ice, Blocks.rail, Blocks.redstone_ore, Blocks.sandstone, Blocks.red_sandstone, Blocks.stone, Blocks.stone_slab, Blocks.stone_button, Blocks.stone_pressure_plate});
				default:
					return null;
			}
		}
	}
}
