import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;


public class WriteCFSMsToJFLAP {
	
    public static void buildCFSMs(CFSMs cfsm) throws Exception {  
    	
    	
        String path = cfsm.getName()+".xml";      
        Element root = new Element("structure");  
        Document Doc = new Document(root);   
    
        root.addContent(new Element("type").setText("fa"));
        Element automaton = new Element("automaton");
        root.addContent(automaton);
           
        Iterator<String> itStates = cfsm.getSetOfStates().iterator();
		while(itStates.hasNext()){
		   String state=itStates.next();
		   System.out.println(state);
		   Element elements = new Element("state");  
           elements.setAttribute("id", "" + state.split("\\|")[0]);  
           elements.setAttribute("name", "" + state.split("\\|")[1]);  
               
           elements.addContent(new Element("x").setText(state.split("\\|")[2] ));  
           elements.addContent(new Element("y").setText(state.split("\\|")[3]));  
           
           if(state.split("\\|")[0].equals(cfsm.getInitialState().split("\\|")[0])){
        	   elements.addContent(new Element("initial"));
           }
             
           automaton.addContent(elements);  
	    }
		
		
		 Iterator<String> itTransitions = cfsm.getSetOfTransitions().iterator();
			while(itTransitions.hasNext()){
			   String transition=itTransitions.next();
			   System.out.println(transition);
			   Element elements = new Element("transition");  
               
	           elements.addContent(new Element("from").setText(transition.split("\\|")[0]));  
	           elements.addContent(new Element("to").setText(transition.split("\\|")[1]));  
	           elements.addContent(new Element("read").setText(transition.split("\\|")[2]));  
	    
	           automaton.addContent(elements);  
		   }
               
        
        XMLOutputter XMLOut = new XMLOutputter();  
        XMLOut.output(Doc, new FileOutputStream(path));  
    }  
  	
}

