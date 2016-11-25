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
    //channel modules
    public static final ItemPlayerChannelModule itemModulePlayer = new ItemPlayerChannelModule();
    public static final ItemColorChannelModule itemModuleColor = new ItemColorChannelModule();
    //dummy
    public static final Item itemDirtChunk = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.dirtChunkItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemObsidianPlate = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.obsidianPlateItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemCreeperKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.creeperKitItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemBlastKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.blastKitItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemSlimeKit = new Item().setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.slimeKitItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    //ttools
    public static final ItemIronWrench itemIronWrench = new ItemIronWrench();

    public static void init() {
        //dummy
        registerItem(itemDirtChunk, ModConstants.ItemNames.dirtChunkItemName);
        registerItem(itemObsidianPlate, ModConstants.ItemNames.obsidianPlateItemName);
        registerItem(itemCreeperKit, ModConstants.ItemNames.creeperKitItemName);
        registerItem(itemBlastKit, ModConstants.ItemNames.blastKitItemName);
        registerItem(itemSlimeKit, ModConstants.ItemNames.slimeKitItemName);
        //tools
        registerItem(itemIronWrench, ModConstants.ItemNames.ironWrenchItemName);
        //misc
        registerItem(itemFertilizer, ModConstants.ItemNames.fertilizerItemName);
        registerItem(itemCompost, ModConstants.ItemNames.compostItemName);
        registerItem(itemSunstone, ModConstants.ItemNames.sunstoneItemName);
        registerItem(itemBlazingRock, ModConstants.ItemNames.blazingRockItemName);
        registerItem(itemEnderPlate, ModConstants.ItemNames.enderPlateItemName);
        registerItem(itemLaunchingArrow, ModConstants.ItemNames.launchingArrowItemName);
        registerItem(itemTransmutationTome, ModConstants.ItemNames.transmutationTomeItemName);
        registerItem(itemSapBottle, ModConstants.ItemNames.sapBottleItemName);
        registerItem(itemAntidote, ModConstants.ItemNames.antidoteItemName);
        registerItem(itemBandage, ModConstants.ItemNames.bandageItemName);
        //channel modules
        registerItem(itemModulePlayer, ModConstants.ItemNames.modulePlayerItemName);
        registerItem(itemModuleColor, ModConstants.ItemNames.moduleColorItemName);
        //baubles
        registerItem(itemSunlightRing, ModConstants.ItemNames.sunlightRingItemName);
        registerItem(itemSaturationRing, ModConstants.ItemNames.saturationRingItemName);
        registerItem(itemBreathAmulet, ModConstants.ItemNames.breathAmuletItemName);
        registerItem(itemChargingRing, ModConstants.ItemNames.chargingRingItemName);

        registerItem(itemAdventurersPickaxe, ModConstants.ItemNames.adventurersPickaxeItemName);

        initModelLoader();
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
        registerModelLocation(itemSunstone, 0, new ModelResourceLocation(itemSunstone.getRegistryName() + "_inactive"));
        registerModelLocation(itemSunstone, 1, new ModelResourceLocation(itemSunstone.getRegistryName() + "_active"));
        registerIndependentModelLocation(itemCompost, new ModelResourceLocation(itemCompost.getRegistryName(), "inventory"));
        registerModelLocation(itemBlazingRock);
        registerModelLocation(itemEnderPlate, 0, new ModelResourceLocation(itemEnderPlate.getRegistryName() + "_inactive", "inventory"));
        registerModelLocation(itemEnderPlate, 1, new ModelResourceLocation(itemEnderPlate.getRegistryName() + "_active", "inventory"));
        registerVariants(itemLaunchingArrow, itemLaunchingArrow.getModelResourceLocations());
        registerModelLocation(itemTransmutationTome);
        registerVariants(itemSapBottle, itemSapBottle.getModelResourceLocations());
        registerModelLocation(itemAntidote);
        registerModelLocation(itemBandage);
        //channel modules
        registerModelLocation(itemModulePlayer);
        registerModelLocation(itemModuleColor);
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

    public static void registerModelLocation(Item item, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }

    public static void registerModelLocation(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerVariants(Item item, ModelResourceLocation[] locations) {
        for (int i = 0; i < locations.length; i++) {
            registerModelLocation(item, i, locations[i]);
        }
    }

    public static void registerModelLocation(Item item, int damage, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, damage, location);
    }

    public static void registerIndependentModelLocation(Item item, ModelResourceLocation location) {
        ModelLoader.setCustomMeshDefinition(item, new SimpleItemMeshDefinition(location));
        ;
    }

    public static void registerItemBlockModel(Block block) {
        ModItems.registerModelLocation(ItemBlock.getItemFromBlock(block));
    }

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
