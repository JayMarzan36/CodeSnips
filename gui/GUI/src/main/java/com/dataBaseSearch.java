package com;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dataBaseSearch {
    public static void main(String[] args) {
        String inputedKeyWord;
        try (Scanner readInput = new Scanner(System.in)) {
            System.out.print("Please input keyword: ");
            inputedKeyWord = readInput.next();
        }
        List<String> dataBaseLines = readDataBase(inputedKeyWord);
        System.out.println(dataBaseLines);
    }
    public static List<String> readDataBase(String keyWord) {
        String line;
        // Update the path to point to the external folder
        Path filePath = Paths.get("external_data", "DataBase.txt");
        int lineCount = 1;
        List<String> foundData = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(filePath.toFile());
            br = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException e) {
            System.out.printf("Exception %s%n", e);
        }
        try {
            while ((line = br.readLine()) != null) {
                String[] contents = line.split("\\|");
                for (int i = 2; i < contents.length; i++) {
                    String[] separatedData = contents[i].split("\\W+");
                    for (String data : separatedData) {
                        if (data.equals(keyWord)) {
                            foundData.add(contents[1]);
                        }
                    }
                }
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Create DataBase.txt file");
            }
        }
        return foundData;
    }
}
