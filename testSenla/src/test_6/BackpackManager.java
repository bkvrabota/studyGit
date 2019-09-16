package test_6;

/* Имеется набор вещей, которые необходимо поместить в рюкзак.
Рюкзак обладает заданной грузоподъемностью.
Вещи в свою очередь обладают двумя параметрами — весом и стоимостью.
Цель задачи заполнить рюкзак не превысив его грузоподъемность и
максимизировать суммарную ценность груза. */

import java.util.ArrayList;

public class BackpackManager {

    private static ArrayList<Items> items = new ArrayList<Items>();
    private static final int MAX_WEIGHT = 100;

    public static void main(String[] args) {

        Backpack backpack = new Backpack(items, MAX_WEIGHT);

        allItems();
        for (Items item : items) {
            backpack.putItem(item);
        }
        backpack.showItems();
    }

    // Набор вещей, которые возможно поместить в рюказак:
    private static void allItems() {
        items.add(new Items ("item_1",25, 300));
        items.add(new Items ("item_2",100, 90));
        items.add(new Items ("item_3",50, 100));
        items.add(new Items ("item_4",70, 150));
        items.add(new Items ("item_5",40, 80));
    }
}
