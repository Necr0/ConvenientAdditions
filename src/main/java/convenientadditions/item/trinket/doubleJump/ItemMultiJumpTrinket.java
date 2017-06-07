package convenientadditions.item.trinket.doubleJump;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.event.PlayerMovementEvent;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.util.Helper;
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
    public float parachuteLift;

    public ItemMultiJumpTrinket(){
        this(ModConstants.ItemNames.cloudJar,1,0f,0f,0f);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemMultiJumpTrinket(String name,int jumps,float jumpBoost, float fallBuffer, float parachuteLift){
        super(name);
        this.jumps=jumps;
        this.jumpBoost=jumpBoost;
        this.fallBuffer=fallBuffer;
        this.parachuteLift=parachuteLift;
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
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        ItemMultiJumpTrinket item=(ItemMultiJumpTrinket)stack.getItem();
        if(player.onGround){
            if(item.jumps>0)
                setJump(stack, (byte) 0);
            if(item.parachuteLift!=0)
                setParachute(stack, false);
        }else if(Helper.isEntityAirBorne(player)){
            if(item.parachuteLift!=0&&getParachute(stack)){
                player.motionY=Math.max(player.motionY,item.parachuteLift);
                player.fallDistance=0;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(player.isInWater()||player.isElytraFlying())
                return;
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
        if(!Helper.isEntityAirBorne(player))
            return;

        int jumpTicks=0;
        try {
            jumpTicks=ReflectionHelper.getPrivateValue(EntityLivingBase.class,player,"jumpTicks","field_70773_bE");
        }catch (Exception e){e.printStackTrace();}
        if(jumpTicks==10)
            return;

        boolean double_jump=false;

        for(SlotNotation slot:InventoryIterator.getIterable(player, EnumInventory.BAUBLES)){
            ItemStack stack=slot.getItem();
            if(!stack.isEmpty() && stack.getItem() instanceof ItemMultiJumpTrinket){
                ItemMultiJumpTrinket item=(ItemMultiJumpTrinket)stack.getItem();
                if(item.jumps>0){
                    if(item.canDoubleJump(player,stack,slot)){
                        player.motionY=0;
                        player.fallDistance=0;
                        player.jump();
                        item.setJump(stack, (byte) (getJump(stack)+1));
                        ModNetworking.INSTANCE.sendToServer(new PacketDoubleJump(slot));
                        double_jump=true;
                    }
                    break;
                }
            }
        }

        if(!double_jump && player.motionY<-.1){
            for(SlotNotation slot:InventoryIterator.getIterable(player, EnumInventory.BAUBLES)){
                ItemStack stack=slot.getItem();
                if(!stack.isEmpty() && stack.getItem() instanceof ItemMultiJumpTrinket){
                    ItemMultiJumpTrinket item=(ItemMultiJumpTrinket)stack.getItem();
                    if(item.parachuteLift!=0){
                        ModNetworking.INSTANCE.sendToServer(new PacketParachute(slot));
                        item.onParachuteOpen(player,stack,slot);
                        break;
                    }
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

    public boolean getParachute(ItemStack stack){
        return initNBT(stack).getBoolean("PARACHUTE_OPEN");
    }

    public void setParachute(ItemStack stack,boolean open){
        initNBT(stack).setBoolean("PARACHUTE_OPEN",open);
    }

    @Override
    public boolean canDoubleJump(EntityPlayer player, ItemStack stack, SlotNotation slot) {
        return getJump(stack)<((ItemMultiJumpTrinket)stack.getItem()).jumps && slot.inventory==EnumInventory.BAUBLES;
    }

    @Override
    public void onDoubleJump(EntityPlayer player, ItemStack stack, SlotNotation slot) {
        setJump(stack, (byte) (getJump(stack)+1));
    }

    @Override
    public boolean isParachute(EntityPlayer player, ItemStack stack, SlotNotation slot) {
        return parachuteLift!=0 && slot.inventory==EnumInventory.BAUBLES;
    }

    @Override
    public void onParachuteOpen(EntityPlayer player, ItemStack stack, SlotNotation slot) {
        setParachute(stack,!getParachute(stack));
        player.motionY=Math.max(player.motionY,((ItemMultiJumpTrinket)stack.getItem()).parachuteLift);
        player.fallDistance=0;
    }
}
