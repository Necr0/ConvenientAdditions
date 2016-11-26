package convenientadditions.api;

public interface IMatcher {
	
	class LockedObject{
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
	
	boolean matches(IMatcher matcher);

	class matcherANY implements IMatcher {
		public boolean matches(IMatcher matcher){return true;}
	}
	
	static boolean matches(IMatcher matcher1,IMatcher matcher2){
		return matcher1.matches(matcher2)||matcher2.matches(matcher1);
	}
}
