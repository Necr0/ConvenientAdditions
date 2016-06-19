package convenientadditions.block.compostSoil;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockCompostSoil extends ItemBlock {

	public ItemBlockCompostSoil(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(I18n.translateToLocal("tooltip.convenientadditions:compostDegraded"+getDamage(stack)));
		super.addInformation(stack, player, list, par4);
	}
	
    public int getMetadata(int damage)
    {
        return damage;
    }
}
