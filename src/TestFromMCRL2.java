import java.util.Iterator;

public class TestFromMCRL2 {

	public static void main(String[] args) {
		
		ReadPAFromMCRL2.read();
		
		Iterator<String> itStates = ReadPAFromMCRL2.setOfStates.iterator();
		while(itStates.hasNext()){
   	   
			System.out.println("state: "+itStates.next());
		}

		System.out.println("**************");
      
		Iterator<String> itTransitions = ReadPAFromMCRL2.setOfTransitions.iterator();
		while(itTransitions.hasNext()){
			String trans=itTransitions.next();
			String[] actions=trans.split("\\|");
			String action=actions[2];

			String peers=action.split(":")[0];
			String fromPeer= peers.split("->")[0];
			String toPeer= peers.split("->")[1];
			String message=action.split(":")[1];
   	  
			System.out.println("transition: "+trans);
   	
		}
      
		Iterator<String> itPeers = ReadPAFromMCRL2.setOfPeers.iterator();
		while(itPeers.hasNext()){
			String peer=itPeers.next();
			System.out.println(peer);  	  
		}
      
		Iterator<String> itMessages = ReadPAFromMCRL2.setOfMessages.iterator();
		while(itMessages.hasNext()){
			String message=itMessages.next();
			System.out.println(message);
   	   
		}
      
		PA pa=new PA("firstExample", ReadPAFromMCRL2.initialState, ReadPAFromMCRL2.setOfPeers, ReadPAFromMCRL2.setOfStates, ReadPAFromMCRL2.setOfTransitions, ReadPAFromMCRL2.setOfMessages);
		System.out.println(pa.getName());
		System.out.println(pa.doProject("A").getName());
		System.out.println(pa.doProject("A").getInitialState());
		pa.doProject("A").getSetOfTransitions();
		System.out.println("The number of state is "+pa.doProject("A").getSetOfStates().size());
		System.out.println("The number of transition is "+pa.doProject("A").getSetOfTransitions().size());
		Iterator<String> it = pa.doProject("A").getSetOfTransitions().iterator();
		while(it.hasNext()){
		   String tr=it.next();
		   System.out.println(tr.split("\\|")[2]);
	    }
		
		System.out.println("end");
		System.out.println("ε");
	 	
        try { 
        	WriteCFSMsToMCRL2.buildCFSMs(pa.doProject("A"));
        	WriteCFSMsToMCRL2.buildCFSMs(pa.doProject("B"));
        	WriteCFSMsToMCRL2.buildCFSMs(pa.doProject("C"));
        } catch (Exception e) {  
        	// TODO Auto-generated catch block  
        	e.printStackTrace();  
        }  
        
        

	}

}
