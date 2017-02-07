package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ModConfig {
    //#########COMPOSTER#############
    public static boolean composter_recipe;
    public static boolean composter_overflowSmell;
    public static int composter_foodMultiplier;
    //chances
    public static float composter_compostChance;
    public static float composter_extraCompostChance;
    public static float composter_dirtChunkChance;
    public static float composter_fertilizerChance;
    public static float composter_sporesMyceliumChance;
    //stats
    public static int composter_capacity;
    public static int composter_progressPeriod;
    public static int composter_progressContent;

    //#########SEED BOX#############
    public static boolean seedBox_recipe;
    public static boolean seedBox_autoCrops;
    public static boolean seedBox_autoCompost;
    public static boolean seedBox_autoBoneMeal;
    public static boolean seedBox_behaviourProviderEntry;
    public static boolean seedBox_autoFeed;
    public static List<String> seedBox_autoFeedItems;
    public static List<String> seedBox_autoFeedBlacklist;

    //#########INVENTORY PROXIES#############
    public static boolean inventoryProxies_regular;
    public static boolean inventoryProxies_sided;
    public static boolean inventoryProxies_filtered;
    public static int inventoryProxies_chainLimit;
    public static boolean inventoryProxies_transmitter;
    public static boolean inventoryProxies_receiver;
    public static List<String> inventoryProxies_blacklist;

    //#########CHANNEL MODULES#############
    public static boolean channelModules_player;
    public static boolean channelModules_color;

    //#########CHARGE ITEMS#############
    public static boolean charge_sunstone;
    public static boolean charge_blazingRock;

    //#########BAUBLES#############
    public static boolean baubles_ring_of_charging;
    public static boolean baubles_amulet_of_breath;
    public static boolean baubles_amulet_of_wisdom;
    public static boolean baubles_ring_of_saturation;
    public static boolean baubles_ring_of_sunlight;
    public static boolean baubles_nether_charm;

    //#########ENDERSLATE#############
    public static boolean enderPlate_recipe;
    public static boolean enderPlate_crystalCharge;
    public static int enderPlate_crystalChargeRate;
    public static boolean enderPlate_enderEyeRechargeRecipe;
    public static float enderPlate_enderEyeRechargePercentage;

    //#########TRANSMUTATION TOME#############
    public static boolean transmutationTome_recipe;
    public static boolean transmutationTome_oreDoubling;
    public static boolean transmutationTome_oreUpgrade;
    public static boolean transmutationTome_oreDowngrade;
    public static boolean transmutationTome_oreOther;
    public static boolean transmutationTome_logVariations;
    public static boolean transmutationTome_stoneVariations;
    public static boolean transmutationTome_cropMutation;
    public static boolean transmutationTome_dirtMutation;
    public static boolean transmutationTome_purification;
    public static boolean transmutationTome_flowerVariatons;
    public static boolean transmutationTome_misc;

    //#########SPECIAL ARROWS#############
    public static boolean specialArrows_creeper;
    public static boolean specialArrows_slime;
    public static boolean specialArrows_blast;

    //#########HOVER PAD#############
    public static boolean hoverPad_recipe;
    public static int hoverPad_range;

    //#########MOB CATCHER#############
    public static boolean mobCatcher_recipe;
    public static List<String> mobCatcher_blacklist;

    //#########GENERAL#############
    public static boolean ironWrench;
    public static boolean launchingArrows;
    public static boolean playerInterface;
    public static boolean proximitySensor;
    public static boolean powderKeg;
    public static boolean setProvider;
    public static boolean treetap;
    public static boolean sugarOreDictInit;
    public static boolean antidote;
    public static boolean bandage;
    public static boolean blastPad;
    public static boolean platform;
    public static boolean storageMatrix;

    public static void init() {
        Configuration cfg = new Configuration(new File("config/ConvAdd.cfg"));
        cfg.load();

        String category = Configuration.CATEGORY_GENERAL;
        cfg.setCategoryRequiresMcRestart(category, true);
        ironWrench = cfg.getBoolean("ironWrench", category, false, "");
        launchingArrows = cfg.getBoolean("launchingArrows", category, true, "");
        playerInterface = cfg.getBoolean("playerInterface", category, true, "");
        proximitySensor = cfg.getBoolean("proximitySensor", category, true, "");
        powderKeg = cfg.getBoolean("powderKeg", category, true, "");
        setProvider = cfg.getBoolean("setProvider", category, true, "");
        treetap = cfg.getBoolean("treetap", category, true, "");
        sugarOreDictInit = cfg.getBoolean("sugarOreDictInit", category, true, "DON'T CHANGE THIS IF YOU DON'T KNOW WHAT IT IS!");
        antidote = cfg.getBoolean("antidote", category, true, "requires sap from treetap");
        bandage = cfg.getBoolean("antidote", category, true, "requires sap from treetap");
        blastPad = cfg.getBoolean("blastPad", category, true, "");
        platform = cfg.getBoolean("platform", category, true, "also disables semi-solid blocks");
        storageMatrix = cfg.getBoolean("storageMatrix", category, true, "");

        category = "composter";
        cfg.setCategoryRequiresMcRestart(category, true);
        composter_recipe = cfg.getBoolean("recipe", category, true, "");
        composter_overflowSmell = cfg.getBoolean("overflowSmell", category, true, "Enables potion effects when overflowing");
        composter_compostChance = cfg.getFloat("compostChance", category, 1F, 0F, 1F, "");
        composter_extraCompostChance = cfg.getFloat("extraCompostChance", category, .25F, 0F, 1F, "");
        composter_dirtChunkChance = cfg.getFloat("dirtChunkChance", category, .4F, 0F, 1F, "");
        composter_fertilizerChance = cfg.getFloat("fertilizerChance", category, .1666F, 0F, 1F, "");
        composter_sporesMyceliumChance = cfg.getFloat("fertilizerChance", category, .015625F, 0F, 1F, "");
        composter_foodMultiplier = cfg.getInt("foodMultiplier", category, 400, 0, Integer.MAX_VALUE, "");
        composter_capacity = cfg.getInt("capacity", category, 25000, 1, Integer.MAX_VALUE, "");
        composter_progressPeriod = cfg.getInt("progressPeriod", category, 2100, 1, Integer.MAX_VALUE, "The amount of time needed for 1 operation.");
        composter_progressContent = cfg.getInt("progressContent", category, 2500, 1, Integer.MAX_VALUE, "The amount of composting mass needed for 1 operation.");

        category = "charge items";
        cfg.setCategoryRequiresMcRestart(category, true);
        charge_sunstone = cfg.getBoolean("sunstone", category, true, "");
        charge_blazingRock = cfg.getBoolean("blazingRock", category, true, "");

        category = "baubles";
        cfg.setCategoryRequiresMcRestart(category, true);
        baubles_ring_of_charging = cfg.getBoolean("ring_of_charging", category, true, "");
        baubles_amulet_of_breath = cfg.getBoolean("amulet_of_breath", category, true, "");
        baubles_amulet_of_wisdom = cfg.getBoolean("amulet_of_wisdom", category, true, "");
        baubles_ring_of_saturation = cfg.getBoolean("ring_of_saturation", category, true, "");
        baubles_ring_of_sunlight = cfg.getBoolean("ring_of_sunlight", category, true, "");
        baubles_nether_charm = cfg.getBoolean("nether_charm", category, true, "");

        category = "enderPlate";
        cfg.setCategoryRequiresMcRestart(category, true);
        enderPlate_recipe = cfg.getBoolean("recipe", category, true, "");
        enderPlate_crystalCharge = cfg.getBoolean("crystalCharge", category, true, "");
        enderPlate_crystalChargeRate = cfg.getInt("crystalChargeRate", category, 120, 0, Integer.MAX_VALUE, "in charges/second; rate=log_2(crystalCount+1)");
        enderPlate_enderEyeRechargeRecipe = cfg.getBoolean("enderEyeRechargeRecipe", category, true, "");
        enderPlate_enderEyeRechargePercentage = cfg.getFloat("enderEyeRechargePercentage", category, 0.45F, 0, 1, "");

        category = "seedbox";
        cfg.setCategoryRequiresMcRestart(category, true);
        seedBox_recipe = cfg.getBoolean("recipe", category, true, "");
        seedBox_autoCrops = cfg.getBoolean("autoCrops", category, true, "");
        seedBox_autoCompost = cfg.getBoolean("autoCompost", category, true, "");
        seedBox_autoBoneMeal = cfg.getBoolean("autoBoneMeal", category, true, "also controls auto fertilizer");
        seedBox_behaviourProviderEntry = cfg.getBoolean("behaviourProviderEntry", category, true, "DON'T CHANGE THIS IF YOU DON'T KNOW WHAT IT IS!");
        seedBox_autoFeed = cfg.getBoolean("autoFeed", category, true, "");
        seedBox_autoFeedItems = Arrays.asList(cfg.getStringList(
                "autoFeedItems",category,new String[]{
                        Items.WHEAT_SEEDS.getRegistryName().toString(),
                        Items.PUMPKIN_SEEDS.getRegistryName().toString(),
                        Items.BEETROOT_SEEDS.getRegistryName().toString(),
                        Items.MELON_SEEDS.getRegistryName().toString(),
                        Items.WHEAT.getRegistryName().toString(),
                        Items.GOLDEN_CARROT.getRegistryName().toString(),
                        Items.GOLDEN_APPLE.getRegistryName().toString(),
                        Items.CARROT.getRegistryName().toString(),
                        Items.BEETROOT.getRegistryName().toString(),
                        Items.POTATO.getRegistryName().toString(),
                        Items.FISH.getRegistryName().toString(),
                        Items.PORKCHOP.getRegistryName().toString(),
                        Items.COOKED_PORKCHOP.getRegistryName().toString(),
                        Items.BEEF.getRegistryName().toString(),
                        Items.COOKED_BEEF.getRegistryName().toString(),
                        Items.CHICKEN.getRegistryName().toString(),
                        Items.COOKED_CHICKEN.getRegistryName().toString(),
                        Items.MUTTON.getRegistryName().toString(),
                        Items.COOKED_MUTTON.getRegistryName().toString(),
                        Items.RABBIT.getRegistryName().toString(),
                        Items.COOKED_RABBIT.getRegistryName().toString(),
                        Blocks.YELLOW_FLOWER.getRegistryName().toString(),
                        Blocks.HAY_BLOCK.getRegistryName().toString()
                },"list of item ids"));
        seedBox_autoFeedBlacklist=Arrays.asList(cfg.getStringList("autoFeedBlacklist",category,new String[0],"Entity registry names e.g.: 'minecraft:chicken'"));

        category = "inventoryProxies";
        cfg.setCategoryRequiresMcRestart(category, true);
        inventoryProxies_regular = cfg.getBoolean("regular", category, true, "needed to craft the others");
        inventoryProxies_sided = cfg.getBoolean("sided", category, true, "");
        inventoryProxies_filtered = cfg.getBoolean("filtered", category, true, "");
        inventoryProxies_chainLimit = cfg.getInt("chainLimit", category, 32, 0, 256, "");
        inventoryProxies_transmitter = cfg.getBoolean("transmitter", category, true, "");
        inventoryProxies_receiver = cfg.getBoolean("receiver", category, true, "");
        inventoryProxies_blacklist=Arrays.asList(cfg.getStringList("blacklist",category,new String[0],"Blocks that will not be mimiced by proxies or the item transmitter e.g.: 'awesomemod:awesomeblock'"));

        category = "channelModules";
        cfg.setCategoryRequiresMcRestart(category, true);
        channelModules_player = cfg.getBoolean("player", category, true, "");
        channelModules_color = cfg.getBoolean("color", category, true, "");

        category = "transmutationTome";
        cfg.setCategoryRequiresMcRestart(category, true);
        transmutationTome_recipe = cfg.getBoolean("recipe", category, true, "");
        transmutationTome_oreDoubling = cfg.getBoolean("oreDoubling", category, true, "");
        transmutationTome_oreUpgrade = cfg.getBoolean("oreUpgrade", category, true, "");
        transmutationTome_oreDowngrade = cfg.getBoolean("oreDowngrade", category, true, "");
        transmutationTome_oreOther = cfg.getBoolean("oreOther", category, true, "");
        transmutationTome_logVariations = cfg.getBoolean("logVariations", category, true, "");
        transmutationTome_stoneVariations = cfg.getBoolean("stoneVariations", category, true, "");
        transmutationTome_cropMutation = cfg.getBoolean("cropMutation", category, true, "");
        transmutationTome_dirtMutation = cfg.getBoolean("dirtMutation", category, true, "");
        transmutationTome_purification = cfg.getBoolean("purification", category, true, "");
        transmutationTome_flowerVariatons = cfg.getBoolean("flowerVariatons", category, true, "");
        transmutationTome_misc = cfg.getBoolean("misc", category, true, "");

        category = "specialArrows";
        cfg.setCategoryRequiresMcRestart(category, true);
        specialArrows_creeper = cfg.getBoolean("creeper", category, true, "");
        specialArrows_blast = cfg.getBoolean("blast", category, true, "");
        specialArrows_slime = cfg.getBoolean("slime", category, true, "");

        category = "hoverPad";
        cfg.setCategoryRequiresMcRestart(category, true);
        hoverPad_recipe = cfg.getBoolean("recipe", category, true, "");
        hoverPad_range = cfg.getInt("range", category, 15, 1, 255, "");

        category = "mobCatcher";
        mobCatcher_recipe = cfg.getBoolean("recipe", category, true, "");
        mobCatcher_blacklist = Arrays.asList(cfg.getStringList("blacklist",category,new String[]{"minecraft:wither","minecraft:ender_dragon"},"Entity registry names e.g.: 'minecraft:chicken'"));

        if (cfg.hasChanged())
            cfg.save();
    }
}
