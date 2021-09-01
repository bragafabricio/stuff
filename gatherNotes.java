// Script to gather some scattered notes in just one file.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



public class Program {
    public static void main(String[] args) {
        File[] notas = retrieveFiles("/Notas");

        for (File nota: notas){
            writeToFile("---- " + nota.getName() + "----------------------------------", "/out.txt", true);
            System.out.println("---- " + nota.getName() + "----------------------------------");
            readFiles(nota.getPath());
            writeToFile("\n", "/out.txt", true);
        }

    }

    private static void readFiles(String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            while (line != null){
                writeToFile(line, "/Notas/out.txt", true);
                System.out.println(line);
                line = br.readLine();
            }
            System.out.println();
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static File[] retrieveFiles(String path){
        File filePath = new File(path);
        File[] files = filePath.listFiles(File::isFile);
        Arrays.sort(files);
        return files;
    }

    private static void printFilesInPath(String path){
        File filePath = new File(path);
        File[] files = filePath.listFiles(File::isFile);
        System.out.println("Files:");
        for (File file:files){
            System.out.println(file);
        }
    }
    private static void writeToFile(String line, String path, boolean writeAfter){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, writeAfter))){
            bw.write(line);
            bw.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}