package jay.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class dataParser{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // Input a file or folder or directory
        // Read line by line and keep track
            // Compare current line (string) to see if the keys in a dictionary are in the line
            // If key in line
                // Track current line + factor of line (10)
                // Add the line 'positions' to the dictionary under the key
            // Else do nothing
            // Keep on comparing with lines
        // When done add the keys and values to the main 'database' that has the same keys, but the value has
            // The file
            // File path
            // And line 'positions'
        // Done
        String fileType; // Python, C, Java
        String inputFileoFolder;
        boolean pathType;
        String keyWordPath = null;
        int lineFactor = 2;
        ArrayList<String> keyWords = new ArrayList<>();
        ArrayList<String> current_contents = new ArrayList<>();
        Map<String, String[]> returnedDict = new HashMap<>();
        try (Scanner readInput = new Scanner(System.in)) {
            System.out.print("Please input type of language (Python, Java, C, ...): ");
            fileType = readInput.next();
            System.out.print("Please input a file path or folder path: ");
            inputFileoFolder = readInput.next();
        }
        if (fileType.equals("Python")) keyWordPath = "D:/Coding/CodeSnips/src/data/keyWords/pythonKeyWords.txt";
        // load key words into a dictionary
        keyWords = parseFile(keyWordPath);
        pathType = Utils.whatIsPath(inputFileoFolder);
        if (!pathType) {
            // System.out.println("\nInput is file path\n");
            long start = System.currentTimeMillis();
            current_contents = parseFile(inputFileoFolder);
            returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
            long end = System.currentTimeMillis();
            System.out.printf("Elapsed time: %d milliseconds", (end - start));
            saveData(returnedDict, inputFileoFolder);
        }
        if (pathType) {
            System.out.println("Input is folder path");
            long start = System.currentTimeMillis();
            File directory = new File(inputFileoFolder);
            Map<String, String> foundFiles = new HashMap<>();
            try {
                File[] Files = Utils.getFilesInDirectory(directory, foundFiles);
                for (File file: Files) {
                    String strfile = file.toString();
                    current_contents = parseFile(strfile);
                    returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
                    saveData(returnedDict, strfile);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            


            long end = System.currentTimeMillis();
            System.out.printf("Elapsed time: %d milliseconds", (end - start));

            
        }
    }
    @SuppressWarnings("rawtypes")
    public static ArrayList parseFile(String filePath) {
        
        ArrayList<String> contents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return contents;
    }
    public static Map keywordsInCurrentLine(ArrayList keyWords, ArrayList contents, int lineFactor) {
        String currentKey;
        String lines;
        String[] oldValue;
        String newValue;
        Map<String, String[]> dict = new HashMap<>();
        for (int i =0; i < contents.size(); i++) { // Iterate through contents/lines of current file
            String[] splitContents = contents.get(i).toString().split(" ");
            for (int j =0; j < keyWords.size(); j++) { // Iterate through key words
                currentKey = keyWords.get(j).toString();
                for (String word: splitContents) {
                    if (word.contains(currentKey)){
                        if (dict.containsKey(currentKey)) {
                            lines = "," + (i + 1) + "-" + (i + 1 + lineFactor);
                            oldValue = dict.get(currentKey);
                            newValue = Arrays.toString(oldValue).replace("[", "").replace("]", "") + lines;
                            dict.put(currentKey, new String[]{newValue});
                        } else {
                            lines = (i + 1) + "-" + (i + 1 + lineFactor);
                            dict.put(currentKey, new String[]{lines});
                        }
                    }
                }
            }
        }
        return dict;
    }
    public static void saveData(Map<String, String[]> returnedDict, String filePath) {
        String fileName;
        String[] splitPath = null;
        List<String> contentToSave = new ArrayList<>();
        if (filePath.contains("/")) splitPath = filePath.split("/");
        else splitPath = filePath.split("\\\\");
        fileName = splitPath[splitPath.length - 1];
        contentToSave.add(fileName);
        contentToSave.add(filePath);
        for (String key: returnedDict.keySet()) {
            contentToSave.add(key + Arrays.toString(returnedDict.get(key)));
        }
        Utils.writeToFile(contentToSave);
    }
}