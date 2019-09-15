package test_1;

import java.io.*;

/* Создать программу, которая будет сообщать, является ли целое число, введенное пользователем,
чётным или нечётным, простым или составным.
Если пользователь введёт не целое число, то сообщать ему об ошибке. */

public class test_1 {

    public static void main(String[] args) throws IOException {

        System.out.print("Введите целое число: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        if (isInteger(s)) {
            System.out.println("Целое число");
            int n = Integer.parseInt(s);

            if (isEven(n)) System.out.println("Четное число");
                else System.out.println("Нечетное число");

            if (isComposite(n)) System.out.println("Составное число");
                else System.out.println("Простое число");
        } else {
            System.out.println("ОШИБКА! Введите целое число!");
        }
    }

    private static boolean isInteger(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private static boolean isEven(int n) {
        if (n % 2 == 0) {
            return true;
        }
        return false;
    }

    private static boolean isComposite(int n) {
        int i;
        for (i = 2; i < n; i++) {
            if (n % i == 0)
                return true;
            }
        return false;
    }
}
