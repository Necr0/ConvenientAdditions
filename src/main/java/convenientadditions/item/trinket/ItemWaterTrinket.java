package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemWaterTrinket extends CAItem implements IBauble {
    public boolean preventDrowning;
    public float waterSpeedBoost, waterMiningBoost;

    public ItemWaterTrinket() {
        this(ModConstants.ItemNames.breathAmulet,true,0f,0f);
        this.setDefaultJoke(true);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemWaterTrinket(String name,boolean preventDrowning,float waterSpeedBoost, float waterMiningBoost) {
        super(name);
        this.setMaxStackSize(1);
        this.preventDrowning=preventDrowning;
        this.waterSpeedBoost=waterSpeedBoost;
        this.waterMiningBoost=waterMiningBoost;
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        EntityPlayer p = (EntityPlayer) player;
        int air = p.getAir();
        if (air < 20 && preventDrowning) {
            p.setAir(20);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(!player.isSneaking()) {
                if(player.isInWater() && player.moveForward > 0F) {
                    for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                        ItemStack stack = slot.getItem();
                        if (!stack.isEmpty() && stack.getItem() instanceof ItemWaterTrinket) {
                            ItemWaterTrinket item=(ItemWaterTrinket)stack.getItem();
                            if(item.waterSpeedBoost>0){
                                player.moveRelative(0F, 1F, item.waterSpeedBoost);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(PlayerEvent.BreakSpeed event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(!player.isSneaking()) {
                if(player.isInWater()) {
                    for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                        ItemStack stack = slot.getItem();
                        if (!stack.isEmpty() && stack.getItem() instanceof ItemWaterTrinket) {
                            ItemWaterTrinket item=(ItemWaterTrinket)stack.getItem();
                            if(item.waterMiningBoost>0){
                                event.setNewSpeed(event.getNewSpeed()+item.waterMiningBoost);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
