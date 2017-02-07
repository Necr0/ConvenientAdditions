package convenientadditions.event;

import convenientadditions.api.event.PlayerMovementEvent;
import convenientadditions.api.util.Helper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MovementInput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandlerPlayerMovement {
    public boolean wasJumping=false;
    public boolean wasSneaking=false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientUpdate(TickEvent.ClientTickEvent event){
        EntityPlayerSP player=Helper.getClientPlayer();
        if(player==null) return;

        MovementInput input=player.movementInput;
        if(!wasJumping && input.jump){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementEvent.PlayerJumpEvent(player,player.getEntityWorld()));
        }
        if(!wasSneaking && input.sneak){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementEvent.PlayerSneakEvent(player,player.getEntityWorld()));
        }

        wasJumping=input.jump;
        wasSneaking=input.sneak;
    }
}
