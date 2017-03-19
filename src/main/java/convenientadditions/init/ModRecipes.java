package convenientadditions.init;

import convenientadditions.item.tools.adventurersPickaxe.RecipeAdventurersPickaxeRepair;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init() {
        if (ModConfig.ironWrench)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemIronWrench, 1),
                    "n n",
                    " i ",
                    "n n",
                    'i', "ingotIron",
                    'n', "nuggetIron"));

        if (ModConfig.transmutationTome_recipe)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemTransmutationTome, 1), Items.BOOK, Items.BLAZE_ROD, Items.ENDER_EYE, Items.WHEAT_SEEDS));

        if(ModConfig.dislocationCore)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemDislocationCore,3),
                    " e ",
                    "lrl",
                    " e ",
                    'e', "enderpearl",
                    'r', "dustRedstone",
                    'l', new ItemStack(Items.DYE,1,4)));

        initCompost();
        initBlocks();
        initMachines();
        initArrows();
        initChargeItems();
        initBaubles();
        initInventoryProxies();
        initModules();
        initTreeTap();
        initAdvPickaxe();
        initMCDs();
    }

    private static void initCompost() {
        if (ModConfig.composter_recipe)
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
        if (ModConfig.platform){
            for(EnumDyeColor c:EnumDyeColor.values()){
                String dye="dye"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                String pane="paneGlass"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                String block="blockGlass"+c.getUnlocalizedName().substring(0,1).toUpperCase()+c.getUnlocalizedName().substring(1);
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.platformBlock, 4, c.getMetadata()),
                        "p p",
                        " d ",
                        "p p",
                        'd', dye,
                        'p', pane));
                ItemStack w=new ItemStack(ModBlocks.platformBlock,1, OreDictionary.WILDCARD_VALUE);
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.platformBlock, 4, c.getMetadata()),w,w,w,w,dye));
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

        if (ModConfig.powderKeg)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
                    "psp",
                    "pgp",
                    "psp",
                    'p', "plankWood",
                    's', "slabWood",
                    'g', "gunpowder"));

        if (ModConfig.seedBox_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.seedBoxBlock,
                    "tpt",
                    "php",
                    "tpt",
                    'h', Blocks.HOPPER,
                    'p', "plankWood",
                    't', "ingotIron"));

        if (ModConfig.displayCase)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.displayCaseBlock,2),
                    "ppp",
                    "p p",
                    "sss",
                    's', "slabWood",
                    'p', "paneGlassColorless"));
    }

    private static void initMachines(){
        if (ModConfig.machineBlock)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineBlock, 4),
                    "sbs",
                    "bpb",
                    "sbs",
                    's', "stone",
                    'b', Blocks.IRON_BARS,
                    'p', Blocks.PISTON));

        if (ModConfig.hoverPad_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.hoverPadBlock, 1),
                    "iei",
                    "pmp",
                    "scs",
                    'i', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'p', Blocks.PISTON,
                    'm', ModBlocks.machineBlock,
                    's', "stone",
                    'c', Items.COMPARATOR));

        if (ModConfig.blastPad)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blastPadBlock, 1),
                    "iki",
                    "dmd",
                    "scs",
                    'i', "ingotIron",
                    'd', Blocks.DISPENSER,
                    'k', ModItems.itemSlimeKit,
                    's', "stone",
                    'm', ModBlocks.machineBlock,
                    'c', Items.COMPARATOR));

        if (ModConfig.playerInterface)
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

        if (ModConfig.setProvider)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.setProviderBlock),
                    "ihi",
                    "cmc",
                    "shs",
                    'i', "ingotIron",
                    'h', Blocks.HOPPER,
                    'c', Items.COMPARATOR,
                    'm', ModBlocks.machineBlock,
                    's', "stone"));

        if (ModConfig.storageMatrix)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.storageMatrixBlock),
                    "ece",
                    "cmc",
                    "ici",
                    'i', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'm', ModBlocks.machineBlock,
                    'c', "chestWood"));

        if (ModConfig.jumpPad)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jumpPadBlock,2),
                    "pep",
                    "dmd",
                    "sgs",
                    'g', "ingotGold",
                    'e', "enderpearl",
                    'm', ModBlocks.machineBlock,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    's', "stone",
                    'd', ModItems.itemDislocationCore));

        if (ModConfig.itemTransmitter)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemTransmitterBlock),
                    "gcg",
                    "dmd",
                    "ses",
                    'g', "ingotGold",
                    'e', Items.ENDER_EYE,
                    'm', ModBlocks.machineBlock,
                    'c', "chest",
                    's', "stone",
                    'd', ModItems.itemDislocationCore));

        if (ModConfig.itemReceiver)
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

        if (ModConfig.proximitySensor)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
                    "sgs",
                    "tmt",
                    "ege",
                    't', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'g', "ingotGold",
                    's', "dustGlowstone",
                    'm', ModBlocks.machineBlock));

        if (ModConfig.inventoryProxies_remote)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.remoteInventoryProxyBlock),
                    "ipi",
                    "dmd",
                    "ipi",
                    'i', "ingotIron",
                    'd', ModItems.itemDislocationCore,
                    'p', ModBlocks.inventoryProxySidedBlock,
                    'm', ModBlocks.machineBlock));
    }

    private static void initChargeItems() {
        if (ModConfig.charge_sunstone)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunstone,
                    "grg",
                    "tdt",
                    "grg",
                    't', "ingotIron",
                    'd', "gemDiamond",
                    'r', "dustRedstone",
                    'g', "dustGlowstone"));

        if (ModConfig.charge_blazingRock)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBlazingRock,
                    "tlt",
                    "bdb",
                    "tgt",
                    't', "nuggetGold",
                    'b', Items.BLAZE_POWDER,
                    'd', "gemDiamond",
                    'l', Items.LAVA_BUCKET,
                    'g', "dustGlowstone"));

        if (ModConfig.enderPlate_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemEnderPlate,
                    "rdr",
                    "bsb",
                    "rdr",
                    'd', ModItems.itemDislocationCore,
                    'b', Items.DRAGON_BREATH,
                    'r', "dustRedstone",
                    's', ModItems.itemObsidianPlate));
    }

    private static void initBaubles() {
        if (ModConfig.baubles_ring_of_sunlight)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunlightRing,
                    "ysy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    's', ModItems.itemSunstone,
                    'y', "string"));

        if (ModConfig.baubles_ring_of_saturation)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSaturationRing,
                    "ygy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    'g', Items.GOLDEN_CARROT,
                    'y', "string"));

        if (ModConfig.baubles_cloud_jar)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemCloudJar,
                    Items.EXPERIENCE_BOTTLE, Items.RABBIT_FOOT, "slimeball", "gunpowder"));

        if (ModConfig.baubles_slime_balloon)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSlimeBalloon,
                    "s",
                    "y",
                    "y",
                    's', "blockSlime",
                    'y', "string"));

        if (ModConfig.baubles_cloud_balloon)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemCloudBalloon,
                    Items.GHAST_TEAR,ModItems.itemCloudJar,ModItems.itemSlimeBalloon));

        if (ModConfig.baubles_ender_cloud_balloon)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemEnderCloudBalloon,
                    Items.DRAGON_BREATH,ModItems.itemCloudBalloon,Items.ENDER_EYE));

        if (ModConfig.baubles_wind_gem)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemWindGem,
                    " f ",
                    "ses",
                    " f ",
                    'e', "gemEmerald",
                    's', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("strong_swiftness")),
                    'f', "feather"));

        if (ModConfig.baubles_spiked_sole)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSpikedSole,
                    "lll",
                    "sss",
                    'l', "leather",
                    's', "stickWood"));

        if (ModConfig.baubles_glider)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemGlider,
                    "ysy",
                    "sls",
                    "l l",
                    'y', "string",
                    'l', "leather",
                    's', "stickWood"));

        if (ModConfig.baubles_valkyrie_wings)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemValkyrieWings,
                    "fwf",
                    "bgb",
                    "fsf",
                    'f', "feather",
                    'w', ModItems.itemWindGem,
                    'b', Items.DRAGON_BREATH,
                    'g', ModItems.itemGlider,
                    's', ModItems.itemSpikedSole));

        if (ModConfig.baubles_amulet_of_breath)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBreathAmulet,
                    "yty",
                    "t t",
                    "ypy",
                    't', "ingotIron",
                    'p', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("water_breathing")),
                    'y', "string"));

        if (ModConfig.baubles_flippers)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFlippers,
                    "l l",
                    "c c",
                    "c c",
                    'l', "leather",
                    'c', new ItemStack(Items.DYE,1,2)));

        if (ModConfig.baubles_flowing_water_rune)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFlowingWaterRune,
                    "rpr",
                    "wsw",
                    "rpr",
                    'p', "gemPrismarine",
                    's', "stone",
                    'r', "dustRedstone",
                    'w', Items.WATER_BUCKET));

        if (ModConfig.baubles_tide_amulet)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemTideAmulet,
                    "sbs",
                    "crc",
                    "sfs",
                    's', "gemPrismarine",
                    'c', "dustPrismarine",
                    'b', ModItems.itemBreathAmulet,
                    'r', ModItems.itemFlowingWaterRune,
                    'f', ModItems.itemFlippers));

        if (ModConfig.baubles_fireproof_cloak)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemFireproofCloak,
                    "iwy",
                    "swl",
                    "swl",
                    'i',new ItemStack(Items.DYE,1,0),
                    'w',Blocks.WOOL,
                    'y',"string",
                    's',"slimeball",
                    'l',"leather"));

        if (ModConfig.baubles_nether_talisman)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemNetherTalisman,
                    "paper","dyeRed","dyeBlack","feather","string"));

        if (ModConfig.baubles_nether_cloak)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.itemNetherCloak,
                    ModItems.itemNetherTalisman, ModItems.itemFireproofCloak, Items.MAGMA_CREAM, Items.GHAST_TEAR));
    }

    private static void initInventoryProxies() {
        if (ModConfig.inventoryProxies_regular)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock, 4),
                    "shs",
                    "pcp",
                    "shs",
                    's', "stickWood",
                    'p', "plankWood",
                    'h', Blocks.HOPPER,
                    'c', Blocks.CHEST));

        if (ModConfig.inventoryProxies_sided)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxySidedBlock), ModBlocks.inventoryProxyBlock));
        if (ModConfig.inventoryProxies_regular)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock), ModBlocks.inventoryProxySidedBlock));
        if (ModConfig.inventoryProxies_filtered)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyFilteredBlock), ModBlocks.inventoryProxyBlock, Items.COMPARATOR));
    }

    private static void initModules() {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemObsidianPlate, 6), Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE));
        if (ModConfig.channelModules_player)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModulePlayer), ModItems.itemObsidianPlate, Items.ENDER_EYE, new ItemStack(Items.SKULL, 1, 1)));
        if (ModConfig.channelModules_color)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleColor), ModItems.itemObsidianPlate, Items.ENDER_EYE, "dye", "dye", "dye"));
        if (ModConfig.moduleLocation)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleLocation), ModItems.itemObsidianPlate, Items.ENDER_EYE, "dustRedstone", "dustGlowstone"));
    }

    private static void initTreeTap() {
        if (ModConfig.treetap)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemSapBottle), Items.GLASS_BOTTLE, new ItemStack(Items.DYE, 1, 2)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.treetapBlock), "ingotIron", "slimeball", "stickWood"));
        if (ModConfig.antidote)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemAntidote), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER), "sap", Blocks.RED_MUSHROOM, Items.BEETROOT));
        if (ModConfig.bandage)
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
        if (ModConfig.specialArrows_creeper) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemCreeperKit, 1),
                    "tgt",
                    "gcg",
                    "tgt",
                    'c', new ItemStack(Items.SKULL, 1, 4),
                    'g', "gunpowder",
                    't', Blocks.TNT));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 0), ModItems.itemCreeperKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }

        if (ModConfig.specialArrows_blast) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemBlastKit, 1),
                    "tft",
                    "fgf",
                    "tft",
                    'f', Items.FLINT,
                    'g', "gunpowder",
                    't', Blocks.TNT));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 1), ModItems.itemBlastKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }

        if (ModConfig.specialArrows_slime) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemSlimeKit, 1),
                    "gsg",
                    "sts",
                    "gsg",
                    's', "slimeball",
                    'g', "gunpowder",
                    't', Blocks.TNT));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemLaunchingArrow, 8, 2), ModItems.itemSlimeKit, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW));
        }
    }

    private static void initMCDs() {
        if(ModConfig.mobCatcher_recipe){
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
}
