package convenientadditions.api.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import convenientadditions.api.registry.behaviour.BehaviourRegistry;

public abstract class EntitySpecialItem extends EntityItem {
	List<Long> behaviour=new ArrayList<Long>();

	public EntitySpecialItem(World p_i1711_1_) {
		super(p_i1711_1_);
	}

	public EntitySpecialItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
		super(p_i1709_1_, p_i1709_2_, p_i1709_4_, p_i1709_6_);
	}

	public EntitySpecialItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
		super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
	}
	
	public void addBehaviour(IEntitySpecialItemBehaviour... behaviours){
		for(IEntitySpecialItemBehaviour b:behaviours){
			behaviour.add(BehaviourRegistry.getDiscriminator(b));
		}
	}
	
	public void addBehaviour(long... behaviours){
		for(Long b:behaviours){
			behaviour.add(b);
		}
	}
	
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
    	NBTTagCompound bnbt=new NBTTagCompound();
    	bnbt.setInteger("COUNT", behaviour.size());
    	int i=0;
    	for(long b:behaviour){
    		bnbt.setLong("B"+i,b);
    		i++;
    	}
        par1NBTTagCompound.setTag("BEHAVIOUR", bnbt);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	this.behaviour=new ArrayList<Long>();
    	if(par1NBTTagCompound.hasKey("BEHAVIOUR")){
	    	NBTTagCompound bnbt=par1NBTTagCompound.getCompoundTag("BEHAVIOUR");
	    	for(int i=0;i<bnbt.getInteger("COUNT");i++){
	    		addBehaviour(bnbt.getLong("B"+i));
	    	}
    	}
    }
    
    ///
    // Isn't necessarily triggered!
    ///
    public void onCreate()
    {
    	System.out.println("exist");
    	for(long b:behaviour){
    		BehaviourRegistry.getBehaviour(b).onCreate(this);
    	}
    }
    
    @Override
    public void onUpdate()
    {
    	for(long b:behaviour){
    		BehaviourRegistry.getBehaviour(b).onItemEntityUpdate(this);
    	}
    	super.onUpdate();
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
    	boolean flag=false;
    	for(long b:behaviour){
    		if(!BehaviourRegistry.getBehaviour(b).onAttackItemEntityFrom(this, source, damage))
    			flag=true;
    	}
    	if(flag)
    		return false;
    	return super.attackEntityFrom(source, damage);
    }
}
