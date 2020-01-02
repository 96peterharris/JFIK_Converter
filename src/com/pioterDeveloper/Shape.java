package com.pioterDeveloper;

public abstract class Shape {
    protected String nameOfShape;
    protected float startX;
    protected float startY;

    Shape() {
        this.nameOfShape = "Shape";
    }

    public Shape(String nameOfShape, float startX, float startY) {
        this.nameOfShape = nameOfShape;
        this.startX = startX;
        this.startY = startY;
    }

    public float getStartX() {
        return this.startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public String getNameOfShape() {
        return this.nameOfShape;
    }

    public void setNameOfShape(String nameOfShape) {
        this.nameOfShape = nameOfShape;
    }

    public abstract void showShape();
}