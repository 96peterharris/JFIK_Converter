package com.pioterDeveloper;

import java.util.ArrayList;

public class GenerateSVG {
    String lines;
    String circles;
    String fileBeg;
    String fileEnd;
    String fullFile;

    GenerateSVG(){
        this.lines = "";
        this.circles = "";
        this.fileBeg = "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "<svg\n" +
                " viewBox=\"-10 -10 100 150\" \n" +
                " width=\"800\" \n" +
                " height=\"600\"\n" +
                " xmlns=\"http://www.w3.org/2000/svg\" \n" +
                " xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n";
        this.fileEnd = "</svg>";
        this.fullFile = "";
    }

    public void shapesToString(ArrayList<Shape> shapeContener) {
        Line tmp = new Line();
        Circle tmpC = new Circle();
        int i = 1;
        int j = 1;

        for(Shape e : shapeContener){

            if(e instanceof Line){
                tmp = (Line) e;
                String tmpStr;

                tmpStr = "<path id=\"krawedz_" + i + "\" fill=\"none\" stroke=\"#000000\" stroke-width=\"2\" d=\"M " + tmp.getStartX() + " " + tmp.getStartY() + " L " + tmp.getEndX() + " " + tmp.getEndY() + " Z \"/>\n";
                lines += tmpStr;

                i++;
            }
            else if(e instanceof Circle){
                tmpC = (Circle) e;
                String tmpStr;

                tmpStr = "<circle id=\"circle" + j + "\" cx=\"" + tmpC.getStartX() + "\" cy=\"" + tmpC.getStartY() + "\" r=\"" + (tmpC.getRadius() + 6) + "\" style=\"stroke:black; fill:white; stroke-width:1\"/>\n";
                circles += tmpStr;
                tmpStr = "<text x=\"" + (tmpC.getStartX() - 5) + "\" y=\"" + (tmpC.getStartY() + 5) + "\">" + j + "</text>\n";
                circles += tmpStr;

                j++;
            }
        }
       //System.out.println(lines);
       //System.out.println(circles);

    }

    public void allContent(ArrayList<Shape> shapeContener){
        shapesToString(shapeContener);
        fullFile = fileBeg + lines + circles + fileEnd;
        System.out.println(fullFile);
    }

    public void convert(ArrayList<Shape> shapeContener, String fileName){
        allContent(shapeContener);
        SaveToFile.write(fileName, this.fullFile);
    }
}
