package com.pioterDeveloper;

public class Line extends Shape{
    protected float endX;
    protected float endY;

    public Line(String nameOfShape, float startX, float startY, String colorHex, int lineWidth, float endX, float endY) {
        super("Line", startX, startY, colorHex, lineWidth);
        this.endY = endY;
        this.endY = endY;
        this.lineWidth = lineWidth;
    }

    Line(){ this.nameOfShape = "Line"; }

    @Override
    public void showShape() {
        System.out.println("Start: X = " + this.startX + " Y = " + this.startY);
        System.out.println("End: X = " + this.endX + " Y = " + this.endY);
    }

    public float getEndX() {
        return this.endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return this.endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

}
