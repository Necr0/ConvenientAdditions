package convenientadditions;

public class ModConstants {
    public static class Mod {
        public static final String MODNAME = "Convenient Additions";
        public static final String MODID = "convenientadditions";
        public static final String BUILD = "GRADLE:BUILD";
        public static final String VERSION = "GRADLE:VERSION-" + BUILD;
        public static final String DEPENDENCIES = "required-after:forge@[13.20.0.2200 ,);after:baubles;after:jei;";
        public static final String commonProxyClassPath = "convenientadditions.proxy.CommonProxy";
        public static final String clientProxyClassPath = "convenientadditions.proxy.ClientProxy";
    }

    public static class ItemNames {
        public static final String compost = "compost";
        public static final String fertilizer = "fertilizer";
        public static final String dirtChunk = "dirtChunk";
        public static final String sunstone = "sunstone";
        public static final String ironWrench = "ironWrench";
        public static final String blazingRock = "blazingRock";
        public static final String enderPlate = "enderPlate";
        public static final String launchingArrow = "launchingArrow";
        public static final String creeperKit = "creeperKit";
        public static final String blastKit = "blastKit";
        public static final String slimeKit = "slimeKit";
        public static final String sapBottle = "sapBottle";
        public static final String antidote = "antidote";
        public static final String bandage = "bandage";
        public static final String adventurersPickaxe = "adventurersPickaxe";
        public static final String mobCatcher = "mobCatcher";
        //
        public static final String transmutationTome = "transmutationTome";
        //channel modules
        public static final String obsidianPlate = "obsidianPlate";
        public static final String modulePlayer = "modulePlayer";
        public static final String moduleColor = "moduleColor";
        //
        public static final String moduleLocation = "moduleLocation";
        //baubles
        public static final String sunlightRing = "sunlightRing";
        public static final String saturationRing = "saturationRing";
        public static final String breathAmulet = "breathAmulet";
        public static final String chargingRing = "chargingRing";
    }

    public static class BlockNames {
        public static final String composter = "composter";
        public static final String compostSoil = "compostSoil";
        public static final String compostSoilTilled = "compostSoilTilled";
        public static final String powderKeg = "powderKeg";
        public static final String playerInterface = "playerInterface";
        public static final String proximitySensor = "proximitySensor";
        public static final String seedBox = "seedBox";
        public static final String setProvider = "setProvider";
        public static final String itemTransmitter = "itemTransmitter";
        public static final String itemReceiver = "itemReceiver";
        public static final String inventoryProxy = "inventoryProxy";
        public static final String inventoryProxySided = "inventoryProxySided";
        public static final String inventoryProxyFiltered = "inventoryProxyFiltered";
        public static final String treetap = "treetap";
        public static final String hoverPad = "hoverPad";
        public static final String blastPad = "blastPad";
        public static final String jumpPad = "jumpPad";
        public static final String platform = "platform";
        public static final String semiSolidBlock = "semiSolidBlock";
        public static final String storageMatrix = "storageMatrix";
        public static final String machineBlock = "machineBlock";
        //
        public static final String tempLight = "tempLight";
        public static final String phantomPlatform = "phantomPlatform";
    }

    public static class Entities {
        public static final int lauchingArrowEntityId = 1;
        public static final String launchingArrowEntityName = "launchingArrow";
        public static final int specialItemEntityId = 2;
        public static final String specialItemEntityName = "specialItem";
    }

    public static class Compat {
        public static class JEI {
            public static final String transmutationTomeCategory = "transmutationTome";
        }

        public static class Waila {
            public static final String registerProviderFQCN = "convenientadditions.compat.waila.ConvAddWailaCallback.registerProvider";
        }
    }

    public static class Items {
        public static class AdvPick {
            public static final String[] unlocalizedMiningLevelNames = new String[]{ModConstants.Mod.MODID + ":miningLevel0", ModConstants.Mod.MODID + ":miningLevel1", ModConstants.Mod.MODID + ":miningLevel2", ModConstants.Mod.MODID + ":miningLevel3", ModConstants.Mod.MODID + ":miningLevel4"};
            public static final String[] unlocalizedMiningMaterialNames = new String[]{ModConstants.Mod.MODID + ":materialWood", ModConstants.Mod.MODID + ":materialStone", ModConstants.Mod.MODID + ":materialIron", ModConstants.Mod.MODID + ":materialDiamond", ModConstants.Mod.MODID + ":materialCobalt"};
            public static final String[] unlocalizedMiningRepairMaterialNames = new String[]{ModConstants.Mod.MODID + ":repairMaterialWood", ModConstants.Mod.MODID + ":repairMaterialStone", ModConstants.Mod.MODID + ":repairMaterialIron", ModConstants.Mod.MODID + ":repairMaterialDiamond", ModConstants.Mod.MODID + ":repairMaterialCobalt"};
            public static final int[] miningLevelOreExperience = new int[]{3, 10, 18, 27, 37};
        }
    }
}
