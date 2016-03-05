package convenientadditions.api.registry.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import convenientadditions.api.entity.behaviour.BehaviourAutoCrops;
import convenientadditions.api.entity.behaviour.BehaviourFloaty;
import convenientadditions.api.entity.behaviour.BehaviourHeavy;
import convenientadditions.api.entity.behaviour.BehaviourImmunityExplosion;
import convenientadditions.api.entity.behaviour.BehaviourImmunityFire;
import convenientadditions.api.entity.behaviour.BehaviourSensitivitySunlight;
import convenientadditions.api.entity.behaviour.BehaviourSensitivityWater;
import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;

public class BehaviourRegistry {
	public static final HashMap<Long, IEntitySpecialItemBehaviour> REGISTRY=new HashMap<Long, IEntitySpecialItemBehaviour>();
	private static boolean registered=false;
	
	public static List<Long> API_DISCRIMINATORS=new ArrayList<Long>();
	
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
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourAutoCrops()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourFloaty()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourHeavy()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourImmunityExplosion()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourImmunityFire()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourSensitivitySunlight()));
		API_DISCRIMINATORS.add(addBehaviour(new BehaviourSensitivityWater()));
	}
}
