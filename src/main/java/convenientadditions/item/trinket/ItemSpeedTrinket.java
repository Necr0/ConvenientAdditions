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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemSpeedTrinket extends CAItem implements IBauble {
    public float speedBoost, glidingLift, glidingSpeed;

    public ItemSpeedTrinket() {
        this(ModConstants.ItemNames.windGem,.08f,0f,0f);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemSpeedTrinket(String name, float speedBoost, float glidingLift, float glidingSpeed) {
        super(name);
        this.maxStackSize=1;
        this.speedBoost=speedBoost;
        this.glidingLift=glidingLift;
        this.glidingSpeed=glidingSpeed;
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(!player.isSneaking()) {

                if((player.onGround || player.capabilities.isFlying) && player.moveForward > 0F && !player.isInWater()) {
                    for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                        ItemStack stack = slot.getItem();
                        if (!stack.isEmpty() && stack.getItem() instanceof ItemSpeedTrinket) {
                            ItemSpeedTrinket item=(ItemSpeedTrinket)stack.getItem();
                            if(item.speedBoost>0){
                                player.moveRelative(0F, 1F, item.speedBoost);
                                break;
                            }
                        }
                    }
                }

                if(!player.onGround && !player.isInWater() && player.fallDistance!=0 && player.motionY<0 && !player.isElytraFlying()){
                    for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                        ItemStack stack = slot.getItem();
                        if (!stack.isEmpty() && stack.getItem() instanceof ItemSpeedTrinket) {
                            ItemSpeedTrinket item=(ItemSpeedTrinket)stack.getItem();
                            if(item.glidingLift!=0){
                                player.motionY=Math.max(player.motionY,item.glidingLift/2);
                                player.fallDistance=0;
                                if(player.moveForward > 0F){
                                    for (SlotNotation slot1 : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                                        ItemStack stack1 = slot1.getItem();
                                        if (!stack1.isEmpty() && stack1.getItem() instanceof ItemSpeedTrinket) {
                                            ItemSpeedTrinket item1=(ItemSpeedTrinket)stack1.getItem();
                                            if(item1.glidingSpeed>0){
                                                player.moveRelative(0F, 1F, item1.glidingSpeed/5);
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
