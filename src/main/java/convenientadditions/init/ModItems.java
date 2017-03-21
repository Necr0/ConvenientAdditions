package convenientadditions.init;

import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.item.*;
import convenientadditions.item.tools.adventurersPickaxe.CustomModelMeshAdventurersPickaxe;
import convenientadditions.item.tools.adventurersPickaxe.ItemAdventurersPickaxe;
import convenientadditions.item.consumable.ItemAntidote;
import convenientadditions.item.consumable.ItemBandage;
import convenientadditions.item.consumable.ItemCompost;
import convenientadditions.item.consumable.ItemFertilizer;
import convenientadditions.item.module.ItemLocationModule;
import convenientadditions.item.module.ItemPlayerChannelModule;
import convenientadditions.item.module.color.ItemColorChannelModule;
import convenientadditions.item.relic.ItemBlazingRock;
import convenientadditions.item.relic.ItemSunstone;
import convenientadditions.item.trinket.*;
import convenientadditions.item.relic.ItemEnderPlate;
import convenientadditions.item.trinket.doubleJump.ItemMultiJumpTrinket;
import convenientadditions.item.tools.mobCatcher.EnumMobCatcherType;
import convenientadditions.item.tools.mobCatcher.ItemMobCatcher;
import convenientadditions.item.tools.ItemIronWrench;
import convenientadditions.item.relic.transmutationTome.ItemTransmutationTome;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    public static final ItemAdventurersPickaxe itemAdventurersPickaxe = new ItemAdventurersPickaxe();
    //
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemBlazingRock itemBlazingRock = new ItemBlazingRock();
    public static final ItemEnderPlate itemEnderPlate = new ItemEnderPlate();
    public static final ItemLaunchingArrow itemLaunchingArrow = new ItemLaunchingArrow();
    public static final ItemTransmutationTome itemTransmutationTome = new ItemTransmutationTome();
    public static final ItemSapBottle itemSapBottle = new ItemSapBottle();
    public static final ItemAntidote itemAntidote = new ItemAntidote();
    public static final ItemBandage itemBandage = new ItemBandage();
    //
    public static final ItemMobCatcher itemMobCatcherRegular = new ItemMobCatcher(ModConstants.ItemNames.mobCatcherRegular, EnumMobCatcherType.DEFAULT);
    public static final ItemMobCatcher itemMobCatcherSuper = new ItemMobCatcher(ModConstants.ItemNames.mobCatcherSuper, EnumMobCatcherType.SUPER);
    public static final ItemMobCatcher itemMobCatcherHyper = new ItemMobCatcher(ModConstants.ItemNames.mobCatcherHyper, EnumMobCatcherType.HYPER);
    public static final ItemMobCatcher itemMobCatcherMega = new ItemMobCatcher(ModConstants.ItemNames.mobCatcherMega, EnumMobCatcherType.MEGA);
    public static final ItemMobCatcher itemMobCatcherMaster = new ItemMobCatcher(ModConstants.ItemNames.mobCatcherMaster, EnumMobCatcherType.MASTER);
    //modules
    public static final ItemPlayerChannelModule itemModulePlayer = new ItemPlayerChannelModule();
    public static final ItemColorChannelModule itemModuleColor = new ItemColorChannelModule();
    public static final ItemLocationModule itemModuleLocation = new ItemLocationModule();
    //dummy
    public static final CAItem itemDirtChunk = new CAItem(ModConstants.ItemNames.dirtChunk).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemObsidianPlate = new CAItem(ModConstants.ItemNames.obsidianPlate).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemCreeperKit = new CAItem(ModConstants.ItemNames.creeperKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemBlastKit = new CAItem(ModConstants.ItemNames.blastKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemSlimeKit = new CAItem(ModConstants.ItemNames.slimeKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemDislocationCore = new CAItem(ModConstants.ItemNames.dislocationCore).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    
    //trinkets
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemWaterTrinket itemBreathAmulet = new ItemWaterTrinket();
    public static final ItemWaterTrinket itemFlippers = new ItemWaterTrinket(ModConstants.ItemNames.flippers,false,.085f,0f);
    public static final ItemWaterTrinket itemFlowingWaterRune = new ItemWaterTrinket(ModConstants.ItemNames.flowingWaterRune,false,0f,1f);
    public static final ItemWaterTrinket itemTideAmulet = new ItemWaterTrinket(ModConstants.ItemNames.tideAmulet,true,.1f,1.2f);
    public static final ItemMultiJumpTrinket itemCloudJar = new ItemMultiJumpTrinket();
    public static final ItemMultiJumpTrinket itemSlimeBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.slimeBalloon,0,.1325f,1f);
    public static final ItemMultiJumpTrinket itemCloudBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.cloudBalloon,1,.1325f,1.75f);
    public static final ItemMultiJumpTrinket itemEnderCloudBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.enderCloudBalloon,2,.2325f,2.6f);
    public static final ItemSpeedTrinket itemWindGem = new ItemSpeedTrinket();
    public static final ItemSpeedTrinket itemSpikedSole = new ItemSpeedTrinket(ModConstants.ItemNames.spikedSole,0f,.5f,0f,0f);
    public static final ItemSpeedTrinket itemGlider = new ItemSpeedTrinket(ModConstants.ItemNames.glider,0f,0f,-.375f,.1f);
    public static final ItemSpeedTrinket itemValkyrieWings = new ItemSpeedTrinket(ModConstants.ItemNames.valkyrieWings,.12f,.5f,-.295f,.135f);
    public static final ItemNetherTrinket itemFireproofCloak = new ItemNetherTrinket();
    public static final ItemNetherTrinket itemNetherTalisman = new ItemNetherTrinket(ModConstants.ItemNames.netherTalisman,false,true,0f,0f,true);
    public static final ItemNetherTrinket itemNetherCloak = new ItemNetherTrinket(ModConstants.ItemNames.netherCloak,true,true,.5f,.5f,true);
    //ttools
    public static final ItemIronWrench itemIronWrench = new ItemIronWrench();

    public static void init() {
        //dummy
        registerItem(itemDirtChunk);
        registerItem(itemObsidianPlate);
        registerItem(itemCreeperKit);
        registerItem(itemBlastKit);
        registerItem(itemSlimeKit);
        registerItem(itemDislocationCore);
        //tools
        registerItem(itemIronWrench);
        //misc
        registerItem(itemFertilizer);
        registerItem(itemCompost);
        registerItem(itemSunstone);
        registerItem(itemBlazingRock);
        registerItem(itemEnderPlate);
        registerItem(itemLaunchingArrow);
        registerItem(itemTransmutationTome);
        registerItem(itemSapBottle);
        registerItem(itemAntidote);
        registerItem(itemBandage);
        //
        registerItem(itemMobCatcherRegular);
        registerItem(itemMobCatcherSuper);
        registerItem(itemMobCatcherHyper);
        registerItem(itemMobCatcherMega);
        registerItem(itemMobCatcherMaster);
        //channel modules
        registerItem(itemModulePlayer);
        registerItem(itemModuleColor);
        //
        registerItem(itemModuleLocation);
        //trinkets
        registerItem(itemSunlightRing);
        registerItem(itemSaturationRing);
        registerItem(itemBreathAmulet);
        registerItem(itemFlippers);
        registerItem(itemFlowingWaterRune);
        registerItem(itemTideAmulet);
        registerItem(itemCloudJar);
        registerItem(itemSlimeBalloon);
        registerItem(itemCloudBalloon);
        registerItem(itemEnderCloudBalloon);
        registerItem(itemWindGem);
        registerItem(itemSpikedSole);
        registerItem(itemGlider);
        registerItem(itemValkyrieWings);
        registerItem(itemFireproofCloak);
        registerItem(itemNetherTalisman);
        registerItem(itemNetherCloak);

        registerItem(itemAdventurersPickaxe);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader() {
        CustomModelMeshAdventurersPickaxe.initVariants();
        ModelLoader.setCustomMeshDefinition(itemAdventurersPickaxe, new CustomModelMeshAdventurersPickaxe());
        ItemMobCatcher.initModels();
        //
        registerModelLocation(itemDirtChunk);
        registerModelLocation(itemObsidianPlate);
        registerModelLocation(itemCreeperKit);
        registerModelLocation(itemBlastKit);
        registerModelLocation(itemSlimeKit);
        registerModelLocation(itemDislocationCore);
        //tools
        registerModelLocation(itemIronWrench);
        //misc
        registerModelLocation(itemFertilizer);
        registerModelLocation(itemSunstone, 0, new ModelResourceLocation(itemSunstone.getRegistryName().toString().toLowerCase() + "_inactive"));
        registerModelLocation(itemSunstone, 1, new ModelResourceLocation(itemSunstone.getRegistryName().toString().toLowerCase() + "_active"));
        registerIndependentModelLocation(itemCompost, new ModelResourceLocation(itemCompost.getRegistryName().toString().toLowerCase(), "inventory"));
        registerModelLocation(itemBlazingRock);
        registerModelLocation(itemEnderPlate, 0, new ModelResourceLocation(itemEnderPlate.getRegistryName().toString().toLowerCase() + "_inactive", "inventory"));
        registerModelLocation(itemEnderPlate, 1, new ModelResourceLocation(itemEnderPlate.getRegistryName().toString().toLowerCase() + "_active", "inventory"));
        registerVariants(itemLaunchingArrow, itemLaunchingArrow.getModelResourceLocations());
        registerModelLocation(itemTransmutationTome);
        registerVariants(itemSapBottle, itemSapBottle.getModelResourceLocations());
        registerModelLocation(itemAntidote);
        registerModelLocation(itemBandage);
        //modules
        registerModelLocation(itemModulePlayer);
        registerModelLocation(itemModuleColor);
        registerModelLocation(itemModuleLocation);
        //trinkets
        registerModelLocation(itemSunlightRing);
        registerModelLocation(itemSaturationRing);
        registerModelLocation(itemBreathAmulet);
        registerModelLocation(itemFlippers);
        registerModelLocation(itemFlowingWaterRune);
        registerModelLocation(itemTideAmulet);
        registerModelLocation(itemCloudJar);
        registerModelLocation(itemSlimeBalloon);
        registerModelLocation(itemCloudBalloon);
        registerModelLocation(itemEnderCloudBalloon);
        registerModelLocation(itemWindGem);
        registerModelLocation(itemSpikedSole);
        registerModelLocation(itemGlider);
        registerModelLocation(itemValkyrieWings);
        registerModelLocation(itemFireproofCloak);
        registerModelLocation(itemNetherTalisman);
        registerModelLocation(itemNetherCloak);
    }

    public static void registerItem(Item item) {
        GameRegistry.register(item);
    }

    public static void registerItem(Item item, String registryName) {
        if (item.getRegistryName() == null)
            item.setRegistryName(registryName);
        GameRegistry.register(item);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString().toLowerCase(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerVariants(Item item, ModelResourceLocation[] locations) {
        for (int i = 0; i < locations.length; i++) {
            registerModelLocation(item, i, locations[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item, int damage, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, damage, location);
    }

    @SideOnly(Side.CLIENT)
    public static void registerIndependentModelLocation(Item item, ModelResourceLocation location) {
        ModelLoader.setCustomMeshDefinition(item, new SimpleItemMeshDefinition(location));
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemBlockModel(Block block) {
        ModItems.registerModelLocation(ItemBlock.getItemFromBlock(block));
    }

    @SideOnly(Side.CLIENT)
    public static class SimpleItemMeshDefinition implements ItemMeshDefinition {
        ModelResourceLocation location;

        public SimpleItemMeshDefinition(ModelResourceLocation location) {
            this.location = location;
        }

        @Override
        public ModelResourceLocation getModelLocation(ItemStack stack) {
            return location;
        }
    }
}
