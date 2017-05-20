package convenientadditions.init;

import convenientadditions.config.ModConfigGeneral;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModOredict {
    public static void registerOres() {
        OreDictionary.registerOre("soil", ModBlocks.compostSoilBlock);
        OreDictionary.registerOre("soil", Blocks.DIRT);
        OreDictionary.registerOre("soilCompost", ModBlocks.compostSoilBlock);
        OreDictionary.registerOre("soilDirt", Blocks.DIRT);

        OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost, 1, 0));
        OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost, 1, 1));

        OreDictionary.registerOre("chunkDirt", new ItemStack(ModItems.itemDirtChunk, 1, 0));

        OreDictionary.registerOre("sugar", new ItemStack(ModItems.itemSapBottle, 1, 1));
        OreDictionary.registerOre("sugar", new ItemStack(ModItems.itemSapBottle, 1, 2));

        OreDictionary.registerOre("sap", new ItemStack(ModItems.itemSapBottle, 1, 1));
        OreDictionary.registerOre("sap", new ItemStack(ModItems.itemSapBottle, 1, 2));

        OreDictionary.registerOre("cheese", new ItemStack(ModItems.itemCheese));
        OreDictionary.registerOre("blockCheese", new ItemStack(ModBlocks.cheeseBlock));

        if (ModConfigGeneral.sugarOreDictInit) {
            OreDictionary.registerOre("sugar", new ItemStack(Items.SUGAR));
            GameRegistry.addRecipe(new ShapelessOreRecipe(Items.PUMPKIN_PIE, Blocks.PUMPKIN, "egg", "sugar"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(Items.FERMENTED_SPIDER_EYE, Blocks.BROWN_MUSHROOM, Items.SPIDER_EYE, "sugar"));
            GameRegistry.addRecipe(new ShapedOreRecipe(Items.CAKE,
                    "mmm",
                    "ses",
                    "www",
                    'm', Items.MILK_BUCKET,
                    'e', "egg",
                    's', "sugar",
                    'w', "cropWheat"));
        }

        if (ModConfigGeneral.doorOreDictInit) {
            OreDictionary.registerOre("door", Items.OAK_DOOR);
            OreDictionary.registerOre("door", Items.BIRCH_DOOR);
            OreDictionary.registerOre("door", Items.ACACIA_DOOR);
            OreDictionary.registerOre("door", Items.DARK_OAK_DOOR);
            OreDictionary.registerOre("door", Items.JUNGLE_DOOR);
            OreDictionary.registerOre("door", Items.SPRUCE_DOOR);
            OreDictionary.registerOre("door", Items.IRON_DOOR);
            OreDictionary.registerOre("doorWood", Items.OAK_DOOR);
            OreDictionary.registerOre("doorWood", Items.BIRCH_DOOR);
            OreDictionary.registerOre("doorWood", Items.ACACIA_DOOR);
            OreDictionary.registerOre("doorWood", Items.DARK_OAK_DOOR);
            OreDictionary.registerOre("doorWood", Items.JUNGLE_DOOR);
            OreDictionary.registerOre("doorWood", Items.SPRUCE_DOOR);
        }
    }
}
