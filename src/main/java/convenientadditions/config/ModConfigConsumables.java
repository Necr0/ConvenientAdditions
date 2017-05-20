package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "consumables", name = "convenientadditions_consumables")
@Config.RequiresMcRestart
public class ModConfigConsumables {
    public static boolean cheese=true;
    public static boolean antidote=true;
    public static boolean bandage=true;

    //#########POTION#############
    public static boolean potion_lumbering=true;
    public static boolean potion_thorns=true;
}
