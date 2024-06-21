package com;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dataParser{
    public static void main(String[] args) throws IOException {

    }

    public static void doMainLogic (String inputPath, int lineFactor, ArrayList<String> current_contents, Map<String, String[]> returnedDict, String dataBaseFile) throws IOException {
        String[] includeExtensions = Utils.readFile("/extensionsToInclude.txt");
        ArrayList<String> keyWords = Utils.parseFile("/keyWords.txt");
        List<String> filesFound = Utils.findFiles(inputPath, includeExtensions);
        for (String filepath: filesFound) {
            System.out.println(filepath);
            current_contents = Utils.parseFile(filepath);
            returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
            saveData(returnedDict, filepath, dataBaseFile);
        }
        System.out.println("Done parsing new folder");
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
        List<String> contentToSave = new ArrayList<>();

        // Extracting file name from filePath
        String fileName;
        if (filePath.contains("/")) {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        } else {
            fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        }

        // Constructing the content line
        StringBuilder contentLine = new StringBuilder();
        contentLine.append(fileName).append("|");
        contentLine.append(filePath).append("|");

        // Adding key-value pairs from returnedDict
        List<String> dictEntries = new ArrayList<>();
        for (String key : returnedDict.keySet()) {
            String value = key + "[" + Arrays.toString(returnedDict.get(key)) + "]";
            dictEntries.add(value);
        }
        contentLine.append(String.join("|", dictEntries));

        // Adding the constructed line to contentToSave
        contentToSave.add(contentLine.toString());

        // Writing contentToSave to dataBaseFile
        Utils.writeToFile(contentToSave, dataBaseFile);
    }
}