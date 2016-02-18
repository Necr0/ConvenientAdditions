package convenientadditions.api;

public class MathHelper {
	public static int overflow(int base,int increment,int capacity){
		return ((base+increment)>capacity)?(base+increment-capacity ):(((base+increment)<0)?(base+increment):0);
	}
	
	public static int drain(int base,int decrement){
		return (((base-decrement)<0)?(decrement-(base-decrement)):decrement);
	}
	
	public static double fraction(int amount,int capacity){
		return (double)amount/(double)capacity;
	}
	
	public static double percentage(int amount,int capacity){
		return fraction(amount,capacity)*100d;
	}
}
