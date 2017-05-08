package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemMinersBracelet extends CAItem implements IBauble {

    public ItemMinersBracelet() {
        super(ModConstants.ItemNames.minersBracelet);
        MinecraftForge.EVENT_BUS.register(this);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(!player.isSneaking()) {
                for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                    ItemStack stack = slot.getItem();
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemMinersBracelet) {
                        event.setNewSpeed(event.getNewSpeed()+.5f);
                    }
                }
            }
        }
    }
}
