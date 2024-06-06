package gui;

import java.io.*;
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
        File filePath = new File("src/main/java/gui/data/DataBase.txt");
        int lineCount = 1;
        List<String> foundData = new ArrayList<>();
        BufferedReader br =null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException e) {
            System.out.printf("Exception %s", e);
        }
        try {
            while ((line = br.readLine()) != null) {
                String[] contents = line.split("\\|");
                for (int i = 2; i <= contents.length - 1; i++) {
                    String[] seperatedData = contents[i].split("\\W+");
                    for (String data: seperatedData) {
                        if (data.equals(keyWord)) {
                            foundData.add(contents[1]);
                        }
                    }
                }
                lineCount ++;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return foundData;
    }
}
