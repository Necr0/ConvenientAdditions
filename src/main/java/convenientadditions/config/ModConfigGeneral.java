package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModConstants.Mod.MODID)
@Config.RequiresMcRestart
@Config(modid = ModConstants.Mod.MODID, name = "convenientadditions_general")
public class ModConfigGeneral {
    //#########GENERAL#############
    @Config.Comment("DON'T CHANGE THIS IF YOU DON'T KNOW WHAT IT IS!")
    public static boolean sugarOreDictInit=true;
    @Config.Comment("DON'T CHANGE THIS IF YOU DON'T KNOW WHAT IT IS!")
    public static boolean doorOreDictInit=true;
    @Config.Comment("If harvesting slime from big slimes should be possible.")
    public static boolean slimeHarvesting=true;
}
