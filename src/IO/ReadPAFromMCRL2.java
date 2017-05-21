package IO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ReadPAFromMCRL2 {

	static Set<String> setOfPeers=new HashSet<String>();
	static Set<String> setOfTransitions=new HashSet<String>(); 
	static Set<String> setOfMessages=new HashSet<String>();;
	static Set<String> setOfStates=new HashSet<String>();
	static String initialState;
	
	public static String getInitialState(){
    	return initialState;
    }
    
    public static Set<String> getSetOfPeers(){
    	return setOfPeers;
    }
    
    public static Set<String> getSetOfTransitions(){
    	return setOfTransitions;
    }
    
    public static Set<String> getSetOfMessages(){
    	return setOfMessages;
    }
    
    public static Set<String> getSetOfStates(){
    	return setOfStates;
    }
	
	
	public static void read() {
		  
		  try {
			  		File file = new File("pa2.fsm");
			  		if(file.isFile() && file.exists()) {
			  			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			  			BufferedReader br = new BufferedReader(isr);
			  			String line = null;
			  			while ((line = br.readLine()) != null) {
			  				System.out.println(line);
			  				if(!line.equals("---")){
			  					setOfStates.add(line.split(" ")[0]);
			  					setOfStates.add(line.split(" ")[1]);
			  					setOfTransitions.add(line.split(" ")[0]+"|"+line.split(" ")[1]+"|"+line.split(" ")[2].replace("\"", ""));
			  					setOfMessages.add(line.split(" ")[2].replace("\"", "").split(":")[1]);
			  					setOfPeers.add(line.split(" ")[2].replace("\"", "").split(":")[0].split("->")[0]);
			  					setOfPeers.add(line.split(" ")[2].replace("\"", "").split(":")[0].split("->")[1]);
			  				}
			  			}
			  			br.close();
		    } else {
		          System.out.println("file does not exist!");
		    }
		  } catch (Exception e) {
		    System.out.println("error in reading file!");
		  }
		  
		  }		  	  
		 
}
