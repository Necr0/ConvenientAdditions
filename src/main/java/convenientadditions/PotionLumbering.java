package convenientadditions;

import convenientadditions.api.util.GuiHelper;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModImageResourceLocations;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionLumbering extends Potion {
    public static final PotionLumbering INSTANCE=new PotionLumbering();

    public PotionLumbering() {
        super(false, 0xFFD400);
        this.setPotionName("potion."+ModConstants.Mod.MODID+":"+ModConstants.PotionNames.lumbering+".name").setRegistryName(ModConstants.Mod.MODID+":lumbering");
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if(event.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            PotionEffect active=player.getActivePotionEffect(PotionLumbering.INSTANCE);
            if(active!=null&&Helper.doesOreDictMatch(event.getState(),"logWood",false)){
                event.setNewSpeed(event.getNewSpeed()*(1<<(active.getAmplifier()+1)));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        GuiHelper.renderIRL(mc,ModImageResourceLocations.LUMBERING,x+6,y+7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        GuiHelper.renderIRL(mc,ModImageResourceLocations.LUMBERING,x+3,y+3,alpha);
    }
}
