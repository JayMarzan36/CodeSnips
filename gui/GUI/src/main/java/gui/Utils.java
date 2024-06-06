package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Utils {
    public static void main(String[] args) throws IOException {
        String test = "Testing my write function";
        // writeToFile("Testing my write function");


    }
    public static List<String> findFiles(String folderPath, String[] includeExtensions) {
        List<String> filePaths = new ArrayList<>();
        Stack<File> stack = new Stack<>();
        File root = new File(folderPath);
        if (!root.isDirectory()) {
            System.err.println("Error: The specified path is not a directory.");
            return filePaths;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            File current = stack.pop();
            File[] files = current.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isIncluded(file, includeExtensions)) {
                        filePaths.add(file.getAbsolutePath());
                    } else if (file.isDirectory()) {
                        stack.push(file);
                    }
                }
            }
        }
        return filePaths;
    }
    private static boolean isIncluded(File file, String[] includedExtensions) {
        String fileName = file.getName();
        for (String extensions: includedExtensions) {
            if (fileName.toLowerCase().endsWith(extensions.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public static String[] readFile(String filePath) {
        String line;
        String[] extensions;
        List<String> tempList = new ArrayList<>();
        BufferedReader br =null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException e) {
            System.out.printf("Exception %s", e);
        }
        try {
            while ((line = br.readLine()) != null) {
                tempList.add(line);
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
        extensions = tempList.toArray(new String[0]);
        return extensions;
    }

    public static File[] getFilesInDirectory(File directory, Map<String, String> filesFound) throws IOException {
        if (!directory.exists()) return null; // If the given path doesn't exist, do nothing

        File[] list = directory.listFiles();
        for (File list1 : list) {
            if (list1.isDirectory()) {
                getFilesInDirectory(list1, filesFound); // Recursively call this method to go into subdirectories
            } else {
                String path = list1.getCanonicalPath(); // Get the full canonical path of each file found
                filesFound.put(list1.getName(), path); // Add the filename as key and its full path as value in our map
            }
        }
        return list;
    }

    public static boolean isValidPath(String path) {
        try {
            new File(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean whatIsPath(String path) {
        // if return true, path is folder. Else path is file
        String pathType;
        if (isValidPath(path)) {
            File file = new File(path);
            return file.isDirectory();
        }
        return false;
    }
    public static void writeToFile(List<String> toWrite, String saveFilePath) {
        try (FileWriter writer = new FileWriter(saveFilePath, true)){
            String finalWrite = (toWrite + System.lineSeparator());
            for (String towrite: toWrite) {
                writer.write(towrite + '|');
            }
            writer.write(System.lineSeparator());
        } catch (IOException e) {
        }
    }
}
