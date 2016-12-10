package convenientadditions.entity.launchingArrow;

import convenientadditions.api.ExtendedExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityLaunchingArrow extends EntityArrow {
    public static final DataParameter<Byte> VARIANT = EntityDataManager.<Byte>createKey(EntityLaunchingArrow.class, DataSerializers.BYTE);

    public EnumLaunchingArrowVariant variant = EnumLaunchingArrowVariant.slime;

    public EntityLaunchingArrow(World worldIn) {
        super(worldIn);
        if(getVariant()==EnumLaunchingArrowVariant.creeper)
            this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    public EntityLaunchingArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        if(getVariant()==EnumLaunchingArrowVariant.creeper)
            this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    public EntityLaunchingArrow(World worldIn, EntityLivingBase shooter, EnumLaunchingArrowVariant variant) {
        super(worldIn, shooter);
        this.setVariant(variant);
        if(getVariant()==EnumLaunchingArrowVariant.creeper)
            this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    public void onUpdate() {
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
        super.onUpdate();

        if (getVariant()==EnumLaunchingArrowVariant.slime && this.inGround && !getEntityWorld().isRemote) {
            List<EntityLivingBase> es = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class,new AxisAlignedBB(posX-1.5D,posY-1.5D,posZ-1.5D,posX+1.5D,posY+1.5D,posZ+1.5D));
            for (EntityLivingBase e:es) {
                if(e.getDistanceSqToEntity(this)<=2.25){
                    ExtendedExplosion.newExplosion(EnumLaunchingArrowVariant.getExtendedExplosionFromVariant(getVariant(), this));
                    this.setDead();
                    break;
                }
            }
        }
    }

    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        ExtendedExplosion.newExplosion(EnumLaunchingArrowVariant.getExtendedExplosionFromVariant(getVariant(), this));
        this.setDead();
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setByte("variant", (byte) this.variant.ordinal());
    }

    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        if (tagCompund.hasKey("variant"))
            setVariant(EnumLaunchingArrowVariant.values()[tagCompund.getByte("variant")]);
        else
            setVariant(EnumLaunchingArrowVariant.values()[0]);
    }

    @Override
    protected void entityInit() {
        this.getDataManager().register(VARIANT, (byte) 2);
        super.entityInit();
    }

    public EnumLaunchingArrowVariant getVariant() {
        if (getEntityWorld().isRemote) {
            Byte b = this.getDataManager().get(VARIANT);
            if (b != null && b < EnumLaunchingArrowVariant.values().length)
                return EnumLaunchingArrowVariant.values()[b];
            else
                return EnumLaunchingArrowVariant.slime;
        } else {
            return this.variant;
        }
    }

    public void setVariant(EnumLaunchingArrowVariant variant) {
        this.variant = variant;
        this.getDataManager().set(VARIANT, (byte) variant.ordinal());
        this.getDataManager().setDirty(VARIANT);
    }

    public enum EnumLaunchingArrowVariant {
        creeper(3f, true, true, .75f, .9f),
        blast(3f, false, true, .4f, 1.1f),
        slime(3f, false, false, 0f, 1.6f);

        public float strength = 3f;
        public boolean destroyBlocks = true;
        public boolean doDamage = true;
        public float damageMultiplier = 1f;
        public float knockbackMultiplier = 1f;

        EnumLaunchingArrowVariant(float strength, boolean destroyBlocks, boolean doDamage, float damageMultiplier, float knockbackMultiplier) {
            this.strength = strength;
            this.destroyBlocks = destroyBlocks;
            this.doDamage = doDamage;
            this.damageMultiplier = damageMultiplier;
            this.knockbackMultiplier = knockbackMultiplier;
        }

        public static ExtendedExplosion getExtendedExplosionFromVariant(EnumLaunchingArrowVariant variant, Entity entity) {
            return new ExtendedExplosion(entity.getEntityWorld(), entity, entity.posX, entity.posY, entity.posZ, variant.strength, false, true).setDamageMultiplier(variant.damageMultiplier).setDamaging(variant.doDamage).setGrieving(variant.destroyBlocks).setKnockbackMultiplier(variant.knockbackMultiplier);
        }
    }
}
