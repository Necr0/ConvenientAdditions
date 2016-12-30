package convenientadditions.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMetadata extends ItemBlock {

    public ItemBlockMetadata(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
}
