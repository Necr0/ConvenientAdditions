package convenientadditions.init;

import convenientadditions.api.recipe.ShapedNBTOreRecipe;
import convenientadditions.api.recipe.ShapelessNBTOreRecipe;
import convenientadditions.config.*;
import convenientadditions.item.soulGem.RecipeSoulGem;
import convenientadditions.item.tools.adventurersPickaxe.RecipeAdventurersPickaxeRepair;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init() {
        RecipeSorter.register("ShapedNBTOreRecipe", ShapedNBTOreRecipe.class, Category.SHAPED, "");
        RecipeSorter.register("ShapelessNBTOreRecipe", ShapelessNBTOreRecipe.class, Category.SHAPELESS, "");

        if (ModConfigTools.ironWrench)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemIronWrench, 1),
                    "n n",
                    " i ",
                    "n n",
                    'i', "ingotIron",
                    'n', "nuggetIron"));

        initCompost();
        initBlocks();
        initMachines();
        initArrows();
        initRelics();
        initTrinkets();
        initInventoryProxies();
        initModules();
        initTreeTap();
        initAdvPickaxe();
        initMCDs();
        initMisc();
        initCraftingItems();
        initCheese();
        initPotions();
        initPlatforms();

        if(Loader.isModLoaded("gbook") && ModConfigCompat.gbook_recipe)
            GameRegistry.addRecipe(new ShapelessOreRecipe(GameRegistry.makeItemStack("gbook:guidebook",0,1,"{Book:\"convenientadditions:xml/book.xml\"}"),
                    Items.BOOK,"feather",Items.WHEAT_SEEDS));
    }

    private static void initCraftingItems() {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemObsidianPlate, 6), "obsidian", "obsidian", "stone"));

        if(ModConfigCraftingItems.dislocationCore)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemDislocationCore,3),
                    " e ",
                    "lrl",
                    " e ",
                    'e', "enderpearl",
                    'r', "dustRedstone",
                    'l', new ItemStack(Items.DYE,1,4)));

        if(ModConfigCraftingItems.soulGem){
            RecipeSorter.register("RecipeSoulGem", RecipeSoulGem.class, Category.SHAPELESS, "");
            GameRegistry.addRecipe(new RecipeSoulGem());
        }

        if(ModConfigCraftingItems.spikes)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemSpikes,4),
                    "sss",
                    "hhh",
                    's', "stickWood",
                    'h', "slabWood"));
    }

    private static void initMisc() {
        if(ModConfigMisc.backpack_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemBackpack),
                    "sls",
                    "l l",
                    "lll",
                    'l', "leather",
                    's', "string"));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.SLIME_BALL,3),ModItems.itemSlimeBucket));
    }

    private static void initCompost() {
        if (ModConfigMisc.composter_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composterBlock),
                    "s s",
                    "s s",
                    "ppp",
                    's', "slabWood",
                    'p', "plankWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
                "cc",
                "cc",
                'c', "chunkCompost"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.DIRT),
                "dd",
                "dd",
                'd', "chunkDirt"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.DIRT, "chunkCompost"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.DIRT), ModBlocks.compostSoilBlock));
    }

    private static void initBlocks() {
        if (ModConfigMisc.powderKeg)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
                    "psp",
                    "pgp",
                    "psp",
                    'p', "plankWood",
                    's', "slabWood",
                    'g', "gunpowder"));

        if (ModConfigMisc.seedBox_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.seedBoxBlock,2),
                    "tpt",
                    "php",
                    "tpt",
                    'h', Blocks.HOPPER,
                    'p', "plankWood",
                    't', "ingotIron"));

        if (ModConfigMisc.displayCase)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.displayCase,2),
                    "ppp",
                    "p p",
                    "sss",
                    's', "slabWood",
                    'p', "paneGlassColorless"));

        if(ModConfigBuildingBlocks.ironGolemBlock)
            GameRegistry.addRecipe(new ShapedNBTOreRecipe(new ItemStack(ModBlocks.ironGolemBlock,32),
                    "iii",
                    "isi",
                    "iii",
                    'i', "blockIron",
                    's', ModItems.itemSoulGem.setEntityId(new ItemStack(ModItems.itemSoulGem),"minecraft:villager_golem")));

        if (ModConfigMisc.enderProofBlock)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.enderProofBlock,6),
                    "oio",
                    "iwi",
                    "oio",
                    'o', "obsidian",
                    'i', Blocks.IRON_BARS,
                    'w', Items.WATER_BUCKET));

        if (ModConfigMisc.enderProofGlass)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.enderProofGlass,6),
                    "gig",
                    "iwi",
                    "gig",
                    'g', "blockGlass",
                    'i', Blocks.IRON_BARS,
                    'w', Items.WATER_BUCKET));

        if (ModConfigMisc.workStation)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.workStation),
                    "sss",
                    "pwp",
                    "pcp",
                    's', "cobblestone",
                    'p', "plankWood",
                    'c', "chestWood",
                    'w', "workbench"));

        if (ModConfigMisc.punjiSticks)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.punjiSticks),
                    "ss",
                    "cc",
                    's', ModItems.itemSpikes,
                    'c', new ItemStack(Blocks.STONE_SLAB)));

        if (ModConfigMisc.storageCrate)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.storageCrate),
                    "lll",
                    "lcl",
                    "lll",
                    'l', "logWood",
                    'c', "chestWood"));

        if (ModConfigBuildingBlocks.woodenTile)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.woodenTile,8),
                    "sps",
                    "ppp",
                    "sps",
                    's', "stickWood",
                    'p', "plankWood"));

        if (ModConfigBuildingBlocks.clearGlass)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.clearGlass,4),Blocks.GLASS,Blocks.GLASS,Blocks.GLASS,Blocks.GLASS));
    }

    private static void initPlatforms() {
        if (ModConfigMisc.platform){
            for(EnumDyeColor c:EnumDyeColor.values()){
                String dye="dye"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                String pane="paneGlass"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                String block="blockGlass"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.platform, 4, c.getMetadata()),
                        "p p",
                        " d ",
                        "p p",
                        'd', dye,
                        'p', pane));
                ItemStack w=new ItemStack(ModBlocks.platform,1, OreDictionary.WILDCARD_VALUE);
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.platform, 4, c.getMetadata()),w,w,w,w,dye));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.semiSolidBlock, 4, c.getMetadata()),
                        "b b",
                        " d ",
                        "b b",
                        'd', dye,
                        'b', block));
                w=new ItemStack(ModBlocks.semiSolidBlock,1, OreDictionary.WILDCARD_VALUE);
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.semiSolidBlock, 4, c.getMetadata()),w,w,w,w,dye));
            }
        }
    }

    private static void initMachines(){
        if (ModConfigCraftingItems.machineBlock)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineBlock, 4),
                    "sbs",
                    "bpb",
                    "sbs",
                    's', "stone",
                    'b', Blocks.IRON_BARS,
                    'p', Blocks.PISTON));

        if (ModConfigMachines.hoverPad)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.hoverPad, 1),
                    "iei",
                    "pmp",
                    "scs",
                    'i', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'p', Blocks.PISTON,
                    'm', ModBlocks.machineBlock,
                    's', "stone",
                    'c', Items.COMPARATOR));

        if (ModConfigMachines.blastPad)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blastPad, 1),
                    "iki",
                    "dmd",
                    "scs",
                    'i', "ingotIron",
                    'd', Blocks.DISPENSER,
                    'k', ModItems.itemSlimeKit,
                    's', "stone",
                    'm', ModBlocks.machineBlock,
                    'c', Items.COMPARATOR));

        if (ModConfigMachines.playerInterface)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
                    "php",
                    "gmg",
                    "sds",
                    'd', ModItems.itemDislocationCore,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    'h', Blocks.HOPPER,
                    'm', ModBlocks.machineBlock,
                    'g', "ingotGold",
                    's', "stone"));

        if (ModConfigMachines.setProvider)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.setProviderBlock),
                    "ihi",
                    "cmc",
                    "shs",
                    'i', "ingotIron",
                    'h', Blocks.HOPPER,
                    'c', Items.COMPARATOR,
                    'm', ModBlocks.machineBlock,
                    's', "stone"));

        if (ModConfigMachines.storageMatrix)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.storageMatrix),
                    "ece",
                    "cmc",
                    "ici",
                    'i', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'm', ModBlocks.machineBlock,
                    'c', "chestWood"));

        if (ModConfigMachines.jumpPad_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jumpPad),
                    "pep",
                    "dmd",
                    "sgs",
                    'g', "ingotGold",
                    'e', "enderpearl",
                    'm', ModBlocks.machineBlock,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    's', "stone",
                    'd', ModItems.itemDislocationCore));

        if (ModConfigMachines.itemTransmitter)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemTransmitterBlock),
                    "gcg",
                    "dmd",
                    "ses",
                    'g', "ingotGold",
                    'e', Items.ENDER_EYE,
                    'm', ModBlocks.machineBlock,
                    'c', "chestWood",
                    's', "stone",
                    'd', ModItems.itemDislocationCore));

        if (ModConfigMachines.itemReceiver)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemReceiverBlock),
                    "ghg",
                    "dmd",
                    "sis",
                    'g', "ingotGold",
                    'i', ModBlocks.inventoryProxyBlock,
                    'm', ModBlocks.machineBlock,
                    'h', Blocks.HOPPER,
                    's', "stone",
                    'd', ModItems.itemDislocationCore));

        if (ModConfigMachines.proximitySensor)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
                    "sgs",
                    "tmt",
                    "ege",
                    't', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'g', "ingotGold",
                    's', "dustGlowstone",
                    'm', ModBlocks.machineBlock));

        if (ModConfigMachines.inventoryProxies_remote)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.remoteInventoryProxy),
                    "ipi",
                    "dmd",
                    "ipi",
                    'i', "ingotIron",
                    'd', ModItems.itemDislocationCore,
                    'p', ModBlocks.inventoryProxySidedBlock,
                    'm', ModBlocks.machineBlock));

        if (ModConfigMachines.ironFarm)
            GameRegistry.addRecipe(new ShapedNBTOreRecipe(new ItemStack(ModBlocks.ironFarm),
                    "gng",
                    "vmv",
                    "idi",
                    'd', "doorWood",
                    'i', "blockIron",
                    'n', Items.DRAGON_BREATH,
                    'm', ModBlocks.machineBlock,
                    'g', ModItems.itemSoulGem.setEntityId(new ItemStack(ModItems.itemSoulGem),"minecraft:villager_golem"),
                    'v', ModItems.itemSoulGem.setEntityId(new ItemStack(ModItems.itemSoulGem),"minecraft:villager")));

        if (ModConfigMachines.autoWorkStation)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.autoWorkStation),
                    "sws",
                    "bmb",
                    "scs",
                    'w', ModBlocks.workStation,
                    'c', "chestWood",
                    's', "stone",
                    'b', Blocks.IRON_BARS,
                    'm', ModBlocks.machineBlock));
    }

    private static void initRelics() {
        if (ModConfigRelics.sunstone)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunstone,
                    "grg",
                    "tdt",
                    "grg",
                    't', "ingotIron",
                    'd', "gemDiamond",
                    'r', "dustRedstone",
                    'g', "glowstone"));

        if (ModConfigRelics.blazingOrb)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBlazingRock,
                    "tlt",
                    "bdb",
                    "tgt",
                    't', "nuggetGold",
                    'b', Items.BLAZE_POWDER,
                    'd', "gemDiamond",
                    'l', Items.LAVA_BUCKET,
                    'g', "dustGlowstone"));

        if (ModConfigRelics.enderPlate)
            GameRegistry.addRecipe(new ShapedNBTOreRecipe(ModItems.itemEnderPlate,
                    "epe",
                    "bsb",
                    "epe",
                    'e', "enderpearl",
                    'b', Items.DRAGON_BREATH,
                    'p', ModItems.itemObsidianPlate,
                    's', ModItems.itemSoulGem.setEntityId(new ItemStack(ModItems.itemSoulGem),"minecraft:shulker")));

        if (ModConfigRelics.portableEnderRift)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemPortableEnderRift,
                    " o ",
                    "ded",
                    " o ",
                    'e', "chestEnder",
                    'd', ModItems.itemDislocationCore,
                    'o', "obsidian"));

        if (ModConfigRelics.transmutationTome_recipe)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemTransmutationTome, 1), Items.BOOK, Items.BLAZE_ROD, Items.ENDER_EYE, Items.WHEAT_SEEDS));
    }

    private static void initTrinkets() {
        if (ModConfigTrinkets.ring_of_sunlight)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunlightRing,
                    "ysy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    's', ModItems.itemSunstone,
                    'y', "string"));

        if (ModConfigTrinkets.ring_of_saturation)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSaturationRing,
                    "ygy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    'g', Items.GOLDEN_CARROT,
                    'y', "string"));

        if (ModConfigTrinkets.cloud_jar)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemCloudJar,
                    Items.EXPERIENCE_BOTTLE, Items.RABBIT_FOOT, "slimeball", "gunpowder"));

        if (ModConfigTrinkets.slime_balloon)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSlimeBalloon,
                    "s",
                    "y",
                    "y",
                    's', "blockSlime",
                    'y', "string"));

        if (ModConfigTrinkets.cloud_balloon)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemCloudBalloon,
                    Items.GHAST_TEAR,ModItems.itemCloudJar,ModItems.itemSlimeBalloon));

        if (ModConfigTrinkets.ender_cloud_balloon)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemEnderCloudBalloon,
                    Items.DRAGON_BREATH,ModItems.itemCloudBalloon,Items.ENDER_EYE));

        if (ModConfigTrinkets.wind_gem)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemWindGem,
                    " f ",
                    "ses",
                    " f ",
                    'e', "gemEmerald",
                    's', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("strong_swiftness")),
                    'f', "feather"));

        if (ModConfigTrinkets.spiked_sole)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSpikedSole,
                    "l l",
                    "s s",
                    'l', "leather",
                    's', ModItems.itemSpikes));

        if (ModConfigTrinkets.glider)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemGlider,
                    "ysy",
                    "sls",
                    "l l",
                    'y', "string",
                    'l', "leather",
                    's', "stickWood"));

        if (ModConfigTrinkets.valkyrie_wings)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemValkyrieWings,
                    "fwf",
                    "bgb",
                    "f f",
                    'f', "feather",
                    'w', ModItems.itemWindGem,
                    'b', Items.DRAGON_BREATH,
                    'g', ModItems.itemGlider));

        if (ModConfigTrinkets.amulet_of_breath)
            GameRegistry.addRecipe(new ShapedNBTOreRecipe(ModItems.itemBreathAmulet,
                    "yty",
                    "t t",
                    "ypy",
                    't', "ingotIron",
                    'p', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("water_breathing")),
                    'y', "string"));

        if (ModConfigTrinkets.flippers)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFlippers,
                    "l l",
                    "c c",
                    "c c",
                    'l', "leather",
                    'c', new ItemStack(Items.DYE,1,2)));

        if (ModConfigTrinkets.flowing_water_rune)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFlowingWaterRune,
                    "rpr",
                    "wsw",
                    "rpr",
                    'p', "gemPrismarine",
                    's', "stone",
                    'r', "dustRedstone",
                    'w', Items.WATER_BUCKET));

        if (ModConfigTrinkets.tide_amulet)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemTideAmulet,
                    "sbs",
                    "crc",
                    "sfs",
                    's', "gemPrismarine",
                    'c', "dustPrismarine",
                    'b', ModItems.itemBreathAmulet,
                    'r', ModItems.itemFlowingWaterRune,
                    'f', ModItems.itemFlippers));

        if (ModConfigTrinkets.fireproof_cloak)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFireproofCloak,
                    "iwy",
                    "swl",
                    "swl",
                    'i',"dyeBlack",
                    'w',new ItemStack(Blocks.WOOL,OreDictionary.WILDCARD_VALUE),
                    'y',"string",
                    's',"slimeball",
                    'l',"leather"));

        if (ModConfigTrinkets.nether_talisman)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemNetherTalisman,
                    "paper","dyeRed","dyeBlack","feather","string"));

        if (ModConfigTrinkets.nether_cloak)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemNetherCloak,
                    ModItems.itemNetherTalisman, ModItems.itemFireproofCloak, Items.MAGMA_CREAM, Items.GHAST_TEAR));

        if (ModConfigTrinkets.rocket_pack)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemRocketPack,
                    "r r",
                    "ele",
                    "r r",
                    'r',Items.FIREWORKS,
                    'e',Items.ENDER_EYE,
                    'l',"leather"));

        if (ModConfigTrinkets.climbing_claws)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemClimbingClaws,
                    "s s",
                    "l l",
                    'l', "leather",
                    's', ModItems.itemSpikes));

        if (ModConfigTrinkets.climbing_gear)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemClimbingGear,ModItems.itemSpikedSole,ModItems.itemClimbingClaws));

        if (ModConfigTrinkets.miners_bracelet)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemMinersBracelet,
                    " p ",
                    "d d",
                    " d ",
                    'd', "gemDiamond",
                    'p', Items.DIAMOND_PICKAXE));

        if (ModConfigTrinkets.parachute)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.parachute,
                    "lll",
                    "l l",
                    "s s",
                    'l', "leather",
                    's', "string"));

    }

    private static void initInventoryProxies() {
        if (ModConfigMisc.inventoryProxies_regular)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock, 4),
                    "shs",
                    "pcp",
                    "shs",
                    's', "stickWood",
                    'p', "plankWood",
                    'h', Blocks.HOPPER,
                    'c', Blocks.CHEST));

        if (ModConfigMisc.inventoryProxies_sided)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxySidedBlock), ModBlocks.inventoryProxyBlock));
        if (ModConfigMisc.inventoryProxies_regular)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock), ModBlocks.inventoryProxySidedBlock));
        if (ModConfigMisc.inventoryProxies_filtered)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyFilteredBlock), ModBlocks.inventoryProxyBlock, Items.COMPARATOR));
    }

    private static void initModules() {
        if (ModConfigModules.modulePlayer)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModulePlayer), ModItems.itemObsidianPlate, Items.ENDER_EYE, new ItemStack(Items.SKULL, 1, 1)));
        if (ModConfigModules.moduleColor)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleColor), ModItems.itemObsidianPlate, Items.ENDER_EYE, "dye", "dye", "dye"));
        if (ModConfigModules.moduleLocation)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleLocation), ModItems.itemObsidianPlate, Items.ENDER_EYE, "dustRedstone", "dustGlowstone"));
        if (ModConfigModules.moduleText)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleText), ModItems.itemObsidianPlate, Items.ENDER_EYE, Items.NAME_TAG));
    }

    private static void initTreeTap() {
        if (ModConfigMisc.treetap){
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemSapBottle), Items.GLASS_BOTTLE, new ItemStack(Items.DYE, 1, 2)));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.treetap), "ingotIron", "slimeball", "stickWood"));
        }
        if (ModConfigConsumables.bandage)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemBandage), "string", "string", "string", "string", "string", "string", "sap"));
    }

    private static void initAdvPickaxe() {
        RecipeSorter.register("RecipeAdventurersPickaxeRepair", RecipeAdventurersPickaxeRepair.class, Category.SHAPELESS, "");
        GameRegistry.addRecipe(new RecipeAdventurersPickaxeRepair());
        GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemAdventurersPickaxe.subitems.get(0), Items.WOODEN_PICKAXE, Items.WHEAT_SEEDS));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemAdventurersPickaxe.subitems.get(1), Items.STONE_PICKAXE, Items.WHEAT_SEEDS));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemAdventurersPickaxe.subitems.get(2), Items.IRON_PICKAXE, Items.WHEAT_SEEDS));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemAdventurersPickaxe.subitems.get(3), Items.DIAMOND_PICKAXE, Items.WHEAT_SEEDS));
    }

    private static void initArrows() {
        if (ModConfigMisc.launchingArrows_creeper) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemCreeperKit, 1),
                    "tgt",
                    "gcg",
                    "tgt",
                    'c', new ItemStack(Items.SKULL, 1, 4),
                    'g', "gunpowder",
                    't', Blocks.TNT));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 0), ModItems.itemCreeperKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }

        if (ModConfigMisc.launchingArrows_blast) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemBlastKit, 1),
                    "tft",
                    "fgf",
                    "tft",
                    'f', Items.FLINT,
                    'g', "gunpowder",
                    't', Blocks.TNT));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 1), ModItems.itemBlastKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemSlimeKit, 1),
                "gsg",
                "sts",
                "gsg",
                's', "slimeball",
                'g', "gunpowder",
                't', Blocks.TNT));
        if (ModConfigMisc.launchingArrows_slime) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 2), ModItems.itemSlimeKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }
    }

    private static void initMCDs() {
        if(ModConfigTools.mobCatcher_recipe){
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemMobCatcherRegular,1),
                    "ysy",
                    "sds",
                    "ysy",
                    'd', ModItems.itemDislocationCore,
                    'y', "string",
                    's', "slimeball"));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemMobCatcherSuper,1),
                    "pip",
                    "idi",
                    "pip",
                    'd', ModItems.itemDislocationCore,
                    'i', "ingotIron",
                    'p', "gemPrismarine"));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemMobCatcherHyper,1),
                    "isi",
                    "bdb",
                    "isi",
                    'd', ModItems.itemDislocationCore,
                    'i', "ingotIron",
                    'b', Blocks.IRON_BARS,
                    's', Items.SHULKER_SHELL));
        }
    }

    private static void initCheese() {
        if(ModConfigConsumables.cheese){
            GameRegistry.addSmelting(Items.MILK_BUCKET,new ItemStack(ModItems.itemCheeseBucket),0.5f);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemCheese,2),ModItems.itemCheeseBucket));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemCheeseSandwich,2),"cheese",Items.BREAD));
        }
        if(ModConfigBuildingBlocks.cheeseBlock){
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.cheeseBlock,1),ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese,ModItems.itemCheese));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemCheese,9),ModBlocks.cheeseBlock));
        }
    }

    private static void initPotions(){
        if(ModConfigConsumables.potion_lumbering)
            GameRegistry.addRecipe(new ShapelessNBTOreRecipe(new ItemStack(ModItems.itemPotionLumbering),PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER),"cheese","sap","cropPotato"));
        if(ModConfigConsumables.potion_thorns)
            GameRegistry.addRecipe(new ShapelessNBTOreRecipe(new ItemStack(ModItems.itemPotionThorns),PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER),Items.SPIDER_EYE,Items.POISONOUS_POTATO,new ItemStack(Items.DYE,1,3)));
        if (ModConfigConsumables.antidote)
            GameRegistry.addRecipe(new ShapelessNBTOreRecipe(new ItemStack(ModItems.itemAntidote), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER), "sap", Blocks.RED_MUSHROOM, Items.BEETROOT));
    }
}
