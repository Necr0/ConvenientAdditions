package convenientadditions;

public class ModConstants {
	public static class Mod{
	    public static final String modName = "Convenient Additions";
	    public static final String MODID = "convenientadditions";
	    public static final String VERSION = "1.0";
	    public static final String DEPENDENCIES = "required-after:Forge;required-after:conveniencecore;required-after:Baubles;after:JEI;";
	    public static final String commonProxyClassPath = "convenientadditions.proxy.CommonProxy";
	    public static final String clientProxyClassPath = "convenientadditions.proxy.ClientProxy";
	}
	
	public static class ItemNames{
	    public static final String ingotTitaniumItemName = "ingotTitanium";
	    public static final String nuggetTitaniumItemName = "nuggetTitanium";
	    public static final String pickaxeTitaniumItemName = "pickaxeTitanium";
	    public static final String axeTitaniumItemName = "axeTitanium";
	    public static final String spadeTitaniumItemName = "spadeTitanium";
	    public static final String hoeTitaniumItemName = "hoeTitanium";
	    public static final String swordTitaniumItemName = "swordTitanium";
	    public static final String compostItemName = "compost";
	    public static final String fertilizerItemName = "fertilizer";
	    public static final String dirtChunkItemName = "dirtChunk";
	    public static final String sunstoneItemName = "sunstone";
	    public static final String titaniumWrenchItemName = "titaniumWrench";
	    public static final String ironWrenchItemName = "ironWrench";
	    public static final String blazingRockItemName = "blazingRock";
	    public static final String enderPlateItemName = "enderPlate";
	    public static final String launchingArrowItemName = "launchingArrow";
	    public static final String sapBottleItemName = "sapBottle";
	    public static final String antidoteItemName = "antidote";
	    public static final String bandageItemName = "bandage";
	    public static final String adventurersPickaxeItemName = "adventurersPickaxe";
	    //
	    public static final String transmutationTomeItemName = "transmutationTome";
	    //channel modules
	    public static final String obsidianPlateItemName = "obsidianPlate";
	    public static final String modulePlayerItemName = "modulePlayer";
	    public static final String moduleColorItemName = "moduleColor";
	    //baubles
	    public static final String sunlightRingItemName = "sunlightRing";
	    public static final String saturationRingItemName = "saturationRing";
	    public static final String breathAmuletItemName = "breathAmulet";
	    public static final String chargingRingItemName = "chargingRing";
	}
	
	public static class BlockNames{
	    public static final String oreTitaniumBlockName = "oreTitanium";
	    public static final String composterBlockName = "composter";
	    public static final String compostSoilBlockName = "compostSoil";
	    public static final String compostSoilTilledBlockName = "compostSoilTilled";
	    public static final String powderKegBlockName = "powderKeg";
	    public static final String playerInterfaceBlockName = "playerInterface";
	    public static final String proximitySensorBlockName = "proximitySensor";
	    public static final String sunlightCollectorBlockName = "sunlightCollector";
	    public static final String chargeAccumulatorBlockName = "chargeAccumulator";
	    public static final String chargeTubeBlockName = "chargeTube";
	    public static final String seedBoxBlockName = "seedBox";
	    public static final String setProviderBlockName = "setProvider";
	    public static final String itemTransmitterBlockName = "itemTransmitter";
	    public static final String itemReceiverBlockName = "itemReceiver";
	    public static final String inventoryProxyBlockName = "inventoryProxy";
	    public static final String inventoryProxySidedBlockName = "inventoryProxySided";
	    public static final String inventoryProxyFilteredBlockName = "inventoryProxyFiltered";
	    public static final String treetapBlockName = "treetap";
	    public static final String gatewayBlockName = "gateway";
	    //
	    public static final String tempLightBlockName = "tempLight";
	    public static final String phantomPlatformBlockName = "phantomPlatform";
    }
	
	public static class Entities{
	    public static final int lauchingArrowEntityId = 1;
	    public static final String launchingArrowEntityName = "launchingArrow";
	}
	
	public static class Compat{
		public static class JEI{
		    public static final String transmutationTomeCategory = "transmutationTome";
		}
	}
	
	public static class Items{
		public static class AdvPick{
			public static final String[] unlocalizedMiningLevelNames=new String[]{ModConstants.Mod.MODID+":miningLevel0",ModConstants.Mod.MODID+":miningLevel1",ModConstants.Mod.MODID+":miningLevel2",ModConstants.Mod.MODID+":miningLevel3",ModConstants.Mod.MODID+":miningLevel4"};
			public static final String[] unlocalizedMiningMaterialNames=new String[]{ModConstants.Mod.MODID+":materialWood",ModConstants.Mod.MODID+":materialStone",ModConstants.Mod.MODID+":materialIron",ModConstants.Mod.MODID+":materialDiamond",ModConstants.Mod.MODID+":materialCobalt"};
			public static final String[] unlocalizedMiningRepairMaterialNames=new String[]{ModConstants.Mod.MODID+":repairMaterialWood",ModConstants.Mod.MODID+":repairMaterialStone",ModConstants.Mod.MODID+":repairMaterialIron",ModConstants.Mod.MODID+":repairMaterialDiamond",ModConstants.Mod.MODID+":repairMaterialCobalt"};
			public static final int[] miningLevelOreExperience=new int[]{3,10,18,27,37};
		}
	}
}