import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner readMachine = new Scanner(System.in);
    int size = readMachine.nextInt();
    int [] array = new int[size];
    int [] control = new int[size];
    for (int i=0; i<size; i++) {
        int current = readMachine.nextInt();
        array[i] = current;
        control[i] = current;
    }
    Arrays.sort(array);
    boolean equal = Arrays.equals(control, array);
    System.out.println(equal);
    }
}