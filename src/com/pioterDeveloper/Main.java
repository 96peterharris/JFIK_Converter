package com.pioterDeveloper;

public class Main {
    public static void main(String[] args) {
        OpenFile input = new OpenFile();
       // ContentFinder finder =
        GenerateSVG svgGenerator = new GenerateSVG();
        GenerateCanvas canvasGenerator = new GenerateCanvas();

        if(args.length >= 2 && args.length <= 3) {
            if(args[0].equalsIgnoreCase("svg")) {
                if(args.length == 3) {
                    input.openFIle(args[1]);
                    svgGenerator.convert(new ContentFinder().findShapes(input.getContent()).getShapeBase(),args[2]);
                } else {
                    input.openFIle(args[1]);
                    svgGenerator.convert(new ContentFinder().findShapes(input.getContent()).getShapeBase(),args[1].replace(".eps",".svg"));
                }
            } else if(args[0].equalsIgnoreCase("canv")) {
                if(args.length == 3) {
                    input.openFIle(args[1]);
                    canvasGenerator.convert(new ContentFinder().findShapes(input.getContent()).getShapeBase(),args[2]);
                } else {
                    input.openFIle(args[1]);
                    canvasGenerator.convert(new ContentFinder().findShapes(input.getContent()).getShapeBase(),args[1].replace(".eps",".html"));
                }
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
