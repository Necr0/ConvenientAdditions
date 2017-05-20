package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "modules", name = "convenientadditions_modules")
@Config.RequiresMcRestart
public class ModConfigModules {

    //#########CHANNEL MODULES#############
    public static boolean modulePlayer=true;
    public static boolean moduleColor=true;
    public static boolean moduleText=true;
    public static boolean moduleLocation=true;
}
