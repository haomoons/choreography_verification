package automata;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import findAllPaths.Search;

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
		int i=Integer.parseInt(s);
		int j=Integer.parseInt(t);
		Search search=new Search();
		search.getStart(getSetOfStates(), getSetOfTransitions(), i, j);			
		return search.getResult();
    }
	
	
	public Set<String> getSetOfWords(ArrayList<String> arrList){
		HashSet<String> setOfWords=new HashSet<String>();
		
		String[] arrOfTransitions= (String[])this.getSetOfTransitions().toArray(new String[this.getSetOfTransitions().size()]);
		
		for(int i=0;i<arrList.size();i++){
			String[] arrOfPaths=arrList.get(i).split("->");
			String words="";
			for(int j=0; j<arrOfPaths.length-1; j++){
				for(int k=0; k<arrOfTransitions.length;k++){
					if(arrOfPaths[j].equals(arrOfTransitions[k].split("\\|")[0]) && arrOfPaths[j+1].equals(arrOfTransitions[k].split("\\|")[1])){
						if(words.equals("")){
							words=words+arrOfTransitions[k].split("\\|")[2];
						}
						else
							words=words+"->"+arrOfTransitions[k].split("\\|")[2];
					}
				}
			}
			
			if(!words.equals("")){
				setOfWords.add(words);
			}
		}
		return setOfWords;
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
				  
				  if(trEpsilon.split("\\|")[0].equals(trRest.split("\\|")[0]) && !trRest.split("\\|")[2].equals("Îµ")){
					  
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
