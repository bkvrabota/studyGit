package test_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/* Создать программу, которая будет:
-	подсчитывать количество слов в предложении
-	выводить их в отсортированном виде
-	делать первую букву каждого слова заглавной.
Предложение вводится вручную. (Разделитель пробел (“ ”)). */

public class test_3 {

    public static void main(String[] args) throws IOException {

        ArrayList<String> list = new ArrayList<String>();

        // Ввод текста:
        System.out.print("Введите текст: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        // Удалить все символы кроме букв и пробелов:
        String wrongSymbols = "1234567890!@#$%^&*()_+!№;%:?*/,.";
        for (char c : wrongSymbols.toCharArray()) {
            text = text.replace(c, ' ');
        }

        // Добавить все слова в список и вывести их количество:
        for (String word : text.split(" +")) {
            list.add(word);
        }
        System.out.println("Количество слов в предложении: " + list.size());

        // Заменить первую букву каждого слова на заглавную:
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).substring(0,1).toUpperCase() + list.get(i).substring(1));
        }

        // Отсортировать и вывести все слова в алфавитном порядке:
        list.sort(Comparator.naturalOrder());
        System.out.println("Слова в алфавитном порядке: ");
        list.forEach(System.out::println);
    }
}
