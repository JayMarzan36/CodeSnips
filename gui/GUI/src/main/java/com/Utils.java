package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.println(extensions);
            if (fileName.toLowerCase().endsWith(extensions.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public static String[] readFile(String filePath) {
        List<String> tempList = new ArrayList<>();
        BufferedReader br = null;

        try {
            if (filePath.startsWith("/")) {
                // External data path (using FileInputStream)
                Path externalFilePath = Paths.get("external_data", filePath.substring(1)); // Remove the leading "/"
                try (FileInputStream fis = new FileInputStream(externalFilePath.toFile());
                     InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                     BufferedReader extBr = new BufferedReader(isr)) {

                    String line;
                    while ((line = extBr.readLine()) != null) {
                        tempList.add(line);
                        System.out.println(line); // For debugging purposes
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("File not found in external data folder: " + externalFilePath);
                    // Return empty array if file not found
                    return new String[0];
                }
            } else {
                // Internal resource path (using getResourceAsStream)
                try (InputStream is = Utils.class.getResourceAsStream(filePath);
                     InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                     BufferedReader intBr = new BufferedReader(isr)) {

                    if (is == null) {
                        System.err.println("Error: File not found - " + filePath);
                        // Return empty array if file not found
                        return new String[0];
                    }

                    String line;
                    while ((line = intBr.readLine()) != null) {
                        tempList.add(line);
                        System.out.println(line); // For debugging purposes
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    e.printStackTrace();
                    // Return empty array on IO error
                    return new String[0];
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
            e.printStackTrace();
            // Return empty array on IO error
            return new String[0];
        }

        return tempList.toArray(new String[0]);
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
        // Adjusting saveFilePath to point to external_data folder
        Path databaseFilePath = Paths.get("external_data", "DataBase.txt");
//        Path databaseFilePath = externalFolderPath.resolve(saveFilePath);

        try (FileWriter writer = new FileWriter(databaseFilePath.toString(), true)) {
            for (String line : toWrite) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static ArrayList<String> parseFile(String filePath) throws FileNotFoundException {
        ArrayList<String> contents = new ArrayList<>();

        // Check if filePath is an external data path or a resource path
        if (!filePath.startsWith("/")) {
            // External data path (using FileInputStream)
            try {
                FileInputStream fis = new FileInputStream(filePath);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        contents.add(line);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    e.printStackTrace();
                    // Return empty contents on IO error
                    return contents;
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + filePath);
                // Return empty contents if file not found
                return contents;
            }
        } else {
            // Resource path (using getResourceAsStream)
            Path externalFilePath = Paths.get("external_data", filePath.substring(1)); // Remove the leading "/"
            try (FileInputStream fis = new FileInputStream(externalFilePath.toFile());
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)) {

                String line;
                while ((line = br.readLine()) != null) {
                    contents.add(line);
                }
            } catch (IOException e) {
                throw new FileNotFoundException("File not found in external data folder: " + externalFilePath);
            }
        }
        return contents;
    }
}
