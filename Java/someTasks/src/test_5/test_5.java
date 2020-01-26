package test_5;


/* Создать программу, которая в последовательности от 0 до N,
находит все числа-палиндромы (зеркальное значение равно оригинальному).
Длина последовательности N вводится вручную и не должна превышать 100. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test_5 {

    public static void main(String[] args) throws IOException {
        isPalindrom(isInput());
    }

    // Проверка на палиндром:
    private static void isPalindrom(int input) {
        for (int j = 0; j <= input; j++) {
            String str = Integer.toString(j);
            int length = str.length();
            char[] arr = str.toCharArray();
            boolean checkPol = true;

            for (int i = 0; i < length/2; i++) {
                if (arr[i] != arr[length - i - 1]) checkPol = false;
            }
            if (checkPol) System.out.println("Это палиндром: " + j);
        }
    }

    // Ввод числа:
    private static int isInput() throws IOException {
        // Ввод текста:
        System.out.print("Введите длину последовательности (не более 100): ");
        BufferedReader readerText = new BufferedReader(new InputStreamReader(System.in));
        String s = readerText.readLine();

        if (isInteger(s)) {
            int N = Integer.parseInt(s);
                if (N >= 0 && N <= 100) {
                    return N;
                } else System.out.println("ОШИБКА! Введите число не менее 0 и не более 100!");
        } else {
            System.out.println("ОШИБКА! Введите целое число!");
        }
        return -1;
    }

    // Является ли строка целым числом:
    private static boolean isInteger(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
