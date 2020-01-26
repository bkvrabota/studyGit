package test_6;

/* Имеется набор вещей, которые необходимо поместить в рюкзак.
Рюкзак обладает заданной грузоподъемностью.
Вещи в свою очередь обладают двумя параметрами — весом и стоимостью.
Цель задачи заполнить рюкзак не превысив его грузоподъемность и
максимизировать суммарную ценность груза. */

import java.util.ArrayList;

public class BackpackManager {

    private static ArrayList<Item> items = new ArrayList<Item>();
    private static final int MAX_WEIGHT = 100;

    public static void main(String[] args) {

        Backpack backpack = new Backpack(MAX_WEIGHT);

        allItems();
        for (Item item : items) {
            backpack.putItem(item);
        }
        backpack.showItems();
    }

    // Набор вещей, которые возможно поместить в рюказак:
    private static void allItems() {
        items.add(new Item("item_1",25, 30));
        items.add(new Item("item_2",100, 90));
        items.add(new Item("item_3",50, 100));
        items.add(new Item("item_4",70, 150));
        items.add(new Item("item_5",40, 80));
    }
}