package convenientadditions.item.transmutationTome;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.CAItem;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTransmutationTome extends CAItem {

    public ItemTransmutationTome() {
        super(ModConstants.ItemNames.transmutationTomeItemName);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_TRANSMUTATION_TOME_ID, world, 0, 0, 0);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
