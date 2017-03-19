package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemNetherTrinket extends CAItem implements IBauble {
    public boolean extinguishFire;
    public boolean preventHotFloorDamage;
    public float fireDamageNegation;
    public float lavaDamageNegation;
    public boolean quickPortals;

    public ItemNetherTrinket() {
        this(ModConstants.ItemNames.fireproofCloak,true,false,.334f,0f,false);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemNetherTrinket(String name, boolean extinguishFire, boolean preventHotFloorDamage, float fireDamageNegation, float lavaDamageNegation, boolean quickPortals) {
        super(name);
        this.maxStackSize=1;
        this.extinguishFire=extinguishFire;
        this.preventHotFloorDamage=preventHotFloorDamage;
        this.fireDamageNegation=fireDamageNegation;
        this.lavaDamageNegation=lavaDamageNegation;
        this.quickPortals=quickPortals;
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.stepHeight=.6f;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityWorld().isRemote)
            return;
        if(player.isBurning()&&this.extinguishFire)
            player.extinguish();
        if(this.quickPortals && player.timeUntilPortal==0){
            try {
                Field f0= Entity.class.getDeclaredField("portalCounter");
                Field f1= Entity.class.getDeclaredField("inPortal");
                f0.setAccessible(true);
                f1.setAccessible(true);
                if( f1.getBoolean(player) && f0.getInt(player)<(player.getMaxInPortalTime()-1) )
                    f0.setInt(player,player.getMaxInPortalTime()-1);
            }catch (Exception e){}
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        if(!(event.getEntityLiving() instanceof EntityPlayer)||event.getEntityLiving().world.isRemote)
            return;
        EntityPlayer player= (EntityPlayer) event.getEntityLiving();
        if(event.getSource()==DamageSource.HOT_FLOOR){
            for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof ItemNetherTrinket) {
                    ItemNetherTrinket item=(ItemNetherTrinket)stack.getItem();
                    if(item.preventHotFloorDamage){
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }else if(event.getSource()==DamageSource.IN_FIRE){
            for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof ItemNetherTrinket) {
                    ItemNetherTrinket item=(ItemNetherTrinket)stack.getItem();
                    if(item.fireDamageNegation>0){
                        event.setAmount(event.getAmount()*(1-item.fireDamageNegation));
                        return;
                    }
                }
            }
        }else if(event.getSource()==DamageSource.LAVA){
            for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof ItemNetherTrinket) {
                    ItemNetherTrinket item=(ItemNetherTrinket)stack.getItem();
                    if(item.lavaDamageNegation>0){
                        event.setAmount(event.getAmount()*(1-item.lavaDamageNegation));
                        return;
                    }
                }
            }
        }
    }
}