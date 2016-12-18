package convenientadditions.init;

import convenientadditions.api.entity.specialitem.BehaviourRegistry;
import convenientadditions.api.entity.specialitem.behaviours.BehaviourAutoBoneMeal;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.block.composter.entries.CompostRegistryEntryCompostable;
import convenientadditions.block.composter.entries.CompostRegistryEntryDoublePlant;
import convenientadditions.block.composter.entries.CompostRegistryEntryFood;
import convenientadditions.block.seedbox.entries.SeedBoxBehaviourProviderEntry;
import convenientadditions.block.seedbox.entries.SeedBoxCropsEntry;
import convenientadditions.entity.behaviour.BehaviourAutoFertilizer;
import convenientadditions.entity.behaviour.BehaviourCompost;
import convenientadditions.api.entity.specialitem.behaviours.BehaviourSunlightChargeable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModCAAPI {
    public static Long compostDiscriminator;
    public static Long sunlightChargableDiscriminator;
    public static Long autoBoneMealDiscriminator;
    public static Long autoFertilizerDiscriminator;

    public static void init() {
        compostDiscriminator = BehaviourRegistry.addBehaviour(new BehaviourCompost());
        sunlightChargableDiscriminator = BehaviourRegistry.addBehaviour(new BehaviourSunlightChargeable());
        autoBoneMealDiscriminator = BehaviourRegistry.addBehaviour(new BehaviourAutoBoneMeal());
        autoFertilizerDiscriminator = BehaviourRegistry.addBehaviour(new BehaviourAutoFertilizer());
        initSeedBox();
        initCompost();
        initTome();
    }

    private static void initSeedBox() {
        if (ModConfig.seedBox_autoCrops)
            SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxCropsEntry());
        if (ModConfig.seedBox_autoCompost)
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), compostDiscriminator);
        if (ModConfig.seedBox_behaviourProviderEntry)
            SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxBehaviourProviderEntry());
        if (ModConfig.seedBox_autoBoneMeal){
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(Items.DYE,1,15), autoBoneMealDiscriminator, false, true);
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemFertilizer), autoFertilizerDiscriminator);
        }
    }

    private static void initCompost() {
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
        CompostRegistry.addCompostingItem(new ItemStack(Blocks.NETHER_WART_BLOCK), 9900);
        CompostRegistry.addCompostingItem(new ItemStack(Items.DYE), 227);
        CompostRegistry.addCompostingItem(new ItemStack(Items.BONE), 230);
        CompostRegistry.addCompostingItem(new ItemStack(Blocks.BONE_BLOCK), 2043);
        CompostRegistry.addCompostingItem(new ItemStack(Blocks.TALLGRASS), 230);
        CompostRegistry.addEntry(new CompostRegistryEntryDoublePlant());
        CompostRegistry.addEntry(new CompostRegistryEntryFood());
        CompostRegistry.addCompostingOre("crop", 1200, true);
        CompostRegistry.addCompostingOre("treeLeaves", 200, true);
        CompostRegistry.addCompostingOre("treeSapling", 800, true);
        CompostRegistry.addCompostingOre("seed", 130, true);
        CompostRegistry.addCompostingOre("crop", 1200, true);
    }

    private static void initTome() {
        int time_short = 2 * 20;
        int time_medium = 5 * 20;
        int time_long = 8 * 20;
        if (ModConfig.transmutationTome_oreDoubling) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.COAL_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.COAL, 6), time_short, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.IRON_INGOT, 6), time_medium, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.GOLD_INGOT, 6), time_long, 6);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.DIAMOND, 6), time_long, 10);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.EMERALD_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.EMERALD, 6), time_long, 12);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.REDSTONE_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.REDSTONE, 6), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LAPIS_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.DYE, 6, 4), time_short, 2);
        }
        if (ModConfig.transmutationTome_oreUpgrade) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.COAL_ORE, 8), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.IRON_ORE, 1), time_short, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 8), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.GOLD_ORE, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 8), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.DIAMOND_ORE, 1), time_long, 10);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 2), new ItemStack(Items.DRAGON_BREATH, 1), new ItemStack(Blocks.EMERALD_ORE, 1), time_long, 10);
        }
        if (ModConfig.transmutationTome_oreDowngrade) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.COAL_ORE, 4), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.IRON_ORE, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GOLD_ORE, 4), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.EMERALD_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.DIAMOND_ORE, 1), time_long, 10);
        }
        if (ModConfig.transmutationTome_oreOther) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 4), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.PRISMARINE_CRYSTALS, 1), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.PRISMARINE_SHARD, 1), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.DYE, 1, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 4), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.REDSTONE, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.QUARTZ, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 16), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.GLOWSTONE_DUST, 16), time_medium, 12);
        }
        if (ModConfig.transmutationTome_purification) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 6), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.LEATHER, 1), time_medium, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.BEEF, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.EGG, 1), new ItemStack(Items.CHICKEN, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(Items.RABBIT, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.POTATO, 1), time_short, 3);
        }
        if (ModConfig.transmutationTome_stoneVariations) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 8, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 8, 1), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 8, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 8, 3), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 8, 3), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 8, 5), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 8, 5), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 8, 6), time_short, 1);
        }
        if (ModConfig.transmutationTome_logVariations) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 8, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 8, 1), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 8, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 8, 2), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 8, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 8, 3), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 8, 3), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG2, 8, 0), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG2, 8, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG2, 8, 1), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG2, 8, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 8, 0), time_short, 2);
        }
        if (ModConfig.transmutationTome_cropMutation) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ENDER_EYE, 8), new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.CHORUS_FLOWER, 1), time_long, 9);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT_SEEDS, 4), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.BEETROOT_SEEDS, 1), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 5), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.NETHER_WART, 1), time_short, 10);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.MELON_BLOCK, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.PUMPKIN, 1), time_medium, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.PUMPKIN, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.MELON_BLOCK, 1), time_medium, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DEADBUSH, 3), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.MELON_SEEDS, 1), time_medium, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.SUGAR, 3), new ItemStack(Blocks.TALLGRASS, 1), new ItemStack(Items.REEDS, 1), time_medium, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.NETHER_WART, 3), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.DYE, 3, 3), time_long, 7);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.PUMPKIN, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Blocks.CACTUS, 1), time_medium, 6);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POTATO, 1), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.POISONOUS_POTATO, 1), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.CARROT, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.POTATO, 2), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POTATO, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.CARROT, 2), time_medium, 5);
        }
        if (ModConfig.transmutationTome_dirtMutation) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 8), new ItemStack(Blocks.GRASS, 1), new ItemStack(Blocks.MYCELIUM), time_long, 11);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT_SEEDS, 8), new ItemStack(Blocks.DIRT, 1), new ItemStack(Blocks.GRASS, 1), time_long, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.GUNPOWDER, 8), new ItemStack(Blocks.DIRT, 1), new ItemStack(Blocks.DIRT, 1), time_medium, 6);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.GUNPOWDER, 8), new ItemStack(Blocks.GRASS, 1), new ItemStack(Blocks.DIRT, 2), time_medium, 6);
        }
        if (ModConfig.transmutationTome_misc) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT, 8), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.BREAD, 4), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.COAL, 8, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.COAL, 8, 0), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.PRISMARINE_SHARD, 2), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Blocks.SPONGE, 1), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.GLASS_BOTTLE, 1), new ItemStack(Items.EXPERIENCE_BOTTLE, 5), time_long, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Blocks.CLAY, 1), time_medium, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4, 1), new ItemStack(Items.ROTTEN_FLESH, 1), new ItemStack(Blocks.SOUL_SAND, 1), time_long, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(Items.SLIME_BALL, 1), new ItemStack(Items.SUGAR, 4), time_medium, 7);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.GUNPOWDER, 4), time_medium, 7);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.SAND, 4, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.SAND, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.NETHER_WART, 6), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.GHAST_TEAR, 2), time_long, 19);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.MAGMA_CREAM, 4), new ItemStack(Items.CHORUS_FRUIT, 1), new ItemStack(Items.ENDER_PEARL, 4), time_long, 9);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REEDS, 4), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.BLAZE_ROD, 2), time_long, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.NETHERRACK, 5), new ItemStack(Items.CHORUS_FRUIT, 1), new ItemStack(Blocks.END_STONE, 5), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.END_STONE, 5), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.NETHERRACK, 5), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 5), new ItemStack(Blocks.STONE, 1), new ItemStack(Blocks.GRAVEL, 3), time_short, 2);
        }
    }
}
