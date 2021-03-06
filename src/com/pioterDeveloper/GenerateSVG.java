package com.pioterDeveloper;

import java.util.ArrayList;

public class GenerateSVG {
    protected String lines;
    protected String circles;
    protected String fileBeg;
    protected String fileEnd;
    protected String fullFile;
    protected int numberOfLines = 0;
    protected int numberOfCircles = 0;

    GenerateSVG(){
        this.lines = "";
        this.circles = "";
        this.fileBeg = "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "<svg\n" +
                " viewBox=\"0 0 300 280\" \n" +
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

        countShapes(shapeContener);

        for(Shape e : shapeContener){

            if(e instanceof Line){
                tmp = (Line) e;
                String tmpStr;

                tmpStr = "<path id=\"krawedz_" + i + "\" fill=\"none\" stroke=\"#" + tmp.getColorHex() + "\" stroke-width=\"" + tmp.getLineWidth() + "\" d=\"M " + tmp.getStartX() + " " + tmp.getStartY() + " L " + tmp.getEndX() + " " + tmp.getEndY() + " Z \"/>\n";
                lines += tmpStr;

                i++;
            }
            else if(e instanceof Circle){
                tmpC = (Circle) e;
                String tmpStr;

                tmpStr = "<circle id=\"circle" + j + "\" cx=\"" + tmpC.getStartX() + "\" cy=\"" + tmpC.getStartY() + "\" r=\"" + tmpC.getRadius() + "\" style=\"stroke:black; fill:#" + tmpC.getColorHex() + "; stroke-width:" + tmpC.getLineWidth() + "\"/>\n";
                circles += tmpStr;
                tmpStr = "<text x=\"" + tmpC.getStartX() + "\" y=\"" + (tmpC.getStartY()+1) + "\"  style=\"text-anchor: middle; font-size: " + tmpC.getRadius() +"px;\">" + j + "</text>\n";
                circles += tmpStr;

                j++;
            }
        }

    }

    public void allContent(ArrayList<Shape> shapeContener){
        shapesToString(shapeContener);
        fullFile = fileBeg + lines + circles + fileEnd;
       // System.out.println(fullFile);
    }

    public void convert(ArrayList<Shape> shapeContener, String fileName){
        allContent(shapeContener);
        SaveToFile.write(fileName, this.fullFile);
    }

    private void countShapes(ArrayList<Shape> shapeContener){
        for(Shape e : shapeContener){
            if(e instanceof Circle){
                numberOfCircles++;
            }
            if(e instanceof Line){
                numberOfLines++;
            }
        }
    }
}
