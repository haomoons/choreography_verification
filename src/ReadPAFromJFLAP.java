import org.w3c.dom.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.xerces.parsers.DOMParser;

public class ReadPAFromJFLAP {	
	
	static Set<String> setOfPeers=new HashSet<String>();
	static Set<String> setOfTransitions=new HashSet<String>(); 
	static Set<String> setOfMessages=new HashSet<String>();;
	static Set<String> setOfStates=new HashSet<String>();
	static String initialState;
	
	public static void read()
    {
       try{
  		DOMParser parser = new DOMParser();
  		parser.parse("pa1.jff");
  		Document doc = parser.getDocument();
  		traverse_tree(doc);
       }
       catch(Exception e){
          e.printStackTrace(System.err);
	   }
    }

	public static void traverse_tree(Node node)
	{
		if(node == null) {return;}
	    int type = node.getNodeType();
	    switch (type) {
	         case Node.DOCUMENT_NODE: {
	        	 traverse_tree(((Document)node).getDocumentElement());
	    		break;
		    }
	         case Node.ELEMENT_NODE: { handleElement(node); break;}   
	         case Node.TEXT_NODE: { handleText(node); break;}
	    }    
	}
	
	private static void handleElement(Node node){
		NodeList childNodes = node.getChildNodes();	
		
		if(node.getNodeName()=="state"){
			String state=node.getAttributes().getNamedItem("id").getNodeValue()+"|"+node.getAttributes().getNamedItem("name").getNodeValue();
			int length = node.getChildNodes().getLength();
			for (int loopIndex = 0; loopIndex < length ; loopIndex++)
			{
				if(childNodes.item(loopIndex).getFirstChild()!=null){
					state=state+"|"+childNodes.item(loopIndex).getFirstChild().getNodeValue();
				}
		 	}
			
			System.out.println(state);
			setOfStates.add(state);
		}
		
		if(node.getNodeName()=="initial"){
			initialState=node.getParentNode().getAttributes().getNamedItem("id").getNodeValue()+"|"+node.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
			System.out.println(initialState);
			System.out.println("the initial state is "+node.getParentNode().getAttributes().getNamedItem("name").getNodeValue());
		}
		
		if(node.getNodeName()=="transition"){
			String transition="";
			int length = childNodes.getLength();
			for (int loopIndex = 0; loopIndex < length ; loopIndex++)
			{
				if(childNodes.item(loopIndex).getNodeName()=="from"){					
					transition=childNodes.item(loopIndex).getFirstChild().getNodeValue();
					
				}
				
				else if(node.getChildNodes().item(loopIndex).getNodeName()=="to"){
					transition=transition+"|"+childNodes.item(loopIndex).getFirstChild().getNodeValue();
					
				}
				
				else if(node.getChildNodes().item(loopIndex).getNodeName()=="read"){
					if(node.getChildNodes().item(loopIndex).getFirstChild()!=null){
						transition=transition+"|"+childNodes.item(loopIndex).getFirstChild().getNodeValue();
					}
					
					else{
						transition=transition+"|ε";
					}
					
				}
		 	}
			System.out.println(transition);
			System.out.println("---------------------");
			
			setOfTransitions.add(transition);
		}
				
		if(childNodes != null) {
			int length = childNodes.getLength();
			for (int loopIndex = 0; loopIndex < length ; loopIndex++)
			{
				traverse_tree(childNodes.item(loopIndex));
		 	}	 
		}
	}

	
	private static void handleText(Node node){
	
	} 
}
