package conveniencecore.util;

import java.util.UUID;

public class MathHelper {
	public static int overflow(int base,int increment,int capacity){
		return ((base+increment)>capacity)?(base+increment-capacity ):(((base+increment)<0)?(base+increment):0);
	}
	
	public static int drain(int base,int decrement){
		return (((base-decrement)<0)?(decrement+(base-decrement)):decrement);
	}
	
	public static double fraction(int amount,int capacity){
		return (double)amount/(double)capacity;
	}
	
	public static double percentage(int amount,int capacity){
		return fraction(amount,capacity)*100d;
	}
	
	public static long[] UUIDtoLONG(UUID uuid){
		long[] ret=new long[2];
		ret[0]=uuid.getMostSignificantBits();
		ret[1]=uuid.getLeastSignificantBits();
		return ret;
	}

	
	public static UUID LONGtoUUID(long[] longs){
		return new UUID(longs[0], longs[1]);
	}
	public static class Bitmask{
		long a;
		public Bitmask(long a){
			this.a=a;
		}
		public void set(long a){
			this.a=a;
		}
		public void setBit(int a,boolean b){
			if(b)
				this.a=this.a|(1<<a);
			else
				this.a=this.a&(~(1<<a));
		}
		public boolean getBit(int a){
			return (this.a&(1<<a))!=0;
		}
		public boolean[] getArray(int lenght){
			boolean[] ret=new boolean[lenght];
			for(int i=0;i<lenght;i++)
				ret[i]=getBit(i);
			return ret;
		}
		public long get(){
			return a;
		}
	}
}
