package convenientadditions.item.relic.transmutationTome;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTransmutationTome extends CAItem {

    public ItemTransmutationTome() {
        super(ModConstants.ItemNames.transmutationTome);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.RELIC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_TRANSMUTATION_TOME_ID, world, 0, 0, 0);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
