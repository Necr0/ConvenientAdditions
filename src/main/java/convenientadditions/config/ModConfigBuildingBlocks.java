package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "building_blocks", name = "convenientadditions_building_blocks")
@Config.RequiresMcRestart
public class ModConfigBuildingBlocks {
    public static boolean ironGolemBlock=true;
    public static boolean woodenTile=true;
    public static boolean cheeseBlock=true;
    public static boolean clearGlass=true;
}
