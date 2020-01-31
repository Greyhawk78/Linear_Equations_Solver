package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Main {

   public static double [] [] read(String [] args){
        int size;
        String trash;
        double [] [] matrix = new double[0][];
        List<String> arguments = Arrays.asList(args);
        if (arguments.contains("-in")) {
            File file = new File(arguments.get(arguments.indexOf("-in") + 1));
            try (Scanner scanner = new Scanner(file)) {
                size = scanner.nextInt();
                matrix = new double[size][size + 1];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size + 1; j++) {
                        matrix[i][j] = scanner.nextDouble();
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

    public static double [] [] rowsManipulate (double [] [] matrix) {
        for (int k = 1; k < matrix.length; k++) {
            for (int j = k; j < matrix.length; j++) {
                double m = matrix[j][k - 1] / matrix[k - 1][k - 1];
                System.out.println("Divider is " + m);
                System.out.println("New row is");
                for (int i = 0; i < matrix.length + 1; i++) {
                    matrix[j][i] = matrix[j][i] - m * matrix[k - 1][i];
                    System.out.print( matrix [j] [i] + " ");
                }
            }
        }
        return matrix;
    }

    public static double [] answers (double [] [] matrix) {
        double [] answers = new double[matrix.length];
        for (int i=matrix.length-1; i>=0;i--) {
            answers [i] = matrix [i] [matrix.length] / matrix [i] [i];
            for (int c=matrix.length-1; c>i;c--) {
                answers[i] = answers [i] - matrix [i][c]*answers[c]/matrix[i][i];
            }
        }
        return answers;
    }

    public static void output(String [] args, double [] answers){
        List<String> arguments = Arrays.asList(args);
        if (arguments.contains("-out")) {
            if (arguments.indexOf("-out") == arguments.size() - 1) {
                System.out.println("Error. -out should have filename");
            } else {
                File outfile = new File(arguments.get(arguments.indexOf("-out") + 1));
                try (PrintWriter printWriter = new PrintWriter(outfile)) {
                    for (int i=0; i<answers.length; i++) {
                        printWriter.println(answers[i]);
                    }
                    System.out.println("Saved to file " + outfile);
                } catch (FileNotFoundException e) {
                    System.out.println("Error. File not found");
                }
            }
        } else {
            System.out.println("Error. -out not found");
        }
    }

    public static void main(String[] args) {

        System.out.println("Reading matrix");
        double [] [] matrix = read (args);
        System.out.println("Start solving");
        System.out.println("Rows manipulation");
        matrix = rowsManipulate(matrix);
        System.out.println("Processing answers");
        double [] anwers=answers(matrix);
        System.out.println(Arrays.toString(anwers));
        System.out.println("Processing output");
        output(args,anwers);

    }
}
