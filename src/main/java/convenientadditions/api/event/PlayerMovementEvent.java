package convenientadditions.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerMovementEvent extends Event {

    private EntityPlayer player;
    private World world;

    public PlayerMovementEvent(EntityPlayer player, World world){
        this.player=player;
        this.world=world;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public static class PlayerJumpEvent extends PlayerMovementEvent {
        public PlayerJumpEvent(EntityPlayer player, World world) {
            super(player, world);
        }
    }

    public static class PlayerSneakEvent extends PlayerMovementEvent {
        public PlayerSneakEvent(EntityPlayer player, World world) {
            super(player, world);
        }
    }
}
