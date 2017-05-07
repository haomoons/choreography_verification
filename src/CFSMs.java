import java.util.Set;

public class CFSMs {

	String name;
	String initalState;
	Set<String> setOfStates, setOfTransitions, setOfMessages;
	
	CFSMs(){}
	
	CFSMs(String name, String initialState, Set<String> setOfStates,Set<String> setOfTransitions,Set<String> setOfMessages){
		this.name=name;
		this.initalState=initialState;
		this.setOfStates=setOfStates;
		this.setOfTransitions=setOfTransitions;
		this.setOfMessages=setOfMessages;
	}
	
	public String getName(){
		return name;
	}
	
	public String getInitialState(){
		return initalState;
	} 	
	
	public Set<String> getSetOfStates(){
		return setOfStates;
	} 
	
	public Set<String> getSetOfTransitions(){
		return setOfTransitions;
	} 
	
	public Set<String> getSetOfMessages(){
		return setOfMessages;
	} 
	
	public static CFSMs eliRemEpsilon(CFSMs cfsm){
				
		return new CFSMs();
	}
}
