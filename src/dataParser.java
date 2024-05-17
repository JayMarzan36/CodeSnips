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
        String fileType;
        String inputPath;
        String pathType;
        String keyWordPath = null;
        int lineFactor = 2;
        ArrayList<String> keyWords;
        ArrayList<String> current_contents = null;
        Map<String, String[]> returnedDict = null;
        try (Scanner readInput = new Scanner(System.in)) {
            System.out.print("Please input type of language (Python, Java, C, ...): ");
            fileType = readInput.next();
            System.out.print("Please input a file path or folder path: ");
            inputPath = readInput.next();
        }
        if (fileType.equals("Python")) keyWordPath = "/data/keyWords/pythonKeyWords.txt";
        keyWords = parseFile(keyWordPath);
        pathType = Utils.whatIsPath(inputPath);
        if (pathType.equals("Path is file")) {
            System.out.println("Input is file path");
            doMainLogic(false, inputPath, lineFactor, keyWords, current_contents, returnedDict);
        } else if (pathType.equals("Path is folder")) {
            System.out.println("Input is folder path");
            doMainLogic(true, inputPath, lineFactor, keyWords, current_contents, returnedDict);
        }
    }

    public static void doMainLogic (boolean folder, String inputPath, int lineFactor, ArrayList<String> keyWords, ArrayList<String> current_contents, Map<String, String[]> returnedDict) throws IOException {
        long start = System.currentTimeMillis();
        if (!folder) {
            current_contents = parseFile(inputPath);
            returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
            saveData(returnedDict, inputPath);
        } else {
            String[] includeExtensions = {".java", ".py", ".cpp"};
            List<String> filesFound = Utils.findFiles(inputPath, includeExtensions);
            for (String filepath: filesFound) {
                current_contents = parseFile(filepath);
                returnedDict = keywordsInCurrentLine(keyWords, current_contents, lineFactor);
                saveData(returnedDict, filepath);
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("Completed in:  %d milliseconds", (end - start));
    }

    public static ArrayList<String> parseFile(String filePath) throws IOException {
        BufferedReader br;
        if (filePath.contains(".txt")) {
            br = new BufferedReader(new InputStreamReader(dataParser.class.getResourceAsStream(filePath)));
        } else {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        }
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
            for (int j =0; j < keyWords.size(); j++) { // Iterate through key words
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

    public static void saveData(Map<String, String[]> returnedDict, String filePath) {
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
        Utils.writeToFile(contentToSave);
    }
}