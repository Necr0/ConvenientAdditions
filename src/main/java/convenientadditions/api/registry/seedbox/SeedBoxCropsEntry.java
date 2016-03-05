package convenientadditions.api.registry.seedbox;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import convenientadditions.api.ConAddAPI;
import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;
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
	public long getDiscriminator(ItemStack stack) {
		return BehaviourRegistry.API_DISCRIMINATORS.get(0);
	}
	
}
