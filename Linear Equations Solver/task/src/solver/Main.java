package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Main {

   public static double [] [] read(String [] args){
        int variables;
        int equations;
        double [] [] matrix = new double[0][];
        List<String> arguments = Arrays.asList(args);
        if (arguments.contains("-in")) {
            File file = new File(arguments.get(arguments.indexOf("-in") + 1));
            try (Scanner scanner = new Scanner(file)) {
                variables = scanner.nextInt();
                equations=scanner.nextInt();
                matrix = new double[equations][variables + 1];
                for (int i = 0; i < equations; i++) {
                    for (int j = 0; j < variables + 1; j++) {
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

    public static double [] [] rowsManipulate (double [] [] matrix, int variables, int equations) {
        for (int row = 0; row < equations-1; row++) {
                    columnSort(matrix, row, row);
                    int column =row;
                    for (int current = row; current<equations-1;current++) {
                    double divider = matrix[current + 1][column] / matrix[row][column];
                    System.out.println("Divider is " + divider);
                    System.out.println("Before step");
                    for (int ser = 0; ser < equations; ser++) {
                        System.out.println(Arrays.toString(matrix[ser]));
                    }

                    for (int i=0;i<matrix[current].length; i++) {
                        matrix[current + 1][i] = matrix[current + 1][i] - divider * matrix[row][i];
                    }
                    System.out.println("After step");
                    for (int ser = 0; ser < equations; ser++) {
                        System.out.println(Arrays.toString(matrix[ser]));
                    }
                }
            }
        return matrix;
    }

    public static double [] answers (double [] [] matrix) {
        double [] answers = new double[matrix.length];
        for (int j=0;j<matrix.length; j++) {
            for (int i = 0; i < matrix[0].length; i++) {

            }
        }

        for (int i=matrix.length-1; i>=0;i--) {
            answers [i] = matrix [i] [matrix.length] / matrix [i] [i];
            for (int c=matrix.length-1; c>i;c--) {
                answers[i] = answers [i] - matrix [i][c]*answers[c]/matrix[i][i];
            }
        }
        return answers;
    }

    public static double [] [] columnSort (double [] [] matrix, int row, int column) {
       boolean swapped=false;
       double [] temp;
       do {
           swapped=false;
           for (int i = row; i<matrix.length-1; i++) {
               if (Math.abs(matrix[i][column])<Math.abs(matrix[i+1][column])) {
                   temp=matrix[i+1];
                   matrix[i+1]=matrix[i];
                   matrix[i]=temp;
                   swapped=true;
               }
           }

        } while (swapped);
        return matrix;
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
       // double [] [] matrix = read (args);
        int variables = 3;
        int equations=4;
        double [] [] matrix = {{1,2,3,6}, {5, 1 , 3, 1}, {-3, 5, 7,1}, {1, 3, -7, 4}};
        System.out.println("Start solving");
        System.out.println("Rows manipulation");
        matrix = rowsManipulate(matrix,variables,equations);
        System.out.println("Processing answers");
        double [] anwers=answers(matrix);
        System.out.println(Arrays.toString(anwers));
        System.out.println("Processing output");
        output(args,anwers);

    }
}
