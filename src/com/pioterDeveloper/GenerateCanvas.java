package com.pioterDeveloper;

import java.util.ArrayList;

public class GenerateCanvas {
    String lines;
    String circles;
    String fileBeg;
    String fileEnd;
    String fullFile;

    GenerateCanvas(){
        this.lines = "";
        this.circles = "";
        this.fileBeg = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <canvas id=\"myCanvas\" width=\"2000\" height=\"2000\"></canvas>\n" +
                "        <script>\n" +
                "            var c = document.getElementById(\"myCanvas\");\n" +
                "            var ctx = c.getContext(\"2d\");\n" +
                "            ctx.scale(4, 4); \n\n";
        this.fileEnd = "\n        </script>\n" +
                "</body>\n" +
                "</html>";
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

                tmpStr = "            ctx.moveTo(" + tmp.getStartX()  + "," + tmp.getStartY() + ");\n" +
                        "            ctx.lineTo(" + tmp.getEndX() + "," + tmp.getEndY() + ");\n" +
                        "            ctx.stroke();\n\n";
                lines += tmpStr;

                i++;
            }
            else if(e instanceof Circle){
                tmpC = (Circle) e;
                String tmpStr;

                tmpStr = "\n            ctx.beginPath();\n" +
                        "            ctx.arc(" + tmpC.getStartX() + "," + tmpC.getStartY() + "," + tmpC.getRadius() + ",0,2*Math.PI);\n" +
                        "            ctx.fillStyle = \"white\";\n" +
                        "            ctx.fill();\n" +
                        "            ctx.font = \"3px Arial\";\n" +
                        "            ctx.fillStyle = \"black\";\n" +
                        "            ctx.textAlign = \"center\";\n" +
                        "            ctx.fillText(\"" + j + "\"," + tmpC.getStartX() + "," + (tmpC.getStartY() + 1) + ");\n" +
                        "            ctx.stroke();\n";
                circles += tmpStr;

                j++;
            }
        }
        System.out.println(lines);
        System.out.println(circles);

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
