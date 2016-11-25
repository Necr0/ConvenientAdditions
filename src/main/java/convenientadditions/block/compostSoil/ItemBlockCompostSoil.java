package convenientadditions.block.compostSoil;

import convenientadditions.api.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockCompostSoil extends ItemBlock {

    public ItemBlockCompostSoil(Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip.convenientadditions:compostDegraded" + getDamage(stack)));
        super.addInformation(stack, player, list, par4);
    }

    public int getMetadata(int damage) {
        return damage;
    }
}
