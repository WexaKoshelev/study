package HomeDZ.Task3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private static final String PKG_DIRECTORY = "C:/Users/79006/IdeaProjects/StartIT/src/HomeDZ/Task3";
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private static final String INPUT_FILE_NAME = "input.txt";
    static String line;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(PKG_DIRECTORY + "/" + OUTPUT_FILE_NAME))) {
            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            try (Writer wr = new FileWriter(PKG_DIRECTORY + "/" + INPUT_FILE_NAME)) {
                for (String line : lines)
                wr.write(line.toUpperCase() + "\n");
            } catch (IOException e) {
                System.out.println("LOG: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}








