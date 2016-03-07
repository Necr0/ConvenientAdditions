package convenientadditions.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IFuelItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlazingRock extends ItemSunlightChargeableBehaviour implements IFuelItem {
	public static ItemStack FULLY_CHARGED;

	public ItemBlazingRock() {
		super(48000, true, true, 12);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.blazingRockItemName)
			.setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.blazingRockItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setHasSubtypes(true)
			.setMaxStackSize(1);
		FULLY_CHARGED=new ItemStack(this,1,0);
		chargeItem(FULLY_CHARGED, getChargeCapacity(FULLY_CHARGED));
	}

	@Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        if (!hasContainerItem(itemStack))
            return null;
        ItemStack ret=itemStack.copy();
        consumeCharge(ret, getFuelTime(itemStack)*4);
        ret.stackSize=1;
        return ret;
    }	
    
    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }	
    
	@Override
	public boolean isFuelItem(ItemStack item) {
		return hasContainerItem(item);
	}

	@Override
	public int getFuelTime(ItemStack item) {
		return Math.min(getCharge(item), 55);
	}

	@Override
	public boolean isSunlightChargeable(ItemStack item,int slot) {
		return slot>=0&&slot<=9||slot==-5;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
        l.add(new ItemStack(i, 1, 0));
        l.add(FULLY_CHARGED.copy());
    }
}
