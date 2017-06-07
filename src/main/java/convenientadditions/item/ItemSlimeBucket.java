package convenientadditions.item;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.config.ModConfigGeneral;
import convenientadditions.init.ModItems;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ItemSlimeBucket extends CAItem {
    public ItemSlimeBucket() {
        super(ModConstants.ItemNames.slimeBucket);
        this.setDefaultInfo(false).setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        ItemStack stack=event.getItemStack();
        EntityPlayer player=event.getEntityPlayer();

        if (ModConfigGeneral.slimeHarvesting && stack.getItem() == Items.BUCKET) //&& !player.mapped_capabilities.isCreativeMode)
        {
            if(!(EntityRegistry.getEntry(event.getTarget().getClass()).getRegistryName().toString().equals("minecraft:slime") && ((EntitySlime)event.getTarget()).getSlimeSize()>2))
                return;
            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            stack.shrink(1);

            if (stack.isEmpty())
            {
                player.setHeldItem(event.getHand(), new ItemStack(ModItems.itemSlimeBucket));
            }
            else
                Helper.insertOrDrop(player, new ItemStack(ModItems.itemSlimeBucket));

            event.setCanceled(true);
        }
    }
}
