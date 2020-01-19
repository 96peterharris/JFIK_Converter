package com.pioterDeveloper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFinder {
    public ArrayList<String> blockOfLines = new ArrayList<>();
    public ArrayList<String> blockOfCircles = new ArrayList<>();
    public String allCircles;
    public String allLines;
    public String lineWidth = "1";
    public String colorLine = "000000"; //Default is black
    public String colorCircle = "FFFFFF"; //Default is white
    public ArrayList<Shape> shapeBase = new ArrayList();


    public ContentFinder() {
    }

    public void findBlocksOfLines(String workContent){
        String tmp;
        for(Matcher linesBlockMatcher = Pattern.compile("(((([0-1]|(0\\.[0-9]))[\\s])+setrgbcolor\n)(newpath.*lineto.*\\n)+)|newpath.*lineto").matcher(workContent); linesBlockMatcher.find();) {
            tmp = linesBlockMatcher.group();
            blockOfLines.add(tmp);
        }
    }

    public void findBlocksOfCircles(String workContent){
        String tmp;
        for(Matcher circlesBlockMatcher = Pattern.compile("(([0-1]|0\\.[0-9])[\\s]){3}setrgbcolor\\n(newpath[\\s]([0-9]+[\\s]+){5}arc.*\\n)+|([0-9]+[\\s]){5}arc").matcher(workContent); circlesBlockMatcher.find();) {
            tmp = circlesBlockMatcher.group();
            blockOfCircles.add(tmp);
        }
    }

    public void findLineWidth(String workContent){
        Matcher lineWidthMatcher = Pattern.compile("[0-1]+[\\s]setlinewidth").matcher(workContent);
        if(lineWidthMatcher.find()){
            this.lineWidth = lineWidthMatcher.group();
        }
    }

    public void findColorLines(String dataBlock){
        Matcher colorMatcher = Pattern.compile("(([0-1]|(0\\.[0-9]))[\\s])+setrgbcolor").matcher(dataBlock);

        if(colorMatcher.find()) {
            String data = colorMatcher.group().replaceAll(" setrgbcolor", "");
            String[] splitedData = data.split("\\s");
            Float[] floatData = Arrays.stream(splitedData).map(Float::valueOf).toArray(Float[]::new);
            convertColorLines(floatData);
        }
    }

    public void convertColorLines(Float[] colorData){
        ArrayList<Integer> product = new ArrayList<>();
        String hex; //!!!!!!!!!
        this.colorLine = "";

        for(float e : colorData){
            product.add(Math.round(e * 255));
        }

        for(Integer e : product){
            hex = Integer.toHexString(e);

            if(hex.equals("0")) {
                this.colorLine += "00";
            }
            else{
                this.colorLine += hex;
            }
        }
    }

    public void findColorCircles(String dataBlock){
        Matcher colorMatcher = Pattern.compile("(([0-1]|(0\\.[0-9]))[\\s])+setrgbcolor").matcher(dataBlock);

        if(colorMatcher.find()) {
            String data = colorMatcher.group().replaceAll(" setrgbcolor", "");
            String[] splitedData = data.split("\\s");
            Float[] floatData = Arrays.stream(splitedData).map(Float::valueOf).toArray(Float[]::new);
            convertColorCircle(floatData);
        }
    }

    public void convertColorCircle(Float[] colorData){
        ArrayList<Integer> product = new ArrayList<>();
        String hex; //!!!!!!!!!
        this.colorCircle = "";

        for(float e : colorData){
            product.add(Math.round(e * 255));
        }

        for(Integer e : product){
            hex = Integer.toHexString(e);

            if(hex.equals("0")){
                this.colorCircle += "00";
            }
            else {
                this.colorCircle += hex;
            }
        }
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


    public void findCirclesData(String circlesBlocks) {
        //this.findCircles(workContent);
        this.findColorCircles(circlesBlocks);

        Matcher circleData = Pattern.compile("([0-9]+[\\s]){4}").matcher(circlesBlocks);

        while(circleData.find()) {
            Circle temp = new Circle();
            //String data = circleData.group().replaceAll("newpath ", "");
            String data = circleData.group();
            String[] splitedData = data.split("\\s");
            temp.setStartX(Float.parseFloat(splitedData[0]));
            temp.setStartY(Float.parseFloat(splitedData[1]));
            temp.setRadius(Float.parseFloat(splitedData[2]));
            temp.setColorHex(colorCircle);
            temp.setLineWidth(Integer.parseInt(lineWidth));
            this.shapeBase.add(temp);
        }

    }

    public void findLinesData(String linesBlocks) {
       // this.findLines(workContent);
        this.findColorLines(linesBlocks);
        Matcher lineData = Pattern.compile("([0-9]+[ ])+moveto[ ]([0-9]+[ ])+").matcher(linesBlocks);

        while(lineData.find()) {
            Line temp = new Line();
            String data = lineData.group().replaceAll(" moveto", "");
            String[] splitedData = data.split("\\s");
            temp.setStartX(Float.parseFloat(splitedData[0]));
            temp.setStartY(Float.parseFloat(splitedData[1]));
            temp.setEndX(Float.parseFloat(splitedData[2]));
            temp.setEndY(Float.parseFloat(splitedData[3]));
            temp.setLineWidth(Integer.parseInt(lineWidth));
            temp.setColorHex(this.colorLine);
            this.shapeBase.add(temp);
        }
    }

    public void findShapes(String workContent) {
        this.findLineWidth(workContent);
        this.findBlocksOfLines(workContent);
        this.findBlocksOfCircles(workContent);

        for(String e : this.blockOfLines){
            this.findLinesData(e);
        }

        for(String e : this.blockOfCircles){
            this.findCirclesData(e);
        }
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
