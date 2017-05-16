package convenientadditions.init;

import convenientadditions.PotionLumbering;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Necro on 5/14/2017.
 */
public class ModPotions {
    public static void init(){
        GameRegistry.register(PotionLumbering.INSTANCE);
        MinecraftForge.EVENT_BUS.register(PotionLumbering.INSTANCE);
    }
}
