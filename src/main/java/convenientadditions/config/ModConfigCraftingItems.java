package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraftforge.common.config.Config;

/**
 * Created by Necro on 5/19/2017.
 */
@Config(modid = ModConstants.Mod.MODID, category = "crafting_items", name = "convenientadditions_crafting_items")
@Config.RequiresMcRestart
public class ModConfigCraftingItems {
    public static boolean machineBlock=true;
    public static boolean dislocationCore=true;
    public static boolean soulGem=true;
    public static boolean spikes=true;
}
