package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.item.*;
import convenientadditions.item.adventurersPickaxe.CustomModelMeshAdventurersPickaxe;
import convenientadditions.item.adventurersPickaxe.ItemAdventurersPickaxe;
import convenientadditions.item.channelModule.ItemPlayerChannelModule;
import convenientadditions.item.channelModule.color.ItemColorChannelModule;
import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.charge.enderPlate.ItemEnderPlate;
import convenientadditions.item.tools.ItemIronWrench;
import convenientadditions.item.transmutationTome.ItemTransmutationTome;
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

@GameRegistry.ObjectHolder(ModConstants.Mod.MODID)
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
    public static final ItemMobCatcher itemMobCatcher = new ItemMobCatcher();
    //channel modules
    public static final ItemPlayerChannelModule itemModulePlayer = new ItemPlayerChannelModule();
    public static final ItemColorChannelModule itemModuleColor = new ItemColorChannelModule();
    //
    public static final ItemLocationModule itemModuleLocation = new ItemLocationModule();
    //dummy
    public static final Item itemDirtChunk = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.dirtChunk).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemObsidianPlate = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.obsidianPlate).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemCreeperKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.creeperKit).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemBlastKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.blastKit).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemSlimeKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.slimeKit).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    //ttools
    public static final ItemIronWrench itemIronWrench = new ItemIronWrench();

    public static void init() {
        //dummy
        registerItem(itemDirtChunk, ModConstants.ItemNames.dirtChunk);
        registerItem(itemObsidianPlate, ModConstants.ItemNames.obsidianPlate);
        registerItem(itemCreeperKit, ModConstants.ItemNames.creeperKit);
        registerItem(itemBlastKit, ModConstants.ItemNames.blastKit);
        registerItem(itemSlimeKit, ModConstants.ItemNames.slimeKit);
        //tools
        registerItem(itemIronWrench, ModConstants.ItemNames.ironWrench);
        //misc
        registerItem(itemFertilizer, ModConstants.ItemNames.fertilizer);
        registerItem(itemCompost, ModConstants.ItemNames.compost);
        registerItem(itemSunstone, ModConstants.ItemNames.sunstone);
        registerItem(itemBlazingRock, ModConstants.ItemNames.blazingRock);
        registerItem(itemEnderPlate, ModConstants.ItemNames.enderPlate);
        registerItem(itemLaunchingArrow, ModConstants.ItemNames.launchingArrow);
        registerItem(itemTransmutationTome, ModConstants.ItemNames.transmutationTome);
        registerItem(itemSapBottle, ModConstants.ItemNames.sapBottle);
        registerItem(itemAntidote, ModConstants.ItemNames.antidote);
        registerItem(itemBandage, ModConstants.ItemNames.bandage);
        registerItem(itemMobCatcher, ModConstants.ItemNames.mobCatcher);
        //channel modules
        registerItem(itemModulePlayer, ModConstants.ItemNames.modulePlayer);
        registerItem(itemModuleColor, ModConstants.ItemNames.moduleColor);
        //
        registerItem(itemModuleLocation, ModConstants.ItemNames.moduleLocation);
        //baubles
        registerItem(itemSunlightRing, ModConstants.ItemNames.sunlightRing);
        registerItem(itemSaturationRing, ModConstants.ItemNames.saturationRing);
        registerItem(itemBreathAmulet, ModConstants.ItemNames.breathAmulet);
        registerItem(itemChargingRing, ModConstants.ItemNames.chargingRing);

        registerItem(itemAdventurersPickaxe, ModConstants.ItemNames.adventurersPickaxe);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelLoader() {
        CustomModelMeshAdventurersPickaxe.initVariants();
        ModelLoader.setCustomMeshDefinition(itemAdventurersPickaxe, new CustomModelMeshAdventurersPickaxe());
        //
        registerModelLocation(itemDirtChunk);
        registerModelLocation(itemObsidianPlate);
        registerModelLocation(itemCreeperKit);
        registerModelLocation(itemBlastKit);
        registerModelLocation(itemSlimeKit);
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
        registerModelLocation(itemMobCatcher, 0, new ModelResourceLocation(itemMobCatcher.getRegistryName().toString().toLowerCase() + "_empty"));
        registerModelLocation(itemMobCatcher, 1, new ModelResourceLocation(itemMobCatcher.getRegistryName().toString().toLowerCase() + "_filled"));
        //channel modules
        registerModelLocation(itemModulePlayer);
        registerModelLocation(itemModuleColor);
        //
        registerModelLocation(itemModuleLocation);
        //baubles
        registerModelLocation(itemSunlightRing);
        registerModelLocation(itemSaturationRing);
        registerModelLocation(itemBreathAmulet);
        registerModelLocation(itemChargingRing);
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
