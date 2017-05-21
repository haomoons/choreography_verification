package findAllPaths;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.swing.plaf.synth.SynthStyle;


public class FindAllPath {
	
	
	//代表某节点是否在stack中,避免产生回路  
	public Map<Integer,Boolean> states=new HashMap();  
	  
	//存放放入stack中的节点  
	public Stack<Integer> stack=new Stack();  
	
	//打印stack中信息,即路径信息  
	 public void printPath(){  
	    StringBuilder sb=new StringBuilder();  
	    for(Integer i :stack){  
	        sb.append(i+"->");  
	    }  
	    sb.delete(sb.length()-2,sb.length());  
	    System.out.println(sb.toString());  
	}  
	 
	//得到x的邻接点为y的后一个邻接点位置,为-1说明没有找到  
	 public int getNextNode(Graph graph,int x,int y){  
	     int next_node=-1;  
	     Graph.EdgeNode edge=graph.vexsarray[x].firstEdge;  
	     if(null!=edge&&y==-1){  
	         int n=edge.adjvex;  
	         //元素还不在stack中  
	         if(!states.get(n))  
	             return n;  
	         return -1;  
	     }  
	           
	     while(null!=edge){  
	         //节点未访问  
	         if(edge.adjvex==y){  
	             if(null!=edge.nextEdge){  
	             next_node=edge.nextEdge.adjvex;  
	             
	             if(!states.get(next_node))  
	                 return next_node;  
	             }  
	             else  
	                 return -1;  
	         }  
	         edge=edge.nextEdge;  
	     }  
	     return -1;  
	 }
	 
	 
	 
	 public void visit(Graph graph,int x,int y){  
	       //初始化所有节点在stack中的情况  
	        for(int i=0;i<graph.vexsarray.length;i++){  
	        states.put(i,false);  
	    }  
	        //stack top元素  
	        int top_node;  
	    //存放当前top元素已经访问过的邻接点,若不存在则置-1,此时代表访问该top元素的第一个邻接点  
	        int adjvex_node=-1;  
	    int next_node;  
	    stack.add(x);  
	    states.put(x,true);  
	    
	    while(!stack.isEmpty()){  
	        top_node=stack.peek();  
	        //找到需要访问的节点  
	               if(top_node==y){  
	            //打印该路径  
	            printPath();  
	            adjvex_node=stack.pop();  
	            states.put(adjvex_node,false);  
	        }  
	        else{  
	            //访问top_node的第advex_node个邻接点  
	                        next_node=getNextNode(graph,top_node,adjvex_node);  
	            if(next_node!=-1){  
	                stack.push(next_node);  
	                //置当前节点访问状态为已在stack中  
	                                states.put(next_node,true);  
	                //临接点重置  
	                                adjvex_node=-1;  
	            }  
	                       //不存在临接点，将stack top元素退出   
	                        else{  
	                //当前已经访问过了top_node的第adjvex_node邻接点  
	                                adjvex_node=stack.pop();  
	                //不在stack中  
	                states.put(adjvex_node,false);  
	            }  
	        }  
	    }  
	} 
	 
	 public static void main(String[] args) {

//			int[] vexs = {0,1,2,3,4};
//			int[][] edges = {
//					{0,1},
//					{0,3},
//					{1,0},
//					{1,2},
//					{2,1},
//					{2,3},
//					{2,4},
//					{3,0},
//					{3,2},
//					{3,4},
//					{4,2},
//					{4,3},
//					
//			};
			
			int[] vexs = {0,1,2,3};
			int[][] edges = {
					{0,1},
					{0,2},
					{2,1},
					{1,3},
					{2,3},					
			};
			Graph graph = new Graph();
			graph.buildGraph(vexs, edges);
			//graph.printGraph();

			
			FindAllPath findALlPath = new FindAllPath();
			findALlPath.visit(graph, 0, 1);
			
		}
	 
	
}