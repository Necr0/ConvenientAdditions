package convenientadditions.api.block.tileentity;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;

public interface IItemProxy {
    IItemHandler tryFetchItemHandler(EnumFacing f, int proxyIndex);
}
