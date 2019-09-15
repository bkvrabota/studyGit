package test_6;

/* Имеется набор вещей, которые необходимо поместить в рюкзак.
Рюкзак обладает заданной грузоподъемностью.
Вещи в свою очередь обладают двумя параметрами — весом и стоимостью.
Цель задачи заполнить рюкзак не превысив его грузоподъемность и
максимизировать суммарную ценность груза. */

import java.util.ArrayList;

public class Backpack {

    private static ArrayList<Items[]> items = new ArrayList<Items[]>();
    private static final int MAX_WEIGHT = 100;

    private static ArrayList<Items[]> bestItems = null;
    private static int bestPrice;

    public static void main(String[] args) {
        AllItems();

        if (items.size() == 0) {
            System.out.println("Вещи отсутствуют. Добавьте список вещей.");
        } else if (!CheckWeight()) {
            System.out.println("Нет вещей с допустимой массой.");
        } else {
            MakeAllSets(items);
            GetBestSet();
        }
    }

    // Набор вещей, которые возможно поместить в рюказак:
    private static void AllItems() {
        items.add(new Items[] {new Items("item_1",125, 30)});
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

    //Общий вес всех вещей:
    private static int CalcWeigth(ArrayList<Items[]> items)
    {
        int sumWeight = 0;

        for (Items[] item : items) {
            sumWeight += item[0].getWeight();
        }
        return sumWeight;
    }

    //Общая стоимость всех вещей:
    private static int CalcPrice(ArrayList<Items[]> items)
    {
        int sumPrice = 0;

        for (Items[] item : items) {
            sumPrice += item[0].getPrice();
        }
        return sumPrice;
    }

    //Является ли текущий набор вещей оптимальным:
    private static void CheckSet(ArrayList<Items[]> items)
    {
        if (bestItems == null) {
            if (CalcWeigth(items) <= MAX_WEIGHT) {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
        else {
            if(CalcWeigth(items) <= MAX_WEIGHT && CalcPrice(items) > bestPrice) {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
    }

    //Перебор вариантов:
    private static void MakeAllSets(ArrayList<Items[]> items) {
        if (items.size() > 0)
            CheckSet(items);
        for (int i = 0; i < items.size(); i++) {
            ArrayList<Items[]> newSet = new ArrayList<Items[]>(items);
            newSet.remove(i);
            MakeAllSets(newSet);
        }
    }

    // Вывод результата:
    private static void GetBestSet() {
        System.out.println("Максимальная вместимость рюкзака: " + MAX_WEIGHT);
        System.out.println("Список вещей уложенных в рюкзак: ");
        for (Items[] bestItem : bestItems) {
            System.out.println("Наименование: " + bestItem[0].getName() +
                    ", Вес предмета: " + bestItem[0].getWeight());
        }
        System.out.println("Суммарная стоимость вещей в рюкзаке: " +  bestPrice);
    }
}
