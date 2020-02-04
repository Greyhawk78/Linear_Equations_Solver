package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Main {

   public static double [] [] read(String [] args){
        int size;
        double [] [] matrix = new double[0][];
        List<String> arguments = Arrays.asList(args);
            File file = new File(arguments.get(arguments.indexOf("-in") + 1));
            try (Scanner scanner = new Scanner(file)) {
                int variables = scanner.nextInt();
                int equations = scanner.nextInt();
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
    }

    public static double [] [] rowsManipulate (double [] [] matrix) {
      for (int ser=0; ser<matrix.length; ser++) {
            System.out.println(Arrays.toString(matrix[ser]));
        }
        for (int k = 1; k < matrix.length; k++) {
            sortColumns(matrix,k-1);
            for (int j = k; j < matrix.length; j++) {

                double m = matrix[j][k - 1] / matrix[k - 1][k - 1];
                System.out.println("Divider is " + m);
                System.out.println("Matrix before step ");
                for (int ser=0; ser<matrix.length; ser++) {
                    System.out.println(Arrays.toString(matrix[ser]));
                }
                for (int i = 0; i < matrix.length + 1; i++) {
                    matrix[j][i] = matrix[j][i] - m * matrix[k - 1][i];
                }
                System.out.println("Matrix after step ");
                for (int ser=0; ser<matrix.length; ser++) {
                    System.out.println(Arrays.toString(matrix[ser]));
                }

            }
        }
        return matrix;
    }

    public static double[][] sortColumns(double[][] m, int index) {

                boolean swapped;
                do {
                    swapped=false;
                    for (int row = index; row < m.length - 1; row++) {
                        // Should these be swapped?
                        if (Math.abs(m[row][index]) < Math.abs(m[row + 1][index])) {
                            // Swap this one with the next.
                            double temp = m[row][index];
                            m[row][index] = m[row + 1][index];
                            m[row + 1][index] = temp;
                            // We swapped! Remember to run through again.
                            swapped = true;
                        }
                    }
                } while (swapped);
        return m;
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


    public static void main(String[] args) {

        System.out.println("Reading matrix");
        // double [] [] matrix = read (args);
        double [] [] matrix = {
                {  2, -3, -1,  2 },
                {  4, -4, 0,  4 },
                {  7, -2, -1,  4 },

        };
        System.out.println("Start solving");
        System.out.println("Rows manipulation");
        matrix=rowsManipulate(matrix);
        System.out.println("Processing answers");
        double [] anwers=answers(matrix);
        System.out.println(Arrays.toString(anwers));
        System.out.println("Processing output");
        // output(args,anwers);

    }
}
