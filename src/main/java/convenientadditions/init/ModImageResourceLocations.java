package convenientadditions.init;

import convenientadditions.api.gui.widget.ImageResourceLocation;
import convenientadditions.ModConstants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModImageResourceLocations {
    public static final ImageResourceLocation RESET = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 16, 0, 16, 16);
    public static final ImageResourceLocation DV = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 0, 32, 16, 16);
    public static final ImageResourceLocation NODV = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 16, 32, 16, 16);
    public static final ImageResourceLocation NBT = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 0, 16, 16, 16);
    public static final ImageResourceLocation NONBT = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 16, 16, 16, 16);
    public static final ImageResourceLocation FILTER = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 0, 96, 16, 16);
    public static final ImageResourceLocation NOFILTER = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 16, 96, 16, 16);
    public static final ImageResourceLocation HIGHRS = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 0, 48, 16, 16);
    public static final ImageResourceLocation NORS = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 16, 48, 16, 16);
    public static final ImageResourceLocation LOWRS = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 32, 48, 16, 16);
    public static final ImageResourceLocation PULSERS = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 48, 48, 16, 16);
    public static final ImageResourceLocation AUTO = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/common_atlas.png", 32, 0, 16, 16);
}
