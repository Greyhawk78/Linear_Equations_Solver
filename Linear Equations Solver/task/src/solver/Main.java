package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static double [] [] read(String [] args){
        int size;
        double [] [] matrix = new double[0][];
        List<String> arguments = Arrays.asList(args);
        if (arguments.contains("-in")) {
            File file = new File(arguments.get(arguments.indexOf("-in") + 1));
            try (Scanner scanner = new Scanner(file)) {
                size = scanner.nextInt();
                matrix = new double[size][size + 1];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size + 1; j++) {
                        matrix[i][j] = scanner.nextInt();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error. File not found");
                return null;
            }
            return matrix;
        } else {
            System.out.println("Error. No -in argument");
            return null;
        }
    }



    public static void main(String[] args) {

        System.out.println("Reading matrix");
        read (args);
        System.out.println("Start solving");

    }
}
