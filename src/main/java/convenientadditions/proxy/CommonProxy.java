package convenientadditions.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public Side getSide() {
        return Side.SERVER;
    }

    public void registerRenderers() {}

    public World getClientWorld() {
        return null;
    }

    public void InitModels() {}
}
