package convenientadditions.item.trinket.doubleJump;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.event.PlayerMovementEvent;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.init.ModNetworking;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemMultiJumpTrinket extends CAItem implements IBauble, IDoubleJumpProvider {
    public int jumps;
    public float jumpBoost;
    public float fallBuffer;

    public ItemMultiJumpTrinket(){
        this(ModConstants.ItemNames.cloudJar,1,0,0);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemMultiJumpTrinket(String name,int jumps,float jumpBoost, float fallBuffer){
        super(name);
        this.jumps=jumps;
        this.jumpBoost=jumpBoost;
        this.fallBuffer=fallBuffer;
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onEquipped(ItemStack stack,EntityLivingBase player){
        setJump(stack, (byte) 2);
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.onGround){
            setJump(itemstack, (byte) 0);
        }
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            for(SlotNotation slot: InventoryIterator.getIterable(player, EnumInventory.BAUBLES)){
                ItemStack stack=slot.getItem();
                if(!stack.isEmpty() && stack.getItem() instanceof ItemMultiJumpTrinket){
                    ItemMultiJumpTrinket item=(ItemMultiJumpTrinket)stack.getItem();
                    if(item.jumpBoost>0){
                        player.motionY += item.jumpBoost;
                        if(player.fallDistance>0)
                            player.fallDistance=0;
                        player.fallDistance -= item.fallBuffer;
                        break;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerMovementJump(PlayerMovementEvent.PlayerJumpEvent event){
        EntityPlayer player=event.getPlayer();
        if(!player.isAirBorne)
            return;

        int jumpTicks=0;
        try {
            jumpTicks=ReflectionHelper.getPrivateValue(EntityLivingBase.class,player,"jumpTicks","field_70773_bE");
        }catch (Exception e){e.printStackTrace();}
        if(jumpTicks==10)
            return;

        for(SlotNotation slot:InventoryIterator.getIterable(player, EnumInventory.BAUBLES)){
            ItemStack stack=slot.getItem();
            if(!stack.isEmpty() && stack.getItem() instanceof ItemMultiJumpTrinket){
                ItemMultiJumpTrinket item=(ItemMultiJumpTrinket)stack.getItem();
                if(item.jumps>0){
                    if(item.canDoubleJump(null,stack)){
                        player.motionY=0;
                        player.fallDistance=0;
                        player.jump();
                        item.setJump(stack, (byte) (getJump(stack)+1));
                        ModNetworking.INSTANCE.sendToServer(new PacketDoubleJump(slot));
                    }
                    break;
                }
            }
        }
    }

    public NBTTagCompound initNBT(ItemStack stack){
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt=stack.getTagCompound();
        if(!nbt.hasKey("DOUBLE_JUMPS_PERFORMED"))
            nbt.setByte("DOUBLE_JUMPS_PERFORMED", (byte) ((ItemMultiJumpTrinket)stack.getItem()).jumps);
        return nbt;
    }

    public byte getJump(ItemStack stack){
        return initNBT(stack).getByte("DOUBLE_JUMPS_PERFORMED");
    }

    public void setJump(ItemStack stack,byte jump){
        initNBT(stack).setByte("DOUBLE_JUMPS_PERFORMED",jump);
    }

    @Override
    public boolean canDoubleJump(EntityPlayer player, ItemStack stack) {
        return getJump(stack)<((ItemMultiJumpTrinket)stack.getItem()).jumps;
    }

    @Override
    public void onDoubleJump(EntityPlayer player, ItemStack stack) {
        setJump(stack, (byte) (getJump(stack)+1));
    }
}
