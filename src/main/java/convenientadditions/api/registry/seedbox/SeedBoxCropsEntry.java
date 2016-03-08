package convenientadditions.api.registry.seedbox;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import convenientadditions.api.registry.behaviour.BehaviourRegistry;

public class SeedBoxCropsEntry implements ISeedBoxItemBehaviourRegistryEntry {

	@Override
	public boolean hasSpecialBehaviour(ItemStack stack) {
		Item i=stack.getItem();
		return i==Items.nether_wart||
				i==Items.potato||
				i==Items.carrot||
				i==Items.wheat_seeds||
				i==Items.melon_seeds||
				i==Items.pumpkin_seeds||
				i==ItemBlock.getItemFromBlock(Blocks.sapling);
	}

	@Override
	public void getDiscriminators(ItemStack stack,List<Long> behaviours) {
		behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("autoCrops"));
	}
	
}
