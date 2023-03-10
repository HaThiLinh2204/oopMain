package com.example.oop.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public static Graph createGraph(){
        Graph g = new Graph();
        return g;
    }

    public void addVertex(int v) {
        Vertex vertex = new Vertex();
        vertex.setId(v);
        vertices.add(vertex);
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }

    public void addEdge(int u,int v,int label) {
            Edge tmp = new Edge();
            Vertex v1 = new Vertex();
            Vertex v2 = new Vertex();
            v1.setId(u);
            v2.setId(v);
            for (Edge i : edges)
                if(i.getFrom().getID() == v1.getID() && i.getTo().getID() == v2.getID())
                    return;
            tmp.setFrom(v1);
            tmp.setTo(v2);
            tmp.setLabel(label);
            edges.add(tmp);
    }

    public void addEdge(Edge e){
        for (Edge edge : edges) {
            if (e.getFrom() == edge.getFrom() && e.getTo() == edge.getTo())
                return;
        }
        if (e.getLabel() == 0)
            return;
        edges.add(e);
    }

    public ArrayList<Vertex> getAdjList(Vertex v){
        ArrayList<Vertex> adjList = new ArrayList<>();
        for (Edge i : edges)
            if (i.getFrom().getID() == v.getID())
                adjList.add(i.getTo());
//        for (Vertex j : adjList)
//            System.out.print(j.getId() + "+");
//        System.out.println(">");
        return adjList;
    }

    public int getLabelEdge(Vertex f, Vertex t){
        Edge tmp = new Edge();
        for (Edge i : edges) {
            if (i.getFrom().getID() == f.getID() && i.getTo().getID() == t.getID())
                tmp = i;
        }
        return tmp.getLabel();
    }


    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    public void removeVertex(Vertex node, AnchorPane main) {
        System.out.println(edges.isEmpty());
        for (int i = 0; i < getEdges().size(); i++) {
            System.out.println(0);
            Edge e = getEdges().get(i);
            if (node.getID() == e.getTo().getID() || node.getID() == e.getFrom().getID()) {
                main.getChildren().removeAll(e, e.node, e.arrow2, e.arrow1);
                System.out.println("delete edge form " + e.getFrom().getID() + " to " + e.getTo().getID());
                edges.remove(e);
            }
        }
        getVertices().remove(node.getID());
        main.getChildren().remove(node);
    }

    public static void highlight(Vertex vertex, boolean isLight) {
        ((Circle) vertex.getChildren().get(0)).setFill(isLight ? Color.ORANGE : Color.AQUA);
    }

    public static void highlight(Edge edge, boolean isLight) {
        if (isLight) {
            edge.setFill(Color.RED);
            edge.setStrokeWidth(4);
        } else {
            edge.setFill(Color.BLUEVIOLET);
            edge.setStrokeWidth(2);
        }
    }
}
