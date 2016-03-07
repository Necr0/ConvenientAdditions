package convenientadditions.api.registry.behaviour;

import java.util.HashMap;

import convenientadditions.api.entity.behaviour.BehaviourAutoCrops;
import convenientadditions.api.entity.behaviour.BehaviourFloaty;
import convenientadditions.api.entity.behaviour.BehaviourHeavy;
import convenientadditions.api.entity.behaviour.BehaviourImmunityExplosion;
import convenientadditions.api.entity.behaviour.BehaviourImmunityFire;
import convenientadditions.api.entity.behaviour.BehaviourSensitivitySunlight;
import convenientadditions.api.entity.behaviour.BehaviourSensitivityWater;
import convenientadditions.api.entity.behaviour.BehaviourSunlightChargeable;
import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;

public class BehaviourRegistry {
	public static final HashMap<Long, IEntitySpecialItemBehaviour> REGISTRY=new HashMap<Long, IEntitySpecialItemBehaviour>();
	private static boolean registered=false;
	
	public static HashMap<String,Long> API_DISCRIMINATORS=new HashMap<String,Long>();
	
	public static long addBehaviour(IEntitySpecialItemBehaviour behaviour){
		long ret=getUnoccupiedDiscrimiator();
		REGISTRY.put(ret, behaviour);
		return ret;
	}
	
	public static long addBehaviour(long discriminator,IEntitySpecialItemBehaviour behaviour){
		REGISTRY.put(discriminator, behaviour);
		return discriminator;
	}
	
	public static IEntitySpecialItemBehaviour getBehaviour(long discriminator){
		return REGISTRY.get(discriminator);
	}
	
	public static Long getDiscriminator(IEntitySpecialItemBehaviour behaviour){
		for(Long n:REGISTRY.keySet()){
			if(REGISTRY.get(n)==behaviour)
				return n;
		}
		return null;
	}
	
	public static long getUnoccupiedDiscrimiator(){
		long r=0;
		for(long n:REGISTRY.keySet()){
			if(n==r||n>r){
				r=n+1;
			}
		}
		return r;
	}
	
	public static void init(){
		if(!registered){
			initBehaviours();
			registered=true;
		}
	}
	
	private static void initBehaviours(){
		API_DISCRIMINATORS.put("autoCrops",addBehaviour(new BehaviourAutoCrops()));
		API_DISCRIMINATORS.put("floaty",addBehaviour(new BehaviourFloaty()));
		API_DISCRIMINATORS.put("heavy",addBehaviour(new BehaviourHeavy()));
		API_DISCRIMINATORS.put("immunityExplosion",addBehaviour(new BehaviourImmunityExplosion()));
		API_DISCRIMINATORS.put("immunityFire",addBehaviour(new BehaviourImmunityFire()));
		API_DISCRIMINATORS.put("sensitivitySunlight",addBehaviour(new BehaviourSensitivitySunlight()));
		API_DISCRIMINATORS.put("sensitivityWater",addBehaviour(new BehaviourSensitivityWater()));
		API_DISCRIMINATORS.put("sunlightChargeable",addBehaviour(new BehaviourSunlightChargeable()));
	}
}
