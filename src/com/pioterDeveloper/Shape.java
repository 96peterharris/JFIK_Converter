package com.pioterDeveloper;

public abstract class Shape {
    protected String nameOfShape;
    protected float startX;
    protected float startY;
    protected String colorHex;
    protected int lineWidth;

    Shape() {
        this.nameOfShape = "Shape";
    }

    public Shape(String nameOfShape, float startX, float startY, String colorHex, int lineWidth) {
        this.nameOfShape = nameOfShape;
        this.startX = startX;
        this.startY = startY;
        this.colorHex = colorHex;
        this.lineWidth = lineWidth;
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

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public abstract void showShape();
}