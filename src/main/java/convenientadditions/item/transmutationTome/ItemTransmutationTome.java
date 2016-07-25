package convenientadditions.item.transmutationTome;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.init.ModGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTransmutationTome extends Item implements IModelResourceLocationProvider {
	
	public ItemTransmutationTome() {
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.transmutationTomeItemName).setMaxStackSize(1).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		if(!world.isRemote){
    		player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_TRANSMUTATION_TOME_ID, world, 0, 0, 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,item);
	}
}
