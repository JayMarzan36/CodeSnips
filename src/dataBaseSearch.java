
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
        List<Integer> dataBaseLines = readDataBase(inputedKeyWord);
        System.out.println(dataBaseLines);

    }

    private static List<Integer> readDataBase(String keyWord) {
        String line;
        String filePath = "CodeSnips/src/data/DataBase.txt";
        int lineCount = 1;
        int displaycount = 0;
        List<Integer> foundData = new ArrayList<>();
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
                            foundData.add(lineCount);
                            System.out.println(displaycount + ": " + contents[0] + " : " + contents[i]);
                            displaycount ++;
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
