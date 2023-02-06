package com.example.oop.algorithm;

import com.example.oop.model.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra extends Algorithm{
	static ArrayList<String> steps = new ArrayList<>();
    Queue<Vertex> vertexQueue = new LinkedList<>();
//    final int cnst = super.getData().getVertices().size();
    private String str = new String();
    private StringBuffer c = new StringBuffer();
    private int min = Integer.MAX_VALUE;
    int w;

    Vertex temp = new Vertex();
    ArrayList<Vertex> list = new ArrayList<>();
    @Override
    public void explore(Vertex vertex) {
        int[] dist = new int[super.getData().getVertices().size()];
        int[] previous = new int[super.getData().getVertices().size()];
        boolean[] visited = new boolean[super.getData().getVertices().size()];

//        System.out.println(super.getData().getVertices().size());
        for (int k = 0; k < super.getData().getVertices().size(); k++) {
            dist[k] = Integer.MAX_VALUE;
            String distK = "disk[" + k +"] = MAX_VAlUE(Interger)";
            steps.add(distK);
            previous[k] = -1;
            String preK = "previous[" + k +"] = -1";
            steps.add(preK);
        }
        String distVer = "dist[" + vertex.getID() +"] = 0";
        steps.add(distVer);
        dist[vertex.getID()] = 0;
        
        String visitVer = "visted[" + vertex.getID() +"] = true";
        steps.add(visitVer);
        visited[vertex.getID()] = true;
        
        String vertexQueueAdd = "vertexQueue.add(" + vertex.getID() +")";
        steps.add(vertexQueueAdd);

        vertexQueue.add(vertex);
        
        String whileVertexQueue = "while(!vertexQueue.isEmpty())";
        steps.add(whileVertexQueue);
        while (!vertexQueue.isEmpty()){
            min = Integer.MAX_VALUE;
            for (Vertex i : vertexQueue) {
            	String CompMin = "if(min > dist[" + i.getID() +"]";
            	steps.add(CompMin);
                if (min > dist[i.getID()]){
                	
                	String setMin = "min = dist[" + i.getID() +"]";
                	steps.add(setMin);
                    min = dist[i.getID()];
                    
                    String setTemp = "temp = " + i.getID();
                    steps.add(setTemp);
                    temp = i;
                }
            }
            String vertexQueueRemove = "vertexQueue.remove(" + temp.getID() + ")";
            steps.add(vertexQueueRemove);
            vertexQueue.remove(temp);
            
            String visitTemp = "visited[" + temp.getID() +"] = true";
            steps.add(visitTemp);
            visited[temp.getID()] = true;
            
            list = super.getData().getAdjList(temp);
            for (Vertex j : list) {
            	String ifVisitJ = "if(!visited[" + j.getID() +"])";
            	steps.add(ifVisitJ);
            	
                if (!visited[j.getID()]) {
                    w = super.getData().getLabelEdge(temp, j);
                    String compDistJTemp = "if(dist[" + j.getID() + "] > " + "dist[" + temp.getID() +"]" + w;
                    steps.add(compDistJTemp);
                    
                    if (dist[j.getID()] > dist[temp.getID()] + w) {
                    	String setDistJ = "dist[" + j.getID() + "] = " + "dist[" + temp.getID() + "" + w;
                    	steps.add(setDistJ);
                    	dist[j.getID()] = dist[temp.getID()] + w;
                    	
                        String setPrevious = "previous[" + j.getID() +"] = " + temp.getID();
                        steps.add(setPrevious);
                        previous[j.getID()] = temp.getID();
                    }
                    String vertexQueueAddJ = "vertexQueue.add(" + j.getID() + ")";
                    steps.add(vertexQueueAddJ);
                    vertexQueue.add(j);
                }
            }

        }

        for (int k = 0; k < super.getData().getVertices().size(); k++){
            str = "";
            System.out.print(k + ":");
            String compPre = "if(previous[" + k +"] == -1";
            steps.add(compPre);
            
            if(previous[k] == -1){
                System.out.println("No path");
                String conti = "continue";
                steps.add(conti);
                continue;
            }
            w = k;
            String whilePre = "while(previous[" + k + "] != -1";
            steps.add(whilePre);
            while (previous[w] != -1){
                str = str + String.valueOf(previous[w]);
                String setW = w + " = " + "previos[" + w +"]";
                steps.add(setW);
                w = previous[w];
                
                String ifPreW = "if(previous[" + w +"] != -1";
                steps.add(ifPreW);
                if (previous[w] != -1)
                {
                	String setStr = "str = str + >";
                	steps.add(setStr);
                    str = str + ">";
                }
            }
            String StrAppend = "c.append(str)"; 
            steps.add(StrAppend);
            c.append(str);
            
            System.out.println(c.reverse() + ">" + k);
            
            c.delete(0, c.length());
        }

    }
	public ArrayList<String> getSteps() {
		return steps;
	}
    
    
//    public void explore(Vertex vertex) {
//        int[] dist = new int[super.getData().getVertices().size()];
//        int[] previous = new int[super.getData().getVertices().size()];
//        boolean[] visited = new boolean[super.getData().getVertices().size()];
//
////        System.out.println(super.getData().getVertices().size());
//        for (int k = 0; k < super.getData().getVertices().size(); k++) {
//            dist[k] = Integer.MAX_VALUE;
//            previous[k] = -1;
//        }
//        dist[vertex.getId()] = 0;
//        visited[vertex.getId()] = true;
//
//        vertexQueue.add(vertex);
//
//        while (!vertexQueue.isEmpty()){
//            min = Integer.MAX_VALUE;
//            for (Vertex i : vertexQueue) {
//                if (min > dist[i.getId()]){
//                    min = dist[i.getId()];
//                    temp = i;
//                }
//            }
//            vertexQueue.remove(temp);
//            visited[temp.getId()] = true;
//            list = super.getData().getAdjList(temp);
//            for (Vertex j : list) {
//                if (!visited[j.getId()]) {
//                    w = super.getData().getLabelEdge(temp, j);
//                    if (dist[j.getId()] > dist[temp.getId()] + w) {
//                        dist[j.getId()] = dist[temp.getId()] + w;
//                        previous[j.getId()] = temp.getId();
//                    }
//                    vertexQueue.add(j);
//                }
//            }
////            System.out.println(temp.getId());
//        }
//
//        for (int k = 0; k < super.getData().getVertices().size(); k++){
//            str = "";
//            System.out.print(k + ":");
//            if(previous[k] == -1){
//                System.out.println("No path");
//                continue;
//            }
//            w = k;
//            while (previous[w] != -1){
//                str = str + String.valueOf(previous[w]);
//                w = previous[w];
//                if (previous[w] != -1)
//                    str = str + ">";
//            }
////            System.out.println(str);
//            c.append(str);
//            System.out.println(c.reverse() + ">" + k);
//            c.delete(0, c.length());
//        }
//
//    }
}
