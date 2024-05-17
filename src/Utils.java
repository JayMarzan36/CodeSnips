
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jaymb
 */
public class Utils {
    public static void main(String[] args) throws IOException {

    }
    public static void readFile(String filePath) {
        String line;
        BufferedReader br = null;
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

    public static boolean isValidPath(String path) {
        try {
            new File(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String whatIsPath(String path) {
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
            for (String towrite: toWrite) {
                writer.write(towrite + '|');
            }
            writer.write(System.lineSeparator());
        } catch (IOException e) {
        }
    }
}