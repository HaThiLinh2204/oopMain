package com.example.oop.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import com.example.oop.model.Vertex;
import com.example.oop.step.PseudoStep;
import com.example.oop.step.VertexDetailStep;

public class DFS extends Algorithm{
    static boolean[] visited = new boolean[200];
    
    ArrayList<String> pseudoCodes = new ArrayList<>();
    
    public ArrayList<String> getPseudoCodes() {
        return pseudoCodes;
    }

    @Override
    public void explore(Vertex vertex) {
        visited[vertex.getID()] = true; 
        PseudoStep s1 = new PseudoStep(0);
        pseudoSteps.add(s1);
        s1.addStep(new VertexDetailStep(vertex.getID() +"is the source vertex",false,vertex));
        System.out.print(vertex.getID() + "->");
        
        PseudoStep s2 = new PseudoStep(1);
        pseudoSteps.add(s2);
        s2.addStep(new VertexDetailStep("DFS(" + vertex.getID() +")",true, vertex));
        
        Iterator<Vertex> ite = super.getData().getAdjList(vertex).iterator();
        
        PseudoStep s3 = new PseudoStep(2);
        pseudoSteps.add(s3);

        while (ite.hasNext()){
        	Vertex adj = ite.next();       	
        	s3.addStep(new VertexDetailStep("relax(" +vertex.getID() + "," + adj.getID()
        			+ "," + super.getData().getLabelEdge(vertex, adj) +")",false, vertex));
        	
            if(!visited[adj.getID()]) {
            	explore(adj);
            }
        }
   }
    
    public DFS() {
    	pseudoCodes.add("""    			
    			show warning if the graph is not a tree
    			initSSSP, then DFS(sourceVertex))\n""");
    	pseudoCodes.add("DFS(u)\n");
    	pseudoCodes.add("""  			
    			for each neighbor v of u
    			if !visited[v]
    			relax(u, v, w(u, v)), DFS(v)""");
    }
    
    

	public static boolean[] getVisited() {
		return visited;
	}

}

//    public void explore(Vertex vertex) {
//        visited[vertex.getId()] = true;
//        
//        int getIdVertex = vertex.getId();
//        System.out.print(vertex.getId() + "->");
//
//
//        Iterator<Vertex> ite = super.getData().getAdjList(vertex).iterator();
//        
//        while (ite.hasNext()){
//            Vertex adj = ite.next();
//            
//            int getAdjId = adj.getId();
//            if(!visited[adj.getId()])
//                explore(adj);
//        }
//    }
//}

//pseudocode:
//    visited[vertex] = true;
//    System.out.print(vertex + " ");
//
//    Iterator<Integer> ite = adjLists[vertex].listIterator();
//    while (ite.hasNext()) {
//      int adj = ite.next();
//      if (!visited[adj])
//        DFS(adj);
//	}
