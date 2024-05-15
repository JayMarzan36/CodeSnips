
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jaymb
 */
public class Utils {
    public static void main(String[] args) throws IOException {
        String test = "Testing my write function";
        // writeToFile("Testing my write function");


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

    public static String whatIsPath(String path) {
        // if return true, path is folder. Else path is file
        String pathType;
        if (isValidPath(path)) {
            File file = new File(path);
            if (file.isDirectory()) {
                pathType = "Path is folder";
                return pathType;
            } else {
                pathType = "Path is file";
                return pathType;
            }
        } else {
            pathType = "None";
            return pathType;
        }
    }
    public static void writeToFile(List<String> toWrite) {
        try (FileWriter writer = new FileWriter("CodeSnips/src/data/DataBase.txt", true)){
            String finalWrite = (toWrite + System.lineSeparator());
            for (String towrite: toWrite) {
                writer.write(towrite + '|');
            }
            writer.write(System.lineSeparator());
        } catch (IOException e) {
        }
    }
}