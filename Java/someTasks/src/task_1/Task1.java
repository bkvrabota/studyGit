package task_1;

/*
программа выводящая на экран случайно сгенерированное трёхзначное натуральное число и его наибольшую цифру
*/

import java.util.Arrays;

public class Task1 {

    private static final int MIN_NUM = 100;
    private static final int MAX_NUM = 999;

    public static void main(String[] args) {
        maxNumber(randomNum());
    }

    // Генерим трехзначное число:
    private static int randomNum() {
        int num;

        num = MIN_NUM + (new java.util.Random()).nextInt(MAX_NUM - MIN_NUM) + 1;

        return num;
    }

    // Ищем наибольшую цифру:
    private static void maxNumber(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);

        System.out.println("В числе " + num + " наибольшей цифрой является " + arr[arr.length - 1]);
    }
}
