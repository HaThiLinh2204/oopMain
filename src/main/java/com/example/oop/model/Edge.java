package com.example.oop.model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
	private Vertex from;
    private Vertex to;
    private int label;

    Line arrow1 = new Line();
    Line arrow2 = new Line();

    Label node = new Label(label + "");

    public Label getNode() {
        return node;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
        
    }

    public void setLength(int length, Pane container) {
        this.label = length;
        node.setText(" " + length + " ");
        node.setLayoutX((getStartX() + getEndX()) / 2 - 20);
        node.setLayoutY((getStartY() + getEndY()) / 2 - 20);
        node.setViewOrder(98);
        drawArrow(container);
        container.getChildren().add(node);
    }

    private void drawArrow(Pane c){
        double lineAngle = Math.atan((getStartY() - getEndY()) / (getStartX() -getEndX()));
        double arrowAngle = getStartX() > getEndX() ? Math.toRadians(30) : -Math.toRadians(210);
        double i = getStartX() > getEndX() ? -1 : 1;
        int length = (int) Math.sqrt(Math.pow(getStartX() - getEndX(), 2) + Math.pow(getStartY() - getEndY(), 2));

        arrow1.setStartX(getEndX() - 22 * Math.cos(lineAngle) * i);
        arrow1.setStartY(getEndY() - 22 * Math.sin(lineAngle) * i);
        arrow1.setEndX(getEndX() - 22 * Math.cos(lineAngle) * i + 15 * Math.cos(lineAngle - arrowAngle));
        arrow1.setEndY(getEndY() - 22 * Math.sin(lineAngle) * i + 15 * Math.sin(lineAngle - arrowAngle));

        arrow2.setStartX(getEndX() - 22 * Math.cos(lineAngle) * i);
        arrow2.setStartY(getEndY() - 22 * Math.sin(lineAngle) * i);
        arrow2.setEndX(getEndX() - 22 * Math.cos(lineAngle) * i + 15 * Math.cos(lineAngle + arrowAngle));
        arrow2.setEndY(getEndY() - 22 * Math.sin(lineAngle) * i + 15 * Math.sin(lineAngle + arrowAngle));

        c.getChildren().addAll(arrow1, arrow2);
    }


    public boolean isWeighted(){
        if (getLabel() != 0)
            return true;
        return false;
    }

    public boolean isDireted(){
        if(getFrom() != null && getTo() != null)
            return true;
        return false;
    }

    public Edge() {
        setVisible(false);
        setFill(Color.BLUEVIOLET);
        setViewOrder(99);
        setStrokeWidth(2);
        arrow1.setStrokeWidth(2);
        arrow2.setStrokeWidth(2);
        arrow1.setViewOrder(99);
        arrow2.setViewOrder(99);


        node.setStyle("-fx-background-radius: 15px; -fx-background-color: #FEF0EF;" +
                "-fx-pref-height: 40px;-fx-pref-width: 40px;-fx-alignment: center");
    }

}