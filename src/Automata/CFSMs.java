package Automata;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import FindAllPaths.Search;

public class CFSMs {

	String name;
	String initalState;
	Set<String> setOfStates, setOfTransitions, setOfMessages;
	
	public CFSMs(){}
	
	public CFSMs(String name, String initialState, Set<String> setOfStates,Set<String> setOfTransitions,Set<String> setOfMessages){
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
	
	public Set<String> getAllEpsilonTransitions(){
		Set<String> setOfEpsilonTransitions= new HashSet<String>();
		Iterator<String> it = this.getSetOfTransitions().iterator();
		while(it.hasNext()){
		   String tr=it.next();
		   if(tr.split("\\|")[2].equals("ε"))
			   setOfEpsilonTransitions.add(tr);
	    }
		
		return setOfEpsilonTransitions;
	}
	
	public Set<String> getRestTransitions(){
		Set<String> setOfRestTransitions= new HashSet<String>();
		Iterator<String> it = this.getSetOfTransitions().iterator();
		while(it.hasNext()){
		   String tr=it.next();
		   if(!tr.split("\\|")[2].equals("ε"))
			   setOfRestTransitions.add(tr);
	    }
		
		return setOfRestTransitions;
	}
	
	public ArrayList<String> getListOfPaths(String s, String t){
		
		
		return Search.getResult();
    }
	
	public Set<String> findRemovableEpsilonTransitions(){
		Set<String> SetOfRemovableEpsilonTransitions= new HashSet<String>();
		
		Set<String> SetOfAllEpsilonTransitions= this.getAllEpsilonTransitions();
		Set<String> SetOfRestTransitions=this.getRestTransitions();
		Iterator<String> itEpsilon = SetOfAllEpsilonTransitions.iterator();
		Iterator<String> itRest = SetOfRestTransitions.iterator();
		while(itEpsilon.hasNext()){
			String trEpsilon=itEpsilon.next();
			
			while(itRest.hasNext()){
				  String trRest=itRest.next();
				  
				  if(trEpsilon.split("\\|")[0].equals(trRest.split("\\|")[0]) && !trRest.split("\\|")[2].equals("ε")){
					  
					  continue;
				  }
				  
				  else{
					  SetOfRemovableEpsilonTransitions.add(trEpsilon);
				  }
				
			}
		 
		   
			   
	    }
		return SetOfRemovableEpsilonTransitions;
	}
	
	
	public CFSMs eliRemEpsilon(){
				
		return new CFSMs();
	}
}
