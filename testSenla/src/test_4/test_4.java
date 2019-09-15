package test_4;

/* Создать программу, которая подсчитывает сколько раз употребляется слово в тексте (без учета регистра).
Текст и слово вводится вручную. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test_4 {

    private static ArrayList<String> list = new ArrayList<String>();
    private static int wordCount = 0;
    private static String text;
    private static String word;

    public static void main(String[] args) throws IOException {
        Input();
        FixText();
        WordFind();
    }

    private static void Input() throws IOException {
        // Ввод текста:
        System.out.print("Введите текст: ");
        BufferedReader readerText = new BufferedReader(new InputStreamReader(System.in));
        text = readerText.readLine();

        // Ввод искомого слова:
        System.out.print("Введите слово: ");
        BufferedReader readerWord = new BufferedReader(new InputStreamReader(System.in));
        word = readerWord.readLine();
    }

    private static void FixText() {
        // Удалить все символы кроме букв и пробелов:
        String wrongSymbols = "1234567890!@#$%^&*()_+!№;%:?*/,.";
        for (char c : wrongSymbols.toCharArray()) {
            text = text.replace(c, ' ');
            word = word.replace(c, ' ');
        }

        // Добавить все слова в список:
        for (String str : text.split(" +")) {
            list.add(str);
        }

        // Все буквы строчные:
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toLowerCase());
        }
        word = word.toLowerCase();
    }

    private static void WordFind() {
        // Поиск искомого слова в тексте:
        for (String s : list) {
            if (word.equals(s)) {
                wordCount++;
            }
        }
        System.out.print("Слово " + "<" + word + ">" + " встречается в тексте " + wordCount + " раз.");
    }
}
