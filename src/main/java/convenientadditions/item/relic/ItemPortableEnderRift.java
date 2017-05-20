package convenientadditions.item.relic;

import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPortableEnderRift extends CAItem {
    public ItemPortableEnderRift() {
        super(ModConstants.ItemNames.portableEnderRift);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.RELIC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote){
            player.displayGUIChest(player.getInventoryEnderChest());
            world.playSound(null,player.posX,player.posY,player.posZ,SoundEvents.BLOCK_ENDERCHEST_OPEN,SoundCategory.PLAYERS,.5f,1f);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
