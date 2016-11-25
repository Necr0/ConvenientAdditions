package convenientadditions.item.channelModule.matchers;

import convenientadditions.api.IMatcher;
import net.minecraft.entity.player.EntityPlayer;

public class MatcherPlayer implements IMatcher {

    String id;

    public MatcherPlayer(String id) {
        this.id = id;
    }

    public MatcherPlayer(EntityPlayer player) {
        this.id = player.getName();
    }

    @Override
    public boolean matches(IMatcher matcher) {
        if (matcher instanceof MatcherPlayer)
            return ((MatcherPlayer) matcher).id.equals(id);
        return false;
    }

}
