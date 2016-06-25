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
		if(ModConfig.seedBox_autoCrops)
			SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxCropsEntry());
		if(ModConfig.seedBox_autoCompost)
			SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), compostDiscriminator);
		if(ModConfig.seedBox_behaviourProviderEntry)
			SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxBehaviourProviderEntry());
	}
	
	private static void initCompost(){
		CompostRegistry.addEntry(new CompostRegistryEntryCompostable());
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.TALLGRASS), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.CACTUS), 500);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.HAY_BLOCK), 10700);
		CompostRegistry.addCompostingItem(new ItemStack(Items.REEDS), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Items.CAKE), 2400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.VINE), 300);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.RED_FLOWER), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.YELLOW_FLOWER), 250);
		CompostRegistry.addCompostingItem(new ItemStack(Items.FEATHER), 110);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.PUMPKIN), 1400);
		CompostRegistry.addCompostingItem(new ItemStack(Items.EGG), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Items.SLIME_BALL), 130);
		CompostRegistry.addCompostingItem(new ItemStack(Items.WHEAT_SEEDS), 170);
		CompostRegistry.addCompostingItem(new ItemStack(Items.MELON_SEEDS), 100);
		CompostRegistry.addCompostingItem(new ItemStack(Items.PUMPKIN_SEEDS), 100);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.RED_MUSHROOM), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.BROWN_MUSHROOM), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.WATERLILY), 400);
		CompostRegistry.addCompostingItem(new ItemStack(Items.NETHER_WART), 1100);
		CompostRegistry.addCompostingItem(new ItemStack(Items.DYE), 227);
		CompostRegistry.addCompostingItem(new ItemStack(Items.BONE), 230);
		CompostRegistry.addCompostingItem(new ItemStack(Blocks.TALLGRASS), 230);
		CompostRegistry.addEntry(new CompostRegistryEntryDoublePlant());;
		CompostRegistry.addEntry(new CompostRegistryEntryFood());
		CompostRegistry.addCompostingOre("crop", 1200, true);
		CompostRegistry.addCompostingOre("treeLeaves", 200, true);
		CompostRegistry.addCompostingOre("treeSapling", 800, true);
		CompostRegistry.addCompostingOre("seed", 130, true);
		CompostRegistry.addCompostingOre("crop", 1200, true);
	}
}
