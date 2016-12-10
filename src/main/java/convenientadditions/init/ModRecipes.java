package convenientadditions.init;

import convenientadditions.item.adventurersPickaxe.RecipeAdventurersPickaxeRepair;
import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.charge.enderPlate.ItemEnderPlate;
import convenientadditions.item.charge.enderPlate.RecipeEnderPlateRecharge;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init() {
        if (ModConfig.ironWrench)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemIronWrench, 1),
                    "i i",
                    " i ",
                    "i i",
                    'i', "ingotIron"));

        if (ModConfig.transmutationTome_recipe)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemTransmutationTome, 1), Items.BOOK, Items.BLAZE_ROD, Items.ENDER_EYE, Items.WHEAT_SEEDS));

        initCompost();
        initArrows();
        initChargeItems();
        initBaubles();
        initInventoryProxies();
        initChannelModules();
        initTreeTap();
        initBlocks();
        initAdvPickaxe();
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
        if (ModConfig.hoverPad_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.hoverPadBlock, 1),
                    "iei",
                    "pgp",
                    "scs",
                    'i', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'p', Blocks.PISTON,
                    'g', "ingotGold",
                    's', "stone",
                    'c', Items.COMPARATOR));

        if (ModConfig.blastPad)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blastPadBlock, 1),
                    "iki",
                    "sds",
                    "scs",
                    'i', "ingotIron",
                    'd', Blocks.DISPENSER,
                    'k', ModItems.itemSlimeKit,
                    's', "stone",
                    'c', Items.COMPARATOR));

        if (ModConfig.platform)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.platformBlock, 4),
                    "bpb",
                    "pep",
                    "bpb",
                    'e', Items.ENDER_EYE,
                    'b', "dyeBlue",
                    'p', "paneGlass"));

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

        if (ModConfig.playerInterface)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
                    "tpt",
                    "geg",
                    "srs",
                    't', "ingotIron",
                    'e', Items.ENDER_PEARL,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    'g', "ingotGold",
                    's', "dustGlowstone",
                    'r', "blockRedstone"));

        if (ModConfig.proximitySensor)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
                    "sgs",
                    "tet",
                    "rgr",
                    't', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'g', "ingotGold",
                    's', "dustGlowstone",
                    'r', "blockRedstone"));

        if (ModConfig.setProvider)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.setProviderBlock),
                    "tht",
                    "bcb",
                    "tht",
                    't', "ingotIron",
                    'h', Blocks.HOPPER,
                    'c', Items.COMPARATOR,
                    'b', Blocks.IRON_BARS));
    }

    private static void initChargeItems() {
        if (ModConfig.charge_sunstone)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemSunstone.FULLY_CHARGED.copy(),
                    "grg",
                    "tdt",
                    "grg",
                    't', "ingotIron",
                    'd', "gemDiamond",
                    'r', "dustRedstone",
                    'g', "dustGlowstone"));

        if (ModConfig.charge_blazingRock)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemBlazingRock.FULLY_CHARGED.copy(),
                    "tlt",
                    "bdb",
                    "tgt",
                    't', "nuggetGold",
                    'b', Items.BLAZE_POWDER,
                    'd', "gemDiamond",
                    'l', Items.LAVA_BUCKET,
                    'g', "dustGlowstone"));

        if (ModConfig.enderPlate_recipe)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemEnderPlate.FULLY_CHARGED.copy(),
                    "rer",
                    "bsb",
                    "rer",
                    'e', Items.ENDER_PEARL,
                    'b', Items.DRAGON_BREATH,
                    'r', "dustRedstone",
                    's', ModItems.itemObsidianPlate));

        if (ModConfig.enderPlate_enderEyeRechargeRecipe) {
            RecipeSorter.register("RecipeEnderPlateRecharge", RecipeEnderPlateRecharge.class, Category.SHAPELESS, "");
            GameRegistry.addRecipe(new RecipeEnderPlateRecharge());
        }
    }

    private static void initBaubles() {
        if (ModConfig.baubles_ring_of_sunlight)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemSunlightRing.FULLY_CHARGED.copy(),
                    "ysy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    's', ModItems.itemSunstone,
                    'y', "string"));

        if (ModConfig.baubles_ring_of_saturation)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemSaturationRing.FULLY_CHARGED.copy(),
                    "ygy",
                    "t t",
                    "yty",
                    't', "ingotIron",
                    'g', Items.GOLDEN_CARROT,
                    'y', "string"));

        if (ModConfig.baubles_amulet_of_breath)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemBreathAmulet.FULLY_CHARGED.copy(),
                    "yty",
                    "t t",
                    "ypy",
                    't', "ingotIron",
                    'p', new ItemStack(Items.POTIONITEM, 1, 0),
                    'y', "string"));

        if (ModConfig.baubles_ring_of_charging)
            GameRegistry.addRecipe(new ShapedOreRecipe(ItemChargingRing.FULLY_CHARGED.copy(),
                    "yry",
                    "gsg",
                    "ygy",
                    'r', "blockRedstone",
                    'g', "dustGlowstone",
                    's', ModItems.itemSunlightRing,
                    'y', "string"));
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

        if (ModConfig.inventoryProxies_transmitter)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemTransmitterBlock, 1),
                    "tot",
                    "epe",
                    "tht",
                    't', "ingotIron",
                    'e', Items.ENDER_EYE,
                    'h', Blocks.HOPPER,
                    'p', ModBlocks.inventoryProxyBlock,
                    'o', ModItems.itemObsidianPlate));

        if (ModConfig.inventoryProxies_receiver)
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemReceiverBlock, 1),
                    "gog",
                    "epe",
                    "ghg",
                    'g', "ingotGold",
                    'e', Items.ENDER_EYE,
                    'h', Blocks.HOPPER,
                    'p', ModBlocks.inventoryProxyBlock,
                    'o', ModItems.itemObsidianPlate));
    }

    private static void initChannelModules() {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemObsidianPlate, 6), Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.STONE));
        if (ModConfig.channelModules_player)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModulePlayer), ModItems.itemObsidianPlate, Items.ENDER_EYE, new ItemStack(Items.SKULL, 1, 1)));
        if (ModConfig.channelModules_color)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleColor), ModItems.itemObsidianPlate, Items.ENDER_EYE, "dye", "dye", "dye"));
    }

    private static void initTreeTap() {
        if (ModConfig.treetap)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemSapBottle), Items.GLASS_BOTTLE, new ItemStack(Items.DYE, 1, 2)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.treetapBlock), "ingotIron", "slimeball", "stickWood"));
        if (ModConfig.antidote)
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemAntidote), new ItemStack(Items.POTIONITEM, 1, 0), "sap", Blocks.RED_MUSHROOM, Items.BEETROOT));
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
}
