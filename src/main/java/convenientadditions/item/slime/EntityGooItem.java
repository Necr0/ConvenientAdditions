package convenientadditions.item.slime;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import convenientadditions.api.util.Helper;

public class EntityGooItem extends EntityItem {

	public boolean explosionImmunity;
	public boolean waterSensitivity;
	public boolean sunlightSensitivity;
	public Random rnd=new Random();
	
	public EntityGooItem(World p_i1711_1_) {
		super(p_i1711_1_);
		this.isImmuneToFire=false;
		this.explosionImmunity=false;
		this.waterSensitivity=false;
		this.sunlightSensitivity=false;
	    this.delayBeforeCanPickup = 20;
	}
	
	public EntityGooItem(World p_i1711_1_
			,boolean fireImmunity,boolean explosionImmunity,boolean waterSensitivity,boolean sunlightSensitivity) {
		super(p_i1711_1_);
		this.isImmuneToFire=fireImmunity;
		this.explosionImmunity=explosionImmunity;
		this.waterSensitivity=waterSensitivity;
		this.sunlightSensitivity=sunlightSensitivity;
	    this.delayBeforeCanPickup = 20;
	}

	public EntityGooItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_,double p_i1709_6_
			,boolean fireImmunity,boolean explosionImmunity,boolean waterSensitivity,boolean sunlightSensitivity) {
		super(p_i1709_1_, p_i1709_2_, p_i1709_4_, p_i1709_6_);
		this.isImmuneToFire=fireImmunity;
		this.explosionImmunity=explosionImmunity;
		this.waterSensitivity=waterSensitivity;
		this.sunlightSensitivity=sunlightSensitivity;
	    this.delayBeforeCanPickup = 20;
	}

	public EntityGooItem(World world, double xc, double yc,double zc, ItemStack stack
			,boolean fireImmunity,boolean explosionImmunity,boolean waterSensitivity,boolean sunlightSensitivity) {
		super(world, xc, yc, zc, stack);
		this.isImmuneToFire=fireImmunity;
		this.explosionImmunity=explosionImmunity;
		this.waterSensitivity=waterSensitivity;
		this.sunlightSensitivity=sunlightSensitivity;
	    this.delayBeforeCanPickup = 20;
	}

    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
    	if (explosionImmunity && p_70097_1_.isExplosion())
	    {
	        return false;
	    }
    	if (isImmuneToFire && (p_70097_1_.isFireDamage()))
	    {
	        return false;
	    }
    	return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    }

    @Override
    public void onUpdate()
    {
    	if(this.handleWaterMovement()&&waterSensitivity&&rnd.nextInt(21)==0)
    		this.setDead();
    	if(this.worldObj.isDaytime() && !this.worldObj.isRaining() && Helper.canEntitySeeSky(this)&&sunlightSensitivity&&rnd.nextInt(15)==0)
    		this.setFire(5);
    	if(this.worldObj.isRaining() && Helper.canEntitySeeSky(this)&&waterSensitivity&&rnd.nextInt(21)==0)
    		this.setDead();
    	super.onUpdate();
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("fireImmunity", this.isImmuneToFire);
        par1NBTTagCompound.setBoolean("explosionImmunity", this.explosionImmunity);
        par1NBTTagCompound.setBoolean("waterSensitivity", this.waterSensitivity);
        par1NBTTagCompound.setBoolean("sunlightSensitivity", this.sunlightSensitivity);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	this.isImmuneToFire=par1NBTTagCompound.getBoolean("fireImmunity");
        this.explosionImmunity=par1NBTTagCompound.getBoolean("explosionImmunity");
        this.waterSensitivity=par1NBTTagCompound.getBoolean("waterSensitivity");
        this.sunlightSensitivity=par1NBTTagCompound.getBoolean("sunlightSensitivity");
    }
}
