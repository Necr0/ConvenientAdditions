package convenientadditions.entity.mobCatcher;

import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModNetworking;
import convenientadditions.item.tools.mobCatcher.ItemMobCatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Necro on 2/14/2017.
 */
public class EntityMobCatcher extends EntityThrowable {
    ItemStack mobCatcherItem=ItemStack.EMPTY;

    public EntityMobCatcher(World worldIn) {
        super(worldIn);
    }

    public EntityMobCatcher(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityMobCatcher(World worldIn, EntityLivingBase throwerIn, ItemStack stack) {
        super(worldIn, throwerIn);
        this.mobCatcherItem=stack;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(this.world.isRemote||mobCatcherItem.isEmpty()||!(mobCatcherItem.getItem() instanceof ItemMobCatcher))
            return;
        if(result.typeOfHit==RayTraceResult.Type.ENTITY){
            Entity target=result.entityHit;
            if(target instanceof EntityCreature && !target.isDead){
                if(!ModConfig.mobCatcher_blacklist.contains(EntityRegistry.getEntry(target.getClass()).getRegistryName().toString())){
                    boolean hostile= target instanceof IMob;
                    ItemMobCatcher item= (ItemMobCatcher) mobCatcherItem.getItem();
                    if( (item.type.captureHostile||!hostile) && (item.type.captureBoss||!ModConfig.mobCatcher_bosses.contains(EntityRegistry.getEntry(target.getClass()).getRegistryName().toString())) ){
                        EntityCreature t= (EntityCreature) target;
                        float resistance=hostile?1.25f:1;
                        float weakness=(t.getMaxHealth()/t.getHealth());
                        float captureStrength=item.type.captureStrength+.5f;
                        if(captureStrength<=0||this.world.rand.nextFloat()<(1-(weakness/(1.5*captureStrength)*resistance))){
                            if(!mobCatcherItem.hasTagCompound())
                                mobCatcherItem.setTagCompound(new NBTTagCompound());
                            NBTTagCompound nbt=mobCatcherItem.getTagCompound();
                            nbt.setTag("CONTAINED_ENTITY",t.serializeNBT());
                            mobCatcherItem.setItemDamage(1);
                            world.playSound(null,result.hitVec.xCoord,result.hitVec.yCoord,result.hitVec.zCoord, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, .5f, 2f);
                            t.setDead();
                            dropItem(result.hitVec);
                        }else{
                            breakCatcher(result.hitVec);
                        }
                    }else{
                        breakCatcher(result.hitVec);
                    }
                }
            }
        }else if(result.typeOfHit==RayTraceResult.Type.BLOCK){
            dropItem(result.hitVec);
        }
    }

    public void breakCatcher(Vec3d where){
        ModNetworking.spawnParticle(world,EnumParticleTypes.ITEM_CRACK, where.xCoord, where.yCoord, where.zCoord, -motionX/8, motionY/8+.15, -motionZ/8, Item.getIdFromItem(ModItems.itemMobCatcherRegular));
        world.playSound(null,where.xCoord,where.yCoord,where.zCoord, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, .5f, 1.4f);
        setDead();
    }

    public void dropItem(Vec3d where){
        world.spawnEntity(new EntityItem(world,where.xCoord,where.yCoord,where.zCoord,mobCatcherItem));
        setDead();
    }
}
