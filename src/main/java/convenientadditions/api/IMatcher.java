package convenientadditions.api;

public interface IMatcher {
	
	public static class LockedObject{
		public IMatcher matcher;
		public Object t;
		
		public LockedObject(Object value){
			this.matcher=new matcherANY();
			this.t=value;
		}
		
		public boolean validate(IMatcher matcherIn){
			return matcher.matches(matcherIn)||matcherIn.matches(matcher);
		}
		
		public Object getValue(){
			return t;
		}
	}
	
	public boolean matches(IMatcher matcher);

	public static class matcherANY implements IMatcher {
		public boolean matches(IMatcher matcher){return true;}
	}
	
	public static boolean matches(IMatcher matcher1,IMatcher matcher2){
		return matcher1.matches(matcher2)||matcher2.matches(matcher1);
	}
}
