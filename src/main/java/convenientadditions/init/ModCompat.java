package convenientadditions.init;

import convenientadditions.compat.oneprobe.ConvAddOneProbePlugin;
import convenientadditions.compat.waila.ConvAddWailaPlugin;

/**
 * Created by Necro on 5/19/2017.
 */
public class ModCompat {
    public static void init(){
        ConvAddWailaPlugin.init();
        ConvAddOneProbePlugin.init();
    }
}
