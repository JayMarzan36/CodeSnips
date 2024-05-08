
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        System.out.print("Input a directory: ");

        String inputDirectory;
        String userContinue;
        String inputFile;
        String readFilePath;
        Scanner readInput = new Scanner(System.in);
            

        inputDirectory = readInput.next();
        System.out.println("User Inputed: " + inputDirectory);

        System.out.print("Confirm (y,n): ");
        userContinue = readInput.next();
        if ("n".equals(userContinue)) {
            System.out.print("Corrected directory: ");
            inputDirectory = readInput.next();
        }

        HashMap<String, String> fileMap = new HashMap<>();
        getFilesInDirectory(new File(inputDirectory), fileMap);

        int count = 0;
        for (Map.Entry<String, String> entry : fileMap.entrySet()) {
            System.out.println(count + " : Filename: " + entry.getKey() + ", Path: " + entry.getValue());
            count ++;
        }

        System.out.print("File to read: ");
        inputFile = readInput.next();

        
        



        if (fileMap.containsKey(inputFile)) {
            String realPath = fileMap.get(inputFile);
            readFile(realPath);
        } else {
            System.out.println("File not exist in dictionary");
        }


    }
    public static void readFile(String filePath) {
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
                System.out.println(line);
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

    private static void getFilesInDirectory(File directory, Map<String, String> filesFound) throws IOException {
        if (!directory.exists()) return; // If the given path doesn't exist, do nothing
        
        File[] list = directory.listFiles();
        for (File list1 : list) {
            if (list1.isDirectory()) {
                getFilesInDirectory(list1, filesFound); // Recursively call this method to go into subdirectories
            } else {
                String path = list1.getCanonicalPath(); // Get the full canonical path of each file found
                filesFound.put(list1.getName(), path); // Add the filename as key and its full path as value in our map
            }
        }
    }
}