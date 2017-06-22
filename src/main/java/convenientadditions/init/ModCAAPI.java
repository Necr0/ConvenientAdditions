package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.api.CCDataSerializers;
import convenientadditions.api.entity.specialitem.BehaviourRegistry;
import convenientadditions.api.entity.specialitem.behaviours.BehaviourAutoBoneMeal;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.block.misc.composter.entries.CompostRegistryEntryCompostable;
import convenientadditions.block.misc.composter.entries.CompostRegistryEntryDoublePlant;
import convenientadditions.block.misc.composter.entries.CompostRegistryEntryFood;
import convenientadditions.block.misc.seedbox.entries.SeedBoxBehaviourProviderEntry;
import convenientadditions.block.misc.seedbox.entries.SeedBoxCropsEntry;
import convenientadditions.block.misc.seedbox.entries.SeedBoxFeedEntry;
import convenientadditions.config.ModConfigMisc;
import convenientadditions.config.ModConfigRelics;
import convenientadditions.entity.behaviour.BehaviourAutoFeed;
import convenientadditions.entity.behaviour.BehaviourAutoFertilizer;
import convenientadditions.entity.behaviour.BehaviourCompost;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModCAAPI {
    public static String BEHAVIOUR_COMPOST;
    public static String BEHAVIOUR_AUTO_BONEMEAL;
    public static String BEHAVIOUR_AUTO_FERTILIZER;
    public static String BEHAVIOUR_AUTO_FEED;

    public static void init() {
        CCDataSerializers.initSerializers();
        BEHAVIOUR_COMPOST = BehaviourRegistry.addBehaviour(new ResourceLocation(ModConstants.Mod.MODID,"compost"), new BehaviourCompost());
        BEHAVIOUR_AUTO_BONEMEAL = BehaviourRegistry.addBehaviour(new ResourceLocation(ModConstants.Mod.MODID,"autoBonemeal"), new BehaviourAutoBoneMeal());
        BEHAVIOUR_AUTO_FERTILIZER = BehaviourRegistry.addBehaviour(new ResourceLocation(ModConstants.Mod.MODID,"autoFertilizer"), new BehaviourAutoFertilizer());
        BEHAVIOUR_AUTO_FEED = BehaviourRegistry.addBehaviour(new ResourceLocation(ModConstants.Mod.MODID,"autoFeed"), new BehaviourAutoFeed());
        initSeedBox();
        initCompost();
        initTome();
    }

    private static void initSeedBox() {
        if (ModConfigMisc.seedBox_autoCrops)
            SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxCropsEntry());
        if (ModConfigMisc.seedBox_autoCompost)
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), BEHAVIOUR_COMPOST);
        if (ModConfigMisc.seedBox_behaviourProviderEntry)
            SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxBehaviourProviderEntry());
        if (ModConfigMisc.seedBox_autoBoneMeal){
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(Items.DYE,1,15), BEHAVIOUR_AUTO_BONEMEAL, false, true);
            SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemFertilizer), BEHAVIOUR_AUTO_FERTILIZER);
        }
        if (ModConfigMisc.seedBox_autoFeed)
            SeedBoxItemBehaviourRegistry.addEntry(new SeedBoxFeedEntry());
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
        int time_very_long = 14 * 20;
        if (ModConfigRelics.transmutationTome_oreDoubling) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.COAL_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.COAL, 8), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.IRON_INGOT, 8), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.GOLD_INGOT, 8), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.DIAMOND, 8), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.EMERALD_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.EMERALD, 8), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.REDSTONE_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.REDSTONE, 24), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LAPIS_ORE, 4), new ItemStack(Items.FLINT, 1), new ItemStack(Items.DYE, 36, 4), time_short, 1);
        }
        if (ModConfigRelics.transmutationTome_oreUpgrade) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.COAL_ORE, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.IRON_ORE, 1), time_short, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.GOLD_ORE, 1), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.DIAMOND_ORE, 1), time_long, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.EMERALD_ORE, 1), time_long, 8);
        }
        if (ModConfigRelics.transmutationTome_oreDowngrade) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.IRON_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.COAL_ORE, 2), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GOLD_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.IRON_ORE, 2), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GOLD_ORE, 2), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.EMERALD_ORE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.DIAMOND_ORE, 1), time_long, 5);
        }
        if (ModConfigRelics.transmutationTome_oreOther) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 4), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.PRISMARINE_CRYSTALS, 1), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.PRISMARINE_SHARD, 1), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.DYE, 1, 4), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 4), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.REDSTONE, 4), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.QUARTZ, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REDSTONE, 16), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.GLOWSTONE_DUST, 16), time_medium, 6);
        }
        if (ModConfigRelics.transmutationTome_purification) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 6), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.LEATHER, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.BEEF, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.EGG, 1), new ItemStack(Items.CHICKEN, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ROTTEN_FLESH, 4), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(Items.RABBIT, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.POTATO, 1), time_short, 2);
        }
        if (ModConfigRelics.transmutationTome_stoneVariations) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 16, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 16, 1), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 16, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 16, 3), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 16, 3), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 16, 5), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.STONE, 16, 5), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.STONE, 16, 6), time_short, 1);
        }
        if (ModConfigRelics.transmutationTome_logVariations) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 16, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 16, 1), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 16, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 16, 2), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 16, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 16, 3), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG, 16, 3), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG2, 16, 0), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG2, 16, 0), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG2, 16, 1), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.LOG2, 16, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.LOG, 16, 0), time_short, 2);
        }
        if (ModConfigRelics.transmutationTome_cropMutation) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.ENDER_EYE, 3), new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.CHORUS_FLOWER, 1), time_long, 9);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT_SEEDS, 4), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.BEETROOT_SEEDS, 1), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 5), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.NETHER_WART, 1), time_short, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.MELON_BLOCK, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.PUMPKIN, 1), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.PUMPKIN, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.MELON_BLOCK, 1), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DEADBUSH, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.MELON_SEEDS, 1), time_medium, 8);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.SUGAR, 3), new ItemStack(Blocks.TALLGRASS, 1), new ItemStack(Items.REEDS, 1), time_medium, 4);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT_SEEDS, 3), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.DYE, 3, 3), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.PUMPKIN, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Blocks.CACTUS, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POTATO, 1), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.POISONOUS_POTATO, 1), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.CARROT, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.POTATO, 2), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.POTATO, 2), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.CARROT, 2), time_medium, 3);
        }
        if (ModConfigRelics.transmutationTome_dirtMutation) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.RED_MUSHROOM, 8), new ItemStack(Blocks.GRASS, 1), new ItemStack(Blocks.MYCELIUM), time_long, 9);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT_SEEDS, 8), new ItemStack(Blocks.DIRT, 1), new ItemStack(Blocks.GRASS, 1), time_long, 6);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.DIRT, 8), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Blocks.DIRT, 4, 1), time_short, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.GRASS, 8), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Blocks.DIRT, 4, 2), time_short, 3);
        }
        if (ModConfigRelics.transmutationTome_misc) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.WHEAT, 8), new ItemStack(Items.MILK_BUCKET, 1), new ItemStack(Items.BREAD, 5), time_short, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.COAL, 8, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.COAL, 8, 0), time_short, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.PRISMARINE_SHARD, 2), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Blocks.SPONGE, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GLASS_BOTTLE, 1), new ItemStack(Items.EXPERIENCE_BOTTLE, 3), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Blocks.CLAY, 1), time_medium, 2);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 4, 1), new ItemStack(Items.ROTTEN_FLESH, 1), new ItemStack(Blocks.SOUL_SAND, 1), time_long, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(Items.SLIME_BALL, 1), new ItemStack(Items.SUGAR, 4), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.GUNPOWDER, 4), time_medium, 1);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 6), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.SAND, 6, 1), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 6, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.SAND, 6), time_medium, 3);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.NETHER_WART, 6), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.GHAST_TEAR, 2), time_long, 10);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.MAGMA_CREAM, 4), new ItemStack(Items.CHORUS_FRUIT, 1), new ItemStack(Items.ENDER_PEARL, 4), time_long, 7);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.REEDS, 4), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.BLAZE_ROD, 2), time_long, 7);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.NETHERRACK, 16), new ItemStack(Items.CHORUS_FRUIT, 1), new ItemStack(Blocks.END_STONE, 16), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.END_STONE, 16), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Blocks.NETHERRACK, 16), time_medium, 5);
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Blocks.SAND, 5), new ItemStack(Blocks.STONE, 1), new ItemStack(Blocks.GRAVEL, 5), time_short, 2);
        }
        if (ModConfigRelics.transmutationTome_dragonEgg) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(Items.EGG, 1), new ItemStack(Items.DRAGON_BREATH, 1), new ItemStack(Blocks.DRAGON_EGG, 1), time_very_long, 42);
        }
        if (ModConfigRelics.transmutationTome_elytra) {
            TransmutationTomeRecipeHandler.INSTANCE.addRecipe(new ItemStack(ModItems.itemGlider, 1), new ItemStack(Items.DRAGON_BREATH, 1), new ItemStack(Items.ELYTRA, 1), time_very_long, 16);
        }
    }
}
