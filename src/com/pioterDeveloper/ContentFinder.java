package com.pioterDeveloper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFinder {
    public String allCircles;
    public String allLines;
    public ArrayList<Shape> shapeBase = new ArrayList();

    public ContentFinder() {
    }

    public void findCircles(String workContent) {
        String tmp;

        for(Matcher circleMatcher = Pattern.compile("([0-9]+[\\s]){5}arc").matcher(workContent); circleMatcher.find(); this.allCircles = this.allCircles + "\n" + tmp) {
            tmp = circleMatcher.group();
        }
    }

    public void findLines(String workContent) {
        String tmp;
        for(Matcher lineMatcher = Pattern.compile("newpath.*lineto").matcher(workContent); lineMatcher.find(); this.allLines = this.allLines + "\n" + tmp) {
            tmp = lineMatcher.group();
        }

    }


    public void findCirclesData(String workContent) {
        this.findCircles(workContent);


        Matcher circleData = Pattern.compile("([0-9]+[\\s]){2}[0-9]+").matcher(this.allCircles);

        while(circleData.find()) {
            Circle temp = new Circle();
            //String data = circleData.group().replaceAll("newpath ", "");
            String data = circleData.group();
            String[] splitedData = data.split("\\s");
            temp.setStartX(Float.parseFloat(splitedData[0]));
            temp.setStartY(Float.parseFloat(splitedData[1]));
            temp.setRadius(Float.parseFloat(splitedData[2]));
            this.shapeBase.add(temp);
        }

    }

    public void findLinesData(String workContent) {
        this.findLines(workContent);
        Matcher lineData = Pattern.compile("([0-9]+[ ])+moveto[ ]([0-9]+[ ])+").matcher(this.allLines);

        while(lineData.find()) {
            Line temp = new Line();
            String data = lineData.group().replaceAll(" moveto", "");
            String[] splitedData = data.split("\\s");
            temp.setStartX(Float.parseFloat(splitedData[0]));
            temp.setStartY(Float.parseFloat(splitedData[1]));
            temp.setEndX(Float.parseFloat(splitedData[2]));
            temp.setEndY(Float.parseFloat(splitedData[3]));
            this.shapeBase.add(temp);
        }
    }

    public void findShapes(String workContent) {
        this.findCirclesData(workContent);
        this.findLinesData(workContent);
    }

    public void printShapeBase() {
        int i = 1;

        for(Iterator var2 = this.shapeBase.iterator(); var2.hasNext(); ++i) {
            Shape e = (Shape)var2.next();
            System.out.println(i + "." + e.getNameOfShape());
            e.showShape();
            System.out.println();
        }
    }

    public ArrayList<Shape> getShapeBase() {
        return shapeBase;
    }
}
