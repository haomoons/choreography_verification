import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		
		ReadPA.read();
		
		Iterator<String> itStates = ReadPA.setOfStates.iterator();
		while(itStates.hasNext()){
   	   
			System.out.println("state: "+itStates.next());
		}

		System.out.println("**************");
      
		Iterator<String> itTransitions = ReadPA.setOfTransitions.iterator();
		while(itTransitions.hasNext()){
			String trans=itTransitions.next();
			String[] actions=trans.split("\\|");
			String action=actions[2];

			String peers=action.split(":")[0];
			String fromPeer= peers.split("->")[0];
			String toPeer= peers.split("->")[1];
			String message=action.split(":")[1];
   	  
			System.out.println("transition: "+trans);
   	   
			ReadPA.setOfPeers.add(fromPeer);
			ReadPA.setOfPeers.add(toPeer);
			ReadPA.setOfMessages.add(message);
		}
      
		Iterator<String> itPeers = ReadPA.setOfPeers.iterator();
		while(itPeers.hasNext()){
			String peer=itPeers.next();
			System.out.println(peer);  	  
		}
      
		Iterator<String> itMessages = ReadPA.setOfMessages.iterator();
		while(itMessages.hasNext()){
			String message=itMessages.next();
			System.out.println(message);
   	   
		}
      
		PA pa=new PA("firstExample", ReadPA.initialState, ReadPA.setOfPeers, ReadPA.setOfStates, ReadPA.setOfTransitions, ReadPA.setOfMessages);
		System.out.println(pa.getName());
		System.out.println(PA.doProject(pa, "A").getName());
		System.out.println(PA.doProject(pa, "A").getInitialState());
		PA.doProject(pa, "A").getSetOfTransitions();
		System.out.println("The number of state is "+PA.doProject(pa, "A").getSetOfStates().size());
		System.out.println("The number of transition is "+PA.doProject(pa, "A").getSetOfTransitions().size());
		Iterator<String> it = PA.doProject(pa, "C").getSetOfTransitions().iterator();
		while(it.hasNext()){
		   String tr=it.next();
		   System.out.println(tr.split("\\|")[2]);
	    }
		
		System.out.println("end");
		System.out.println("ε");
	 	
        try {  
        	WriteCFSMs.buildCFSMs(PA.doProject(pa, "C"));
        } catch (Exception e) {  
        	// TODO Auto-generated catch block  
        	e.printStackTrace();  
        }  

	}

}
