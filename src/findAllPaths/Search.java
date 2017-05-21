package findAllPaths;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;  
  
  
public class Search {  
    /* 临时�?存路径节点的栈 */  
    public  Stack<Node> stack = new Stack<Node>();  
    /* 存储路径的集�?� */  
    public  ArrayList<Object[]> sers = new ArrayList<Object[]>(); 
    public  ArrayList<String> result = new ArrayList<String>(); 
    
    public  ArrayList<String> getResult(){
    	return result;
    }
  
    /* 判断节点是�?�在栈中 */  
    public  boolean isNodeInStack(Node node)  
    {  
        Iterator<Node> it = stack.iterator();  
        while (it.hasNext()) {  
            Node node1 = (Node) it.next();  
            if (node == node1)  
                return true;  
        }  
        return false;  
    }  
  
    /* 此时栈中的节点组�?一�?�所求路径，转储并打�?�输出 */  
    public  void showAndSavePath()  
    {  
    	String paths="";
        Object[] o = stack.toArray();  
        for (int i = 0; i < o.length; i++) {  
            Node nNode = (Node) o[i];  
              
            if(i < (o.length - 1)) { 
               // System.out.print(nNode.getName() + "->");  
            	paths=paths+nNode.getName() + "->";
            }
            else {
               // System.out.print(nNode.getName());
            	paths=paths+nNode.getName();
            }
        }  
        sers.add(o); /* 转储 */ 
        System.out.println("the path: "+ paths);
        result.add(paths);
        System.out.println("\n"); 
    }  
  
    /* 
     * 寻找路径的方法  
     * cNode: 当�?的起始节点currentNode 
     * pNode: 当�?起始节点的上一节点previousNode 
     * sNode: 最�?的起始节点startNode 
     * eNode: 终点endNode 
     */  
    public boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {  
        Node nNode = null;  
        /* 如果符�?��?�件判断说明出现环路，�?能�?顺�?�该路径继续寻路，返回false */  
        if (cNode != null && pNode != null && cNode == pNode)  
            return false;  
  
        if (cNode != null) {  
            int i = 0;  
            /* 起始节点入栈 */  
            stack.push(cNode);  
            /* 如果该起始节点就是终点，说明找到一�?�路径 */  
            if (cNode == eNode)  
            {  
                /* 转储并打�?�输出该路径，返回true */  
                showAndSavePath();  
                return true;  
            }  
            /* 如果�?是,继续寻路 */  
            else  
            {  
                /*  
                 * 从与当�?起始节点cNode有连接关系的节点集中按顺�?�??历得到一个节点 
                 * 作为下一次递归寻路时的起始节点  
                 */  
                nNode = cNode.getRelationNodes().get(i);  
                while (nNode != null) {  
                    /* 
                     * 如果nNode是最�?的起始节点或者nNode就是cNode的上一节点或者nNode已�?在栈中 ，  
                     * 说明产生环路 ，应�?新在与当�?起始节点有连接关系的节点集中寻找nNode 
                     */  
                    if (pNode != null  
                            && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {  
                        i++;  
                        if (i >= cNode.getRelationNodes().size())  
                            nNode = null;  
                        else  
                            nNode = cNode.getRelationNodes().get(i);  
                        continue;  
                    }  
                    /* 以nNode为新的起始节点，当�?起始节点cNode为上一节点，递归调用寻路方法 */  
                    if (getPaths(nNode, cNode, sNode, eNode))/* 递归调用 */  
                    {  
                        /* 如果找到一�?�路径，则弹出栈顶节点 */  
                        stack.pop();  
                    }  
                    /* 继续在与cNode有连接关系的节点集中测试nNode */  
                    i++;  
                    if (i >= cNode.getRelationNodes().size())  
                        nNode = null;  
                    else  
                        nNode = cNode.getRelationNodes().get(i);  
                }  
                /*  
                 * 当�??历完所有与cNode有连接关系的节点�?�， 
                 * 说明在以cNode为起始节点到终点的路径已�?全部找到  
                 */  
                stack.pop();  
                return false;  
            }  
        } else  
            return false;  
    }  
  
    public void getStart(Set<String> setOfStates, Set<String> setOfTransitions, int s, int t) {  
        /* 定义节点关系 */  
//        int nodeRalation[][] =  
//        {  
//            {1},      //0  
//            {0,5,2,3},//1  
//            {1,4},    //2  
//            {1,4},    //3  
//            {2,3,5},  //4  
//            {1,4}     //5  
//        };  
        
//        int nodeRalation[][] =  
//            {  
//                {1,2},      //0  
//                {3},//1  
//                {1,3},    //2  
//                {},    //3             
//            };  
        
//        Set<String> setOfTransitions = new HashSet<String>();
//        setOfTransitions.add("1|2|a");
//        setOfTransitions.add("1|3|b");
//        setOfTransitions.add("3|2|c");
//        setOfTransitions.add("2|4|d");
//        setOfTransitions.add("3|4|e");
		
//		Set<String> setOfStates = new HashSet<String>();
//		setOfStates.add("1");
//		setOfStates.add("2");
//		setOfStates.add("3");
//		setOfStates.add("4");

				
		String[] arrOfStates= (String[])setOfStates.toArray(new String[setOfStates.size()]);
		String[] arrOfTransitions= (String[])setOfTransitions.toArray(new String[setOfTransitions.size()]);
				
		int nodeRelation[][];
		
		nodeRelation=new int[setOfStates.size()][];
		
		
		for(int i=1;i<=arrOfStates.length;i++)  
		{  
			int size=0;		
			for(int j=0;j<arrOfTransitions.length;j++){
				if(arrOfTransitions[j].split("\\|")[0].equals(String.valueOf(i))){
                        size++;	            
				}	  
			}
			nodeRelation[i-1]=new int[size];
		}  
				 	
 

		for(int i=1;i<=nodeRelation.length;i++)  
		{  	
			int k=0;
			for(int j=0;j<arrOfTransitions.length;j++){
				if(arrOfTransitions[j].split("\\|")[0].equals(String.valueOf(i))){					
					nodeRelation[i-1][k]=Integer.parseInt(arrOfTransitions[j].split("\\|")[1]);  				
					k++;
				}	  
			}		
		}  
				
          
        /* 定义节点数组 */  
        Node[] node = new Node[nodeRelation.length];  
          
        for(int i=1;i<=nodeRelation.length;i++)  
        {  
            node[i-1] = new Node();  
            node[i-1].setName("" + i);  
        }  
          
        /* 定义与节点相关�?�的节点集�?� */  
        for(int i=1;i<=nodeRelation.length;i++)  
        {  
            ArrayList<Node> List = new ArrayList<Node>();  
              
            for(int j=0;j<nodeRelation[i-1].length;j++)  
            {  
                List.add(node[nodeRelation[i-1][j]-1]);  
            }  
            node[i-1].setRelationNodes(List);  
            List = null;  //释放内存  
        }  
  
        /* 开始�?�索所有路径 */  
        getPaths(node[s-1], null, node[0], node[t-1]);  
              
    }  
}  