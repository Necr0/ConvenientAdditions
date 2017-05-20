package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID,category = "compatibility",name = "convenientadditions_compatibility")
public class ModConfigCompat {
    @Config.RequiresMcRestart
    @Config.Comment("requires gigaherz' guidebook mod")
    public static boolean gbook_recipe=true;
    @Config.Comment("requires gigaherz' guidebook mod")
    public static boolean gbook_onFirstSpawn=true;
}
