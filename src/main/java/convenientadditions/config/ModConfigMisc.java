package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "misc", name = "convenientadditions_misc")
public class ModConfigMisc {
    //#########COMPOSTER#############
    @Config.RequiresMcRestart
    public static boolean composter_recipe=true;
    @Config.Comment("Enables potion effects when overflowing")
    public static boolean composter_overflowSmell=true;
    @Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
    public static int composter_foodMultiplier=400;
    //chances
    @Config.RangeDouble(min = 0F, max = 1F)
    public static float composter_compostChance=1F;
    @Config.RangeDouble(min = 0F, max = 1F)
    public static float composter_extraCompostChance=0.5F;
    @Config.RangeDouble(min = 0F, max = 1F)
    public static float composter_dirtChunkChance=0.45F;
    @Config.RangeDouble(min = 0F, max = 1F)
    public static float composter_fertilizerChance=0.2F;
    @Config.RangeDouble(min = 0F, max = 1F)
    public static float composter_sporesMyceliumChance=0.015F;
    //stats
    @Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
    public static int composter_capacity=25000;
    @Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
    @Config.Comment("The amount of time needed for 1 operation.")
    public static int composter_progressPeriod=2100;
    @Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
    @Config.Comment("The amount of composting mass needed for 1 operation.")
    public static int composter_progressContent=2500;

    //#########INVENTORY PROXIES#############
    @Config.RequiresMcRestart
    public static boolean inventoryProxies_regular=true;
    @Config.RequiresMcRestart
    public static boolean inventoryProxies_sided=true;
    @Config.RequiresMcRestart
    public static boolean inventoryProxies_filtered=true;
    @Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
    @Config.Comment("Needed to prevent infinite loops.")
    public static int inventoryProxies_chainLimit=32;
    @Config.Comment("Blocks that will not be mimiced by proxies or the item transmitter e.g.: 'minecraft:chest'")
    public static String[] inventoryProxies_blacklist=new String[0];

    //#########SPECIAL ARROWS#############
    public static boolean launchingArrows_creeper=true;
    public static boolean launchingArrows_slime=true;
    public static boolean launchingArrows_blast=true;
    //
    public static boolean powderKeg=true;
    public static boolean platform=true;
    public static boolean backpack_recipe=true;
    @Config.Comment("Item id (usually other backpacks) e.g.: 'backpacks:backpack'")
    public static String[] backpack_blacklist=new String[0];
    public static boolean storageCrate=true;
    public static boolean punjiSticks=true;
    public static boolean enderProofBlock=true;
    public static boolean enderProofGlass=true;
    public static boolean workStation=true;
    public static boolean treetap=true;
    public static boolean displayCase=true;
    //#########SEED BOX#############
    public static boolean seedBox_recipe=true;
    public static boolean seedBox_autoCrops=true;
    public static boolean seedBox_autoCompost=true;
    public static boolean seedBox_autoBoneMeal=true;
    @Config.Comment("DON'T CHANGE THIS IF YOU DON'T KNOW WHAT IT IS!")
    public static boolean seedBox_behaviourProviderEntry=true;
    public static boolean seedBox_autoFeed=true;
    @Config.Comment("Item ids e.g.: 'minecraft:wheat'; the mod also has an internal list")
    public static String[] seedBox_autoFeedItems=new String[0];
    @Config.Comment("Entity registry names e.g.: 'minecraft:chicken'")
    public static String[] seedBox_autoFeedBlacklist=new String[0];
}
