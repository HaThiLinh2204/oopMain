package com.example.oop.algorithm;
import com.example.oop.model.Edge;
import com.example.oop.model.Vertex;

import java.util.ArrayList;

import java.util.Stack;

public class DP extends Algorithm{
	private ArrayList<Edge> edges = new ArrayList<>();
	static int[] color = new int[0];
	static boolean[] visited = new boolean[0];
	static Stack<Vertex> topoStack = new Stack<>();
	
	@Override
	public void explore(Vertex vertex) {
		
		
	}
	
	public boolean checkCyclic(Vertex u) {
		
		color[u.getID()] = 1;
		for(Vertex v : super.getData().getAdjList(u)) {
			if(color[v.getID()] == 0) {
				if(checkCyclic(v)) 
					return true;
			}
			else if(color[v.getID()] == 1) return true;//exist (v,u)
		}
		
		color[u.getID()] = 2;
		return false;
		
	}
	
	public boolean isDAG() {
		int colorSize = super.getData().getVertices().size();
		color = new int[colorSize];
		
		for(int i = 0;i < colorSize; i++) {
			color[i] = 0;
		}
		
		for(Vertex v : super.getData().getVertices()) {
				if(checkCyclic(v))
					return false;
		}
		return true;
	}	
	
	public void topoTraverser(Vertex v){
		
		visited[v.getID()] = true;
		for(Vertex u : super.getData().getAdjList(v)) {
			if(!visited[u.getID()])
				topoTraverser(u);
		}
		
		topoStack.push(v);
	}
	
	public void topoSort() {
		
		int visitSize = super.getData().getVertices().size();
		visited = new boolean[visitSize];
		
		for(int i = 0;i < visitSize; i++) {
			visited[i] = false;
		}
		
		for(Vertex v : super.getData().getVertices()) {
			if(!visited[v.getID()])
			topoTraverser(v);
		}
	}

	public Stack<Vertex> getTopoStack() {
		return topoStack;
	}
	
	
	
	
	
	
}
