package test_6;

/* Имеется набор вещей, которые необходимо поместить в рюкзак.
Рюкзак обладает заданной грузоподъемностью.
Вещи в свою очередь обладают двумя параметрами — весом и стоимостью.
Цель задачи заполнить рюкзак не превысив его грузоподъемность и
максимизировать суммарную ценность груза. */

import java.util.ArrayList;

public class BackpackManager {

    private static ArrayList<Items[]> items = new ArrayList<Items[]>();
    private static final int MAX_WEIGHT = 100;

    public static void main(String[] args) {
        AllItems();

        Backpack backpack_1 = new Backpack(items, MAX_WEIGHT);

        if (items.size() == 0) {
            System.out.println("Вещи отсутствуют. Добавьте список вещей.");
        } else if (!CheckWeight()) {
            System.out.println("Нет вещей с допустимой массой.");
        } else {
            backpack_1.GetBestSet();
        }

    }

    // Набор вещей, которые возможно поместить в рюказак:
    private static void AllItems() {
        items.add(new Items[] {new Items("item_1",125, 300)});
        items.add(new Items[] {new Items("item_2",100, 90)});
        items.add(new Items[] {new Items("item_3",50, 100)});
        items.add(new Items[] {new Items("item_4",70, 150)});
        items.add(new Items[] {new Items("item_5",40, 80)});
    }

    // Проверка на наличие вещей допустимой массы:
    private static boolean CheckWeight() {
        for (Items[] item : items) {
            if (item[0].getWeight() <= MAX_WEIGHT) {
                return true;
            }
        }
        return false;
    }
}
