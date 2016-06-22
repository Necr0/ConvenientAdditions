package convenientadditions.init;

import conveniencecore.entity.behaviour.BehaviourRegistry;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.block.composter.entries.CompostRegistryEntryCompostable;
import convenientadditions.block.composter.entries.CompostRegistryEntryDoublePlant;
import convenientadditions.block.composter.entries.CompostRegistryEntryFood;
import convenientadditions.block.seedbox.entries.SeedBoxBehaviourProviderEntry;
import convenientadditions.block.seedbox.entries.SeedBoxCropsEntry;
import convenientadditions.entity.behaviour.BehaviourCompost;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModCAAPI {
	public static Long compostDiscriminator;
	
	public static void init(){
		compostDiscriminator=BehaviourRegistry.addBehaviour(new BehaviourCompost());
		initSeedBox();
		initCompost();
	}

	private static void initSeedBox(){
		SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxCropsEntry());
		SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxBehaviourProviderEntry());
		SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), compostDiscriminator);
	}
	
	private static void initCompost(){
		CompostRegistry.addEntry(new CompostRegistryEntryCompostable());
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.cactus), 500);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.hay_block), 10700);
		CompostRegistry.addCompostingItem(new ItemStack(Items.reeds), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Items.cake), 2400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.vine), 300);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.red_flower), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.yellow_flower), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Items.feather), 110);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.pumpkin), 1400);
		CompostRegistry.addCompostingItem(new ItemStack(Items.egg), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Items.slime_ball), 130);
		CompostRegistry.addCompostingItem(new ItemStack(Items.wheat_seeds), 170);
		CompostRegistry.addCompostingItem(new ItemStack(Items.melon_seeds), 100);
		CompostRegistry.addCompostingItem(new ItemStack(Items.pumpkin_seeds), 100);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.red_mushroom), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.brown_mushroom), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.waterlily), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Items.nether_wart), 1100);
		CompostRegistry.addCompostingItem(new ItemStack(Items.dye), 227);
		CompostRegistry.addCompostingItem(new ItemStack(Items.bone), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		CompostRegistry.addEntry(new CompostRegistryEntryDoublePlant());;
		CompostRegistry.addEntry(new CompostRegistryEntryFood());
		CompostRegistry.addCompostingOre("crop", 1200, true);
		CompostRegistry.addCompostingOre("treeLeaves", 200, true);
		CompostRegistry.addCompostingOre("treeSapling", 800, true);
		CompostRegistry.addCompostingOre("seed", 130, true);
		CompostRegistry.addCompostingOre("crop", 1200, true);
	}
}
