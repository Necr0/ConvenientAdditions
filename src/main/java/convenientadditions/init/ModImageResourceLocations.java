package convenientadditions.init;

import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.ModConstants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModImageResourceLocations {
    private static final String COMMON_ATLAS=ModConstants.Mod.MODID + ":textures/gui/common_atlas.png";
    public static final ImageResourceLocation RESET = new ImageResourceLocation(COMMON_ATLAS, 16, 0, 16, 16);
    public static final ImageResourceLocation DV = new ImageResourceLocation(COMMON_ATLAS, 0, 32, 16, 16);
    public static final ImageResourceLocation NODV = new ImageResourceLocation(COMMON_ATLAS, 16, 32, 16, 16);
    public static final ImageResourceLocation NBT = new ImageResourceLocation(COMMON_ATLAS, 0, 16, 16, 16);
    public static final ImageResourceLocation NONBT = new ImageResourceLocation(COMMON_ATLAS, 16, 16, 16, 16);
    public static final ImageResourceLocation FILTER = new ImageResourceLocation(COMMON_ATLAS, 0, 96, 16, 16);
    public static final ImageResourceLocation NOFILTER = new ImageResourceLocation(COMMON_ATLAS, 16, 96, 16, 16);
    public static final ImageResourceLocation BLACKLIST = new ImageResourceLocation(COMMON_ATLAS, 32, 96, 16, 16);
    public static final ImageResourceLocation WHITELIST = new ImageResourceLocation(COMMON_ATLAS, 48, 96, 16, 16);
    public static final ImageResourceLocation HIGHRS = new ImageResourceLocation(COMMON_ATLAS, 0, 48, 16, 16);
    public static final ImageResourceLocation NORS = new ImageResourceLocation(COMMON_ATLAS, 16, 48, 16, 16);
    public static final ImageResourceLocation LOWRS = new ImageResourceLocation(COMMON_ATLAS, 32, 48, 16, 16);
    public static final ImageResourceLocation PULSERS = new ImageResourceLocation(COMMON_ATLAS, 48, 48, 16, 16);
    public static final ImageResourceLocation AUTO = new ImageResourceLocation(COMMON_ATLAS, 32, 0, 16, 16);
    public static final ImageResourceLocation INV = new ImageResourceLocation(COMMON_ATLAS, 0, 128, 16, 16);
    public static final ImageResourceLocation NOINV = new ImageResourceLocation(COMMON_ATLAS, 16, 128, 16, 16);
    public static final ImageResourceLocation FORBID = new ImageResourceLocation(COMMON_ATLAS, 0, 0, 16, 16);
    //POTIONS
    private static final String ICONS_POTION=ModConstants.Mod.MODID + ":textures/gui/icons_potion.png";
    public static final ImageResourceLocation LUMBERING = new ImageResourceLocation(ICONS_POTION, 0, 0, 18, 18);
    public static final ImageResourceLocation SPELUNKING = new ImageResourceLocation(ICONS_POTION, 18, 0, 18, 18);
    public static final ImageResourceLocation THORNS = new ImageResourceLocation(ICONS_POTION, 36, 0, 18, 18);
    public static final ImageResourceLocation GREENTHUMB = new ImageResourceLocation(ICONS_POTION, 54, 0, 18, 18);
    public static final ImageResourceLocation HARVESTLUCK = new ImageResourceLocation(ICONS_POTION, 72, 0, 18, 18);
}
