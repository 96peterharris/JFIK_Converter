package com.pioterDeveloper;

public class Circle extends Shape {
    protected float radius;
    protected String fontType; //added
    protected String fontSize; // added

    public Circle(String nameOfShape, float startX, float startY, String colorHex, int lineWidth, float radius) {
        super("Circle", startX, startY, colorHex, lineWidth);
        this.radius = radius;
    }

    Circle() {
        this.nameOfShape = "Circle";
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void showShape() {
        System.out.println("Centre: X = " + this.startX + " Y = " + this.startY);
        System.out.println("Radius: " + this.radius);
    }
}
