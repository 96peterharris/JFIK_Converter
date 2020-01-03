package com.pioterDeveloper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class SaveToFile {

    public static void write(String fileName, String str){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);

            writer.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
