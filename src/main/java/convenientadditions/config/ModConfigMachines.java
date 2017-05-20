package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "machines", name = "convenientadditions_machines")
public class ModConfigMachines {

    //#########HOVER PAD#############
    @Config.RequiresMcRestart
    public static boolean hoverPad=true;

    @Config.RequiresMcRestart
    public static boolean storageMatrix=true;
    @Config.Comment("I can understand if you want to disable it.")
    @Config.RequiresMcRestart
    public static boolean ironFarm=true;
    @Config.RequiresMcRestart
    public static boolean autoWorkStation=true;
    @Config.RequiresMcRestart
    public static boolean itemTransmitter=true;
    @Config.RequiresMcRestart
    public static boolean itemReceiver=true;
    @Config.RequiresMcRestart
    public static boolean playerInterface=true;
    @Config.RequiresMcRestart
    public static boolean proximitySensor=true;
    @Config.RequiresMcRestart
    public static boolean setProvider=true;
    @Config.RequiresMcRestart
    public static boolean blastPad=true;
    @Config.RangeInt(min = 0,max = Integer.MAX_VALUE)
    public static int jumpPad_range=256;
    @Config.RequiresMcRestart
    public static boolean jumpPad_recipe=true;
    @Config.RequiresMcRestart
    public static boolean inventoryProxies_remote=true;
}
