package gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class dataParser{
    public static void main(String[] args) throws IOException {

    }

    public static void doMainLogic (String inputPath, int lineFactor, ArrayList<String> current_contents, Map<String, String[]> returnedDict, String dataBaseFile) throws IOException {
        System.out.println("Starting to parse files");
        String[] includeExtensions = Utils.readFile("src/main/java/gui/data/extensionsToInclude.txt");
        ArrayList<String> keyWords = parseFile("src/main/java/gui/data/keyWords.txt");
        List<String> filesFound = Utils.findFiles(inputPath, includeExtensions);
        for (String filepath: filesFound) {
            System.out.println(STR."Parsing: \{filepath}");
            current_contents = parseFile(filepath);
            returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
            saveData(returnedDict, filepath, dataBaseFile);
        }
        System.out.println("Done parsing files");
    }

    public static ArrayList<String> parseFile(String filePath) throws IOException {
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        ArrayList<String> contents = new ArrayList<>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            throw e;
        }
        return contents;
    }

    public static Map<String, String[]> keywordsInCurrentLine(ArrayList<String> keyWords, ArrayList<String> contents, int lineFactor) {
        String currentKey;
        String lines;
        String[] oldValue;
        String newValue;
        Map<String, String[]> dict = new HashMap<>();
        for (int i =0; i < contents.size(); i++) { // Iterate through contents/lines of current file
            String[] splitContents = contents.get(i).split(" ");
            for (int j =0; j < keyWords.size(); j++) { // Iterate through keywords
                currentKey = keyWords.get(j);
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

    public static void saveData(Map<String, String[]> returnedDict, String filePath, String dataBaseFile) {
        String fileName;
        String[] splitPath;
        List<String> contentToSave = new ArrayList<>();
        if (filePath.contains("/")) splitPath = filePath.split("/");
        else splitPath = filePath.split("\\\\");
        fileName = splitPath[splitPath.length - 1];
        contentToSave.add(fileName);
        contentToSave.add(filePath);
        for (String key: returnedDict.keySet()) {
            contentToSave.add(key + Arrays.toString(returnedDict.get(key)));
        }
        Utils.writeToFile(contentToSave, dataBaseFile);
    }
}