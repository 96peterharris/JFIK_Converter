package com.pioterDeveloper;

public class Main {
    public static void main(String[] args) {
        OpenFile input = new OpenFile();
        input.openFIle("test.eps");
        String workString = input.getContent();
        System.out.println(workString);
        ContentFinder finder = new ContentFinder();
        finder.findShapes(workString);
        finder.printShapeBase();
        GenerateSVG svgGenerator = new GenerateSVG();
        svgGenerator.convert(finder.getShapeBase(),"test.svg");
        GenerateCanvas canvasGenerator = new GenerateCanvas();
        canvasGenerator.convert(finder.getShapeBase(),"test.html");
    }
}
