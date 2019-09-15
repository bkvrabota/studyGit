package test_2;

import java.io.*;

/* Создать программу, которая будет вычислять и выводить на экран НОК (наименьшее общее кратное)
и НОД (наибольший общий делитель) двух целых чисел, введенных пользователем.
Если пользователь некорректно введёт хотя бы одно из чисел, то сообщать об ошибке. */

public class test_2 {

    public static void main(String[] args) throws IOException {

        Integer[] arr = new Integer[2];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("Введите целое число: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();

            if (isInteger(s)) arr[i] = Integer.parseInt(s);
                else {
                    System.out.println("ОШИБКА! Введите целое число!");
                    i--;
            }
        }

        int nod = NOD(arr[0], arr[1]);

        NOK(arr[0], arr[1], nod);

    }

    private static boolean isInteger(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private static int NOD(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    private  static void NOK(int a, int b, int c) {
        int d = a * (b / c);

        System.out.println("Наибольший общий делитель: " + c);
        System.out.println("Наименьшее общее кратное: " + d);
    }
}
