package convenientadditions.potion;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.CAPotion;
import convenientadditions.init.ModImageResourceLocations;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionLumbering extends CAPotion {
    public static final PotionLumbering INSTANCE=new PotionLumbering();

    public PotionLumbering() {
        super(ModConstants.PotionNames.lumbering, false, 0xFFD400, ModImageResourceLocations.LUMBERING);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        PotionEffect active=player.getActivePotionEffect(PotionLumbering.INSTANCE);
        if(active!=null&&Helper.doesOreDictMatch(event.getState(),"logWood",false)){
            event.setNewSpeed(event.getNewSpeed()*(1<<(active.getAmplifier()+1)));
        }
    }
}
