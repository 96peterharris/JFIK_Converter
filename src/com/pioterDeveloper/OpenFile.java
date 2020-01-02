package com.pioterDeveloper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile {
    String reader;
    String content;

    OpenFile() {
    }

    public void openFIle(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));

            while((this.reader = input.readLine()) != null) {
                contentBuilder.append(this.reader + "\n");
            }

            input.close();
        } catch (IOException var4) {
            System.out.println(var4.getMessage());
        }

        this.content = contentBuilder.toString();
    }

    public String getContent() {
        return this.content;
    }
}
