package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "relics", name = "convenientadditions_relics")
@Config.RequiresMcRestart
public class ModConfigRelics {
    public static boolean sunstone=true;
    public static boolean blazingOrb=true;
    public static boolean enderPlate=true;
    public static boolean portableEnderRift=true;

    //#########TRANSMUTATION TOME#############
    public static boolean transmutationTome_recipe=true;
    public static boolean transmutationTome_oreDoubling=true;
    public static boolean transmutationTome_oreUpgrade=true;
    public static boolean transmutationTome_oreDowngrade=true;
    public static boolean transmutationTome_oreOther=true;
    public static boolean transmutationTome_logVariations=true;
    public static boolean transmutationTome_stoneVariations=true;
    public static boolean transmutationTome_cropMutation=true;
    public static boolean transmutationTome_dirtMutation=true;
    public static boolean transmutationTome_purification=true;
    public static boolean transmutationTome_misc=true;
    public static boolean transmutationTome_elytra=true;
    public static boolean transmutationTome_dragonEgg=true;
}
