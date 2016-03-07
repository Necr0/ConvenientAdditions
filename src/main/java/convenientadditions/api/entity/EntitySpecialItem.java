package convenientadditions.api.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;
import convenientadditions.api.network.PacketEntitySpecialItemBehaviours;
import convenientadditions.api.registry.behaviour.BehaviourRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public abstract class EntitySpecialItem extends EntityItem {
	public List<Long> behaviour=new ArrayList<Long>();
	private boolean behaviourUpdateScheduled=false;

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
	
	public void addBehaviour(List<Long> behaviours){
		this.behaviour=behaviours;
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
    
    public void scheduleBehaviourUpdate(){
    	this.behaviourUpdateScheduled=true;
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
    	if(behaviourUpdateScheduled){
    		updateBehaviours();
    		behaviourUpdateScheduled=false;
    	}
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
    
    public void updateBehaviours(){
    	try {
			PacketEntitySpecialItemBehaviours p=getCleanBehaviourPacket();
			p.setInformation(this, this.behaviour);
			getSimpleNetworkWrapper().sendToAllAround(p, new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 120));
		} catch (Exception e) {
			e.printStackTrace();
		}
    };
    
    public abstract PacketEntitySpecialItemBehaviours getCleanBehaviourPacket();
    
    public abstract SimpleNetworkWrapper getSimpleNetworkWrapper();
}
