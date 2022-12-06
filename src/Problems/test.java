package Problems;

import java.util.InputMismatchException;
import java.util.Scanner;

public class test {
    public static void main (String args[]) {
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        try {
            boolean y = scanner.nextBoolean();
            System.out.println(add(x,y));
        } catch (Exception exception) {
            System.out.println("wrong input");
        }
    }

    public static int add(int x, Boolean y) {
        System.out.println(y);
        return x;
    }
}
