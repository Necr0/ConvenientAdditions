package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.item.ItemSlimeBucket;
import convenientadditions.item.module.text.ItemTextChannelModule;
import convenientadditions.item.misc.backpack.ItemBackpack;
import convenientadditions.item.relic.ItemPortableEnderRift;
import convenientadditions.potion.PotionLumbering;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.CAItemPotion;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.item.ItemCheeseBucket;
import convenientadditions.item.misc.ItemLaunchingArrow;
import convenientadditions.item.misc.ItemSapBottle;
import convenientadditions.item.consumable.ItemAntidote;
import convenientadditions.item.consumable.ItemBandage;
import convenientadditions.item.consumable.ItemCompost;
import convenientadditions.item.consumable.ItemFertilizer;
import convenientadditions.item.module.ItemLocationModule;
import convenientadditions.item.module.ItemPlayerChannelModule;
import convenientadditions.item.module.color.ItemColorChannelModule;
import convenientadditions.item.relic.ItemBlazingRock;
import convenientadditions.item.relic.ItemEnderPlate;
import convenientadditions.item.relic.ItemSunstone;
import convenientadditions.item.relic.transmutationTome.ItemTransmutationTome;
import convenientadditions.item.soulGem.ItemSoulGem;
import convenientadditions.item.tools.ItemIronWrench;
import convenientadditions.item.tools.adventurersPickaxe.CustomModelMeshAdventurersPickaxe;
import convenientadditions.item.tools.adventurersPickaxe.ItemAdventurersPickaxe;
import convenientadditions.item.tools.mobCatcher.EnumMobCatcherType;
import convenientadditions.item.tools.mobCatcher.ItemMobCatcher;
import convenientadditions.item.trinket.*;
import convenientadditions.item.trinket.doubleJump.ItemMultiJumpTrinket;
import convenientadditions.potion.PotionThorns;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
    public static final ItemSoulGem itemSoulGem = new ItemSoulGem();
    public static final Item itemCheese = new ItemFood(1,0.175f,false).setAlwaysEdible().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.cheese).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(ModConstants.ItemNames.cheese);
    public static final ItemCheeseBucket itemCheeseBucket = new ItemCheeseBucket();
    public static final Item itemCheeseSandwich = new ItemFood(4,0.475f,false).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.cheeseSandwich).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(ModConstants.ItemNames.cheeseSandwich);
    public static final ItemBackpack itemBackpack = new ItemBackpack();
    public static final ItemPortableEnderRift itemPortableEnderRift = new ItemPortableEnderRift();
    public static final CAItemPotion itemPotionLumbering = new CAItemPotion(ModConstants.ItemNames.potionLumbering,new PotionEffect(PotionLumbering.INSTANCE,4200,0));
    public static final CAItemPotion itemPotionThorns = new CAItemPotion(ModConstants.ItemNames.potionThorns,new PotionEffect(PotionThorns.INSTANCE,2100,0));
    public static final ItemSlimeBucket itemSlimeBucket = new ItemSlimeBucket();
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
    public static final ItemTextChannelModule itemModuleText = new ItemTextChannelModule();
    //dummy
    public static final CAItem itemDirtChunk = new CAItem(ModConstants.ItemNames.dirtChunk).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemObsidianPlate = new CAItem(ModConstants.ItemNames.obsidianPlate).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemCreeperKit = new CAItem(ModConstants.ItemNames.creeperKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemBlastKit = new CAItem(ModConstants.ItemNames.blastKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemSlimeKit = new CAItem(ModConstants.ItemNames.slimeKit).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemDislocationCore = new CAItem(ModConstants.ItemNames.dislocationCore).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    public static final CAItem itemSpikes = new CAItem(ModConstants.ItemNames.spikes).setDefaultInfo(false).setCategory(EnumItemCategory.CRAFTING_MATERIAL);
    
    //trinkets
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemWaterTrinket itemBreathAmulet = new ItemWaterTrinket();
    public static final ItemWaterTrinket itemFlippers = new ItemWaterTrinket(ModConstants.ItemNames.flippers,false,.085f,0f);
    public static final ItemWaterTrinket itemFlowingWaterRune = new ItemWaterTrinket(ModConstants.ItemNames.flowingWaterRune,false,0f,1f);
    public static final ItemWaterTrinket itemTideAmulet = new ItemWaterTrinket(ModConstants.ItemNames.tideAmulet,true,.1f,1.2f);
    public static final ItemMultiJumpTrinket itemCloudJar = new ItemMultiJumpTrinket();
    public static final ItemMultiJumpTrinket itemSlimeBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.slimeBalloon,0,.1325f,1f,0f);
    public static final ItemMultiJumpTrinket itemCloudBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.cloudBalloon,1,.1325f,1.75f,0f);
    public static final ItemMultiJumpTrinket itemEnderCloudBalloon = new ItemMultiJumpTrinket(ModConstants.ItemNames.enderCloudBalloon,2,.2325f,2.6f,0f);
    public static final ItemMultiJumpTrinket parachute = new ItemMultiJumpTrinket(ModConstants.ItemNames.parachute,0,0f,0f,-.15f);
    public static final ItemSpeedTrinket itemWindGem = new ItemSpeedTrinket();
    public static final ItemSpeedTrinket itemGlider = new ItemSpeedTrinket(ModConstants.ItemNames.glider,0f,.0375f,.06875f);
    public static final ItemSpeedTrinket itemValkyrieWings = new ItemSpeedTrinket(ModConstants.ItemNames.valkyrieWings,.125f,.0375f,.0765875f);
    public static final ItemNetherTrinket itemFireproofCloak = new ItemNetherTrinket();
    public static final ItemNetherTrinket itemNetherTalisman = new ItemNetherTrinket(ModConstants.ItemNames.netherTalisman,false,true,0f,0f,true);
    public static final ItemNetherTrinket itemNetherCloak = new ItemNetherTrinket(ModConstants.ItemNames.netherCloak,true,true,.5f,.5f,true);
    public static final ItemRocketPack itemRocketPack = new ItemRocketPack();
    public static final ItemClimbingClaws itemClimbingClaws = new ItemClimbingClaws();
    public static final ItemClimbingClaws itemSpikedSole = new ItemClimbingClaws(ModConstants.ItemNames.spikedSole,0f,.5f);
    public static final ItemClimbingClaws itemClimbingGear = new ItemClimbingClaws(ModConstants.ItemNames.climbingGear,.25f,.5f);
    public static final ItemMinersBracelet itemMinersBracelet = new ItemMinersBracelet();

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
        registerItem(itemSpikes);
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
        registerItem(itemSoulGem);
        registerItem(itemCheese);
        registerItem(itemCheeseBucket);
        registerItem(itemCheeseSandwich);
        registerItem(itemBackpack);
        registerItem(itemPortableEnderRift);
        registerItem(itemPotionLumbering);
        registerItem(itemPotionThorns);
        registerItem(itemSlimeBucket);
        //
        registerItem(itemMobCatcherRegular);
        registerItem(itemMobCatcherSuper);
        registerItem(itemMobCatcherHyper);
        registerItem(itemMobCatcherMega);
        registerItem(itemMobCatcherMaster);
        //channel modules
        registerItem(itemModulePlayer);
        registerItem(itemModuleColor);
        registerItem(itemModuleText);
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
        registerItem(parachute);
        registerItem(itemWindGem);
        registerItem(itemGlider);
        registerItem(itemValkyrieWings);
        registerItem(itemFireproofCloak);
        registerItem(itemNetherTalisman);
        registerItem(itemNetherCloak);
        registerItem(itemRocketPack);
        registerItem(itemClimbingClaws);
        registerItem(itemSpikedSole);
        registerItem(itemClimbingGear);
        registerItem(itemMinersBracelet);

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
        registerModelLocation(itemSpikes);
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
        registerModelLocation(itemSoulGem);
        registerModelLocation(itemCheese);
        registerModelLocation(itemCheeseBucket);
        registerModelLocation(itemCheeseSandwich);
        registerModelLocation(itemBackpack);
        registerModelLocation(itemPortableEnderRift);
        registerModelLocation(itemPotionLumbering);
        registerModelLocation(itemPotionThorns);
        registerModelLocation(itemSlimeBucket);
        //modules
        registerModelLocation(itemModulePlayer);
        registerModelLocation(itemModuleColor);
        registerModelLocation(itemModuleLocation);
        registerModelLocation(itemModuleText);
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
        registerModelLocation(parachute);
        registerModelLocation(itemWindGem);
        registerModelLocation(itemGlider);
        registerModelLocation(itemValkyrieWings);
        registerModelLocation(itemFireproofCloak);
        registerModelLocation(itemNetherTalisman);
        registerModelLocation(itemNetherCloak);
        registerModelLocation(itemRocketPack);
        registerModelLocation(itemClimbingClaws);
        registerModelLocation(itemSpikedSole);
        registerModelLocation(itemClimbingGear);
        registerModelLocation(itemMinersBracelet);
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
