package com.pioterDeveloper;

public class Circle extends Shape {
    protected float radius;

    public Circle(String nameOfShape, float startX, float startY, float radius) {
        super("Circle", startX, startY);
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
