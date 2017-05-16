package convenientadditions.item.trinket;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import convenientadditions.ModConstants;
import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Optional.Interface(iface = "baubles.api.IBauble",modid = "baubles",striprefs = true)
public class ItemClimbingClaws extends CAItem implements IBauble {
    public float wallClimbSpeed, stepHeightBoost;

    public ItemClimbingClaws() {
        this(ModConstants.ItemNames.climbingClaws,.2f,0f);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ItemClimbingClaws(String name,float wallClimbSpeed,float stepHeightBoost) {
        super(name);
        this.wallClimbSpeed=wallClimbSpeed;
        this.stepHeightBoost=stepHeightBoost;
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TRINKET).setBaublesRequiredInfo(true);
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        if(((ItemClimbingClaws)itemstack.getItem()).stepHeightBoost>0)
            player.stepHeight=.6f;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof ItemClimbingClaws) {
                    ItemClimbingClaws item=(ItemClimbingClaws)stack.getItem();
                    if(item.wallClimbSpeed>0){
                        Vec3d ppos=new Vec3d(player.posX,player.posY,player.posZ);
                        Vec3d plook=player.getLookVec();
                        plook=plook.subtract(0,plook.yCoord,0).normalize();
                        RayTraceResult r=player.world.rayTraceBlocks(ppos,ppos.add(plook.scale(.425)),false,true,false);
                        if(r!=null&&r.typeOfHit==RayTraceResult.Type.BLOCK){
                            IBlockState state=player.world.getBlockState(r.getBlockPos());
                            if(state.isSideSolid(player.world,r.getBlockPos(),r.sideHit)){
                                if(player.isSneaking()){
                                    if(player.moveForward > 0F) {
                                        player.motionY=Math.max(item.wallClimbSpeed,player.motionY);
                                    }else{
                                        player.motionY=Math.max(0d,player.motionY);
                                    }
                                    player.fallDistance=0;
                                }else{
                                    player.motionY=Math.max(player.motionY,Math.min(player.motionY+.1d,-.15d));
                                    if(player.motionY > -.666)
                                        player.fallDistance = 0;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            for (SlotNotation slot : InventoryIterator.getIterable(player, EnumInventory.BAUBLES)) {
                ItemStack stack = slot.getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof ItemClimbingClaws) {
                    ItemClimbingClaws item=(ItemClimbingClaws)stack.getItem();
                    if(item.stepHeightBoost>0){
                        if(player.isSneaking())
                            player.stepHeight=.6f;
                        else
                            player.stepHeight=.6f+item.stepHeightBoost;
                        break;
                    }
                }
            }
        }
    }
}
