package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "tools", name = "convenientadditions_tools")
public class ModConfigTools {

    //#########MOB CATCHER#############
    @Config.RequiresMcRestart
    public static boolean mobCatcher_recipe=true;
    @Config.RequiresMcRestart
    public static boolean mobCatcher_loot=true;
    @Config.Comment("Entity registry names e.g.: 'minecraft:chicken'; Ender Dragon automatically blacklisted")
    public static String[] mobCatcher_blacklist=new String[0];
    @Config.Comment("Entity registry names e.g.: 'minecraft:chicken'; Internally contains Wither")
    public static String[] mobCatcher_bosses=new String[0];
    @Config.RequiresMcRestart
    public static boolean ironWrench=true;
}
