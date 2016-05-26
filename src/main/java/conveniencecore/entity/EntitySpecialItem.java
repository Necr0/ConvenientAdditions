package conveniencecore.entity;

import java.util.ArrayList;
import java.util.List;

import conveniencecore.behaviours.BehaviourRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpecialItem extends EntityItem {
    private static final DataParameter<List<Long>> BEHAVIOURS = EntityDataManager.<List<Long>>createKey(EntitySpecialItem.class, CCDataSerializers.LISTLONG);
	public List<Long> behaviours=new ArrayList<Long>();

	public EntitySpecialItem(World world) {
		super(world);
	}

	public EntitySpecialItem(World world, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
		super(world, p_i1709_2_, p_i1709_4_, p_i1709_6_);
	}

	public EntitySpecialItem(World world, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
		super(world, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
	}

    public List<Long> getBehaviours()
    {
        return this.worldObj.isRemote ? (List<Long>)this.dataWatcher.get(BEHAVIOURS) : this.behaviours;
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
    	NBTTagCompound bnbt=new NBTTagCompound();
    	bnbt.setInteger("COUNT", behaviours.size());
    	int i=0;
    	for(long b:behaviours){
    		bnbt.setLong("B"+i,b);
    		i++;
    	}
        par1NBTTagCompound.setTag("BEHAVIOURS", bnbt);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	this.behaviours=new ArrayList<Long>();
    	if(par1NBTTagCompound.hasKey("BEHAVIOURS")){
	    	NBTTagCompound bnbt=par1NBTTagCompound.getCompoundTag("BEHAVIOURS");
	    	for(int i=0;i<bnbt.getInteger("COUNT");i++){
	    		addBehaviourSilent(bnbt.getLong("B"+i));
	    	}
	    	syncBehaviours();
    	}
    }
    
    public void onCreate()
    {
    	//System.out.println("exist");
    	for(long b:behaviours){
    		BehaviourRegistry.getBehaviour(b).onCreate(this);
    	}
    }
    
    @Override
    public void onUpdate()
    {
    	for(long b:getBehaviours()){
    		BehaviourRegistry.getBehaviour(b).onItemEntityUpdate(this);
    	}
    	super.onUpdate();
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
    	boolean flag=false;
    	for(long b:getBehaviours()){
    		if(!BehaviourRegistry.getBehaviour(b).onAttackItemEntityFrom(this, source, damage))
    			flag=true;
    	}
    	if(flag)
    		return false;
    	return super.attackEntityFrom(source, damage);
    }

    
    //DATA STUFF
    protected void entityInit()
    {
        this.getDataManager().register(BEHAVIOURS, new ArrayList<Long>());
        super.entityInit();
    }
    
    public void setBehaviours(List<Long> behaviours)
    {
        this.getDataManager().set(BEHAVIOURS, behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
    }
	
	public void addBehaviour(long... behaviours){
		for(Long b:behaviours){
			this.behaviours.add(b);
		}
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
	}
	
	public void addBehaviours(List<Long> behaviours){
		for(Long b:behaviours){
			this.behaviours.add(b);
		}
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
	}
	
	public void addBehaviourSilent(long... behaviours){
		for(Long b:behaviours){
			this.behaviours.add(b);
		}
	}
	
	public void addBehavioursSilent(List<Long> behaviours){
		for(Long b:behaviours){
			this.behaviours.add(b);
		}
	}
	
	public void syncBehaviours(){
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
	}
}
