import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class dataBaseSearch {
    public static void main(String[] args) {
        String inputedKeyWord;
        try (Scanner readInput = new Scanner(System.in)) {
            System.out.print("Please input keyword: ");
            inputedKeyWord = readInput.next();
            long start = System.currentTimeMillis();
            Map<String, List<String>> dataBaseLines = readDataBase(inputedKeyWord);
            int count = 0;
            for (Map.Entry<String, List<String>> entry: dataBaseLines.entrySet()) {
                String fileName = entry.getKey();
                System.out.printf("%d : File: %s\n", count, fileName);
                count ++;
            }
            long end = System.currentTimeMillis();
            System.out.printf("Completed search in: %d milliseconds\n", (end - start));
            System.out.print("Select file by name: ");
            String selectedFileName = readInput.next();
            List<String> specificFileInfo = dataBaseLines.get(selectedFileName);
            String fileName = specificFileInfo.get(0);
            String fileData = specificFileInfo.get(1);
            System.out.printf("Selected file: %s \n Selected file data: %s", fileName, fileData);
        }
    }

public static Map<String, List<String>> readDataBase(String keyWord) {
        InputStream dataBaseStream = dataBaseSearch.class.getResourceAsStream("/data/DataBase.txt");
        Map<String, List<String>> foundData = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataBaseStream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contents = line.split("\\|");
                for (int i = 2; i < contents.length; i++) {
                    String[] separatedData = contents[i].split("\\W+");
                    for (String data : separatedData) {
                        if (data.equals(keyWord)) {
                            String fileName = contents[0];
                            String filePath = contents[1];
                            String keywordData = contents[i];
                            List<String> fileInfo = new ArrayList<>();
                            fileInfo.add(filePath);
                            fileInfo.add(keywordData);
                            foundData.put(fileName, fileInfo);
                        }
                    }
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
        return foundData;
    }
}