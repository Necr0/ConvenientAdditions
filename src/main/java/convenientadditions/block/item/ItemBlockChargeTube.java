package convenientadditions.block.item;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockChargeTube extends ItemBlock {

	public ItemBlockChargeTube(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.chargeTubeBlockName);
	}

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.itemIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
    }
}
