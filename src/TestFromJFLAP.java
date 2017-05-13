import java.util.Iterator;

public class TestFromJFLAP {

	public static void main(String[] args) {
		
		ReadPAFromJFLAP.read();
		//ReadPAFromMCRL2.read();
		
		Iterator<String> itStates = ReadPAFromJFLAP.setOfStates.iterator();
		while(itStates.hasNext()){
   	   
			System.out.println("state: "+itStates.next());
		}

		System.out.println("**************");
      
		Iterator<String> itTransitions = ReadPAFromJFLAP.setOfTransitions.iterator();
		while(itTransitions.hasNext()){
			String trans=itTransitions.next();
			String[] actions=trans.split("\\|");
			String action=actions[2];

			String peers=action.split(":")[0];
			String fromPeer= peers.split("->")[0];
			String toPeer= peers.split("->")[1];
			String message=action.split(":")[1];
   	  
			System.out.println("transition: "+trans);
   	   
			ReadPAFromJFLAP.setOfPeers.add(fromPeer);
			ReadPAFromJFLAP.setOfPeers.add(toPeer);
			ReadPAFromJFLAP.setOfMessages.add(message);
		}
      
		Iterator<String> itPeers = ReadPAFromJFLAP.setOfPeers.iterator();
		while(itPeers.hasNext()){
			String peer=itPeers.next();
			System.out.println(peer);  	  
		}
      
		Iterator<String> itMessages = ReadPAFromJFLAP.setOfMessages.iterator();
		while(itMessages.hasNext()){
			String message=itMessages.next();
			System.out.println(message);
   	   
		}
      
		PA pa=new PA("firstExample", ReadPAFromJFLAP.initialState, ReadPAFromJFLAP.setOfPeers, ReadPAFromJFLAP.setOfStates, ReadPAFromJFLAP.setOfTransitions, ReadPAFromJFLAP.setOfMessages);
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
        	WriteCFSMsToJFLAP.buildCFSMs(pa.doProject("A"));
        	WriteCFSMsToJFLAP.buildCFSMs(pa.doProject("B"));
        	WriteCFSMsToJFLAP.buildCFSMs(pa.doProject("C"));
        } catch (Exception e) {  
        	// TODO Auto-generated catch block  
        	e.printStackTrace();  
        }  
        
        

	}

}
