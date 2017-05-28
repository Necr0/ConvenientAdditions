package convenientadditions.init;

import convenientadditions.potion.PotionBandaged;
import convenientadditions.potion.PotionLumbering;
import convenientadditions.potion.PotionThorns;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Necro on 5/14/2017.
 */
public class ModPotions {
    public static void init(){
        GameRegistry.register(PotionLumbering.INSTANCE);
        GameRegistry.register(PotionThorns.INSTANCE);
        GameRegistry.register(PotionBandaged.INSTANCE);
    }
}
