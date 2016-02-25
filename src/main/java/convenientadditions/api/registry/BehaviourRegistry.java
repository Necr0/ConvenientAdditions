package convenientadditions.api.registry;

import java.util.HashMap;

import convenientadditions.api.ConAddAPI;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;
import convenientadditions.api.entity.behaviour.BehaviourAutoCrops;
import convenientadditions.api.entity.behaviour.BehaviourFloaty;
import convenientadditions.api.entity.behaviour.BehaviourHeavy;
import convenientadditions.api.entity.behaviour.BehaviourImmunityExplosion;
import convenientadditions.api.entity.behaviour.BehaviourImmunityFire;
import convenientadditions.api.entity.behaviour.BehaviourSensitivitySunlight;
import convenientadditions.api.entity.behaviour.BehaviourSensitivityWater;

public class BehaviourRegistry {
	public static final HashMap<String, IEntitySpecialItemBehaviour> REGISTRY=new HashMap<String, IEntitySpecialItemBehaviour>();
	private static boolean registered=false;
	
	public static void addBehaviour(String name,IEntitySpecialItemBehaviour behaviour){
		REGISTRY.put(name, behaviour);
	}
	
	public static IEntitySpecialItemBehaviour getBehaviour(String name){
		return REGISTRY.get(name);
	}
	
	public static String getName(IEntitySpecialItemBehaviour behaviour){
		for(String n:REGISTRY.keySet()){
			if(REGISTRY.get(n)==behaviour)
				return n;
		}
		return null;
	}
	
	public static void init(){
		if(!registered){
			initBehaviours();
			registered=true;
		}
	}
	
	private static void initBehaviours(){
		addBehaviour(ConAddAPI.NAME+":"+"autoCrops", new BehaviourAutoCrops());
		addBehaviour(ConAddAPI.NAME+":"+"floaty", new BehaviourFloaty());
		addBehaviour(ConAddAPI.NAME+":"+"heavy", new BehaviourHeavy());
		addBehaviour(ConAddAPI.NAME+":"+"immunityExplosion", new BehaviourImmunityExplosion());
		addBehaviour(ConAddAPI.NAME+":"+"immunityFire", new BehaviourImmunityFire());
		addBehaviour(ConAddAPI.NAME+":"+"sensitivitySunlight", new BehaviourSensitivitySunlight());
		addBehaviour(ConAddAPI.NAME+":"+"sensitivityWater", new BehaviourSensitivityWater());
	}
}
