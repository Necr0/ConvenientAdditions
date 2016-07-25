package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSounds {
	public static SoundEvent bookTurn;
	public static SoundEvent bookSlam;
	
	public static void init(){
		int id=0;
	    bookTurn=register(id++,"bookTurn");
	    bookSlam=register(id++,"bookSlam");
	}
	
	public static SoundEvent register(int id,String loc){
		ResourceLocation l=new ResourceLocation(ConvenientAdditions.MODID+":"+loc);
		SoundEvent e=new SoundEvent(l);
		SoundEvent.REGISTRY.register(id, l, e);
		return e;
	}
}
