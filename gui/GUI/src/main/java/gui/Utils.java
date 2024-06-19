package gui;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

        try (InputStream is = Utils.class.getResourceAsStream(filePath)) {
            if (is == null) {
                return new String[0];
            }

            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                tempList.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
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
    public static ArrayList<String> parseFile(String filePath) throws IOException {
        ArrayList<String> contents = new ArrayList<>();

        // Check if filePath is a resource path or a full file path
        if (filePath.startsWith("/")) {
            // Resource path (using getResourceAsStream)
            try (InputStream is = Utils.class.getResourceAsStream(filePath)) {
                if (is == null) {
                    throw new FileNotFoundException("Resource file not found: " + filePath);
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        contents.add(line);
                    }
                }
            }
        } else {
            // Full file path (using FileInputStream)
            try (FileInputStream fis = new FileInputStream(filePath);
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)) {

                String line;
                while ((line = br.readLine()) != null) {
                    contents.add(line);
                }
            }
        }
        return contents;
    }
}
