package Automata;
import java.util.*;


public class PA {

	String name;
	String initalState;
	Set<String> setOfPeers, setOfStates, setOfTransitions, setOfMessages;
	
	public PA(String name, String initialState, Set<String> setOfPeers, Set<String> setOfStates,Set<String> setOfTransitions,Set<String> setOfMessages){
		this.name=name;
		this.initalState=initialState;
		this.setOfPeers=setOfPeers;
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
	
	public Set<String> getSetOfPeers(){
		return setOfPeers;
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
	
	public CFSMs doProject(String peer){
		
		String newName=peer;
		
		String newInitialState=this.getInitialState();
		
		Set<String> newSetOfStates=this.getSetOfStates();
		
		Set<String> newSetOfTransitions=new HashSet<String>();
		Iterator<String> itTransitions = this.getSetOfTransitions().iterator();
	    while(itTransitions.hasNext()){
	    	 String transition=itTransitions.next();
	    	 String fromPeer=transition.split("\\|")[2].split("->")[0];
	    	 String toPeer=transition.split("\\|")[2].split("->")[1].split(":")[0];
	    	 String message=transition.split("\\|")[2].split("->")[1].split(":")[1];	    	 
	    	 if(fromPeer.equals(peer)){
	    		 String newTransition=transition.split("\\|")[0]+"|"+transition.split("\\|")[1]+"|"+"!"+message+toPeer;	    	
	    		 newSetOfTransitions.add(newTransition);
	    	 }
	    	   
	    	 else if(toPeer.equals(peer)){
	    		 String newTransition=transition.split("\\|")[0]+"|"+transition.split("\\|")[1]+"|"+"?"+message+fromPeer;    	
	    		 newSetOfTransitions.add(newTransition);
	    	 }
	    	   
	    	 else{
	    		 String newTransition=transition.split("\\|")[0]+"|"+transition.split("\\|")[1]+"|"+"ε";	    	
	    		 newSetOfTransitions.add(newTransition);
	    	 }
	    	  
	    }
	    
	    Set<String> newSetOfMessages=this.getSetOfMessages();
	    
		return new CFSMs(newName, newInitialState, newSetOfStates, newSetOfTransitions, newSetOfMessages);
	}

}
