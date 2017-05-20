package convenientadditions;

public class ModConstants {
    public static class Mod {
        public static final String MODNAME = "Convenient Additions";
        public static final String MODID = "convenientadditions";
        public static final String BUILD = "GRADLE:BUILD";
        public static final String VERSION = "GRADLE:VERSION-" + BUILD;
        public static final String DEPENDENCIES = "required-after:forge@[13.20.0.2282 ,);after:baubles;after:jei;after:gbook;after:theoneprobe";
        public static final String commonProxyClassPath = "convenientadditions.proxy.CommonProxy";
        public static final String clientProxyClassPath = "convenientadditions.proxy.ClientProxy";
        public static final String guiFactoryClassPath = "convenientadditions.config.CAGuiFactory";
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
        public static final String dislocationCore = "dislocationCore";
        public static final String cheese = "cheese";
        public static final String cheeseBucket = "cheeseBucket";
        public static final String cheeseSandwich = "cheeseSandwich";
        public static final String potionLumbering = "potionLumbering";
        public static final String potionThorns = "potionThorns";
        public static final String backpack = "backpack";
        public static final String portableEnderRift = "portableEnderRift";
        //
        public static final String mobCatcher = "mobCatcher";
        public static final String mobCatcherRegular = "regular";
        public static final String mobCatcherSuper = "super";
        public static final String mobCatcherHyper = "hyper";
        public static final String mobCatcherMega = "mega";
        public static final String mobCatcherMaster = "master";
        //
        public static final String transmutationTome = "transmutationTome";
        //modules
        public static final String obsidianPlate = "obsidianPlate";
        public static final String modulePlayer = "modulePlayer";
        public static final String moduleColor = "moduleColor";
        public static final String moduleLocation = "moduleLocation";
        public static final String moduleText = "moduleText";
        //trinkets
        public static final String sunlightRing = "sunlightRing";
        public static final String saturationRing = "saturationRing";
        public static final String breathAmulet = "breathAmulet";
        public static final String flippers = "flippers";
        public static final String tideAmulet = "tideAmulet";
        public static final String cloudJar = "cloudJar";
        public static final String slimeBalloon = "slimeBalloon";
        public static final String cloudBalloon = "cloudBalloon";
        public static final String enderCloudBalloon = "enderCloudBalloon";
        public static final String windGem = "windGem";
        public static final String glider = "glider";
        public static final String valkyrieWings = "valkyrieWings";
        public static final String fireproofCloak = "fireproofCloak";
        public static final String netherTalisman = "netherTalisman";
        public static final String netherCloak = "netherCloak";
        public static final String flowingWaterRune = "flowingWaterRune";
        public static final String rocketPack = "rocketPack";
        public static final String climbingClaws = "climbingClaws";
        public static final String spikedSole = "spikedSole";
        public static final String climbingGear = "climbingGear";
        public static final String minersBracelet = "minersBracelet";
        public static final String soulGem = "soulGem";
        public static final String spikes = "spikes";
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
        public static final String remoteInventoryProxy = "remoteInventoryProxy";
        public static final String displayCase = "displayCase";
        public static final String enderProofBlock = "enderProofBlock";
        public static final String enderProofGlass = "enderProofGlass";
        public static final String ironFarm = "ironFarm";
        public static final String ironGolemBlock = "ironGolemBlock";
        public static final String workStation = "workStation";
        public static final String autoWorkStation = "autoWorkStation";
        public static final String cheeseBlock = "cheeseBlock";
        public static final String punjiSticks = "punjiSticks";
        public static final String storageCrate = "storageCrate";
        public static final String woodenTile = "woodenTile";
        //
        public static final String tempLight = "tempLight";
        public static final String phantomPlatform = "phantomPlatform";
    }

    public static class PotionNames {
        public static final String lumbering="lumbering";
        public static final String thorns="thorns";
    }

    public static class Entities {
        public static final int lauchingArrowEntityId = 1;
        public static final String launchingArrowEntityName = "launchingArrow";
        public static final int specialItemEntityId = 2;
        public static final String specialItemEntityName = "specialItem";
        public static final int mobCatcherEntityId = 3;
        public static final String mobCatcherEntityName = "mobCatcher";
    }

    public static class Compat {
        public static class JEI {
            public static final String transmutationTomeCategory = "transmutationTome";
        }

        public static class Waila {
            public static final String registerProviderFQCN = "convenientadditions.compat.waila.ConvAddWailaCallback.registerProvider";
        }

        public static class TheOneProbe {
            public static final String registerProviderFQCN = "convenientadditions.compat.oneprobe.ConvAddOneProbePlugin$GetTheOneProbe";
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

    public static class Dimensions {
        public static final String plane_name="plane";
    }
}
