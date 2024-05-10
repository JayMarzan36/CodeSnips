
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class dataParser extends Utils{
    public static void main(String[] args) {
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
        String pathType;
        String keyWordPath = null;

        int lineFactor = 10;

        HashMap<String, String> contents = new HashMap<>();
        ArrayList<String> keyWords = new ArrayList<>();

        try (Scanner readInput = new Scanner(System.in)) {
            System.out.print("Please input type of file (Python, Java, C, ...): ");
            fileType = readInput.next();
            System.out.print("Please input a file path or folder path: ");
            inputFileoFolder = readInput.next();
        }


        if (fileType.equals("Python")) keyWordPath = "CodeSnips/src/data/keyWords/pythonKeyWords.txt";


        // load key words into a dictionary

        Utils.readFile(keyWordPath);






        pathType = Utils.whatIsPath(inputFileoFolder);

        if (pathType.equals("Path is file")) {
            System.out.println("Input is file path");
        }

        if (pathType.equals("Path is folder")) {
            System.out.println("Input is folder path");
        }







    }




    @SuppressWarnings("rawtypes")
    public static void parseFile(String filePath, HashMap dictionary, ArrayList keywords) {
        String line;
        BufferedReader br =null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException e) {
            System.out.printf("Exception %s", e);
        }
        try {
            while ((line = br.readLine()) != null) {
                // Stuff here










                // System.out.println(line);
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
    }
}
