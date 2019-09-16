package test_6;

import java.util.ArrayList;

class Backpack {

    private int MAX_WEIGHT;
    private ArrayList<Item> goodItems = new ArrayList<Item>();
    private ArrayList<Item> bestItems = null;
    private int bestPrice;

    Backpack(int MAX_WEIGHT) {
        this.MAX_WEIGHT = MAX_WEIGHT;
    }

    // Проверка на допустимый вес:
    void putItem(Item item) {
        if (item.getWeight() <= MAX_WEIGHT) {
            goodItems.add(item);
        } else System.out.println("Масса предмета <" + item.getName() + "> превышает допустимую.");
    }

    // Показать наполнение рюкзака:
    void showItems() {
        if (goodItems.size() != 0) GetBestSet();
            else System.out.println("Вещи для наполнения рюкзака отсутствуют.");
    }

    //Общий вес всех вещей:
    private int calcWeigth(ArrayList<Item> items)
    {
        int sumWeight = 0;

        for (Item item : items) {
            sumWeight += item.getWeight();
        }
        return sumWeight;
    }

    //Общая стоимость всех вещей:
    private int calcPrice(ArrayList<Item> items)
    {
        int sumPrice = 0;

        for (Item item : items) {
            sumPrice += item.getPrice();
        }
        return sumPrice;
    }

    //Является ли текущий набор вещей оптимальным:
    private void checkSet(ArrayList<Item> items)
    {
        if (bestItems == null) {
            if (calcWeigth(items) <= MAX_WEIGHT) {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        }
        else {
            if(calcWeigth(items) <= MAX_WEIGHT && calcPrice(items) > bestPrice) {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        }
    }

    //Перебор вариантов:
    private void MakeAllSets(ArrayList<Item> items) {
        if (items.size() > 0)
            checkSet(items);
        for (int i = 0; i < items.size(); i++) {
            ArrayList<Item> newSet = new ArrayList<Item>(items);
            newSet.remove(i);
            MakeAllSets(newSet);
        }
    }

    // Получение результата:
    private void GetBestSet() {
        MakeAllSets(goodItems);
        System.out.println("Максимальная вместимость рюкзака: " + MAX_WEIGHT);
        System.out.println("Список вещей уложенных в рюкзак: ");
        for (Item bestItem : bestItems) {
            System.out.println("Наименование: " + bestItem.getName() +
                    ", Вес предмета: " + bestItem.getWeight());
        }
        System.out.println("Суммарная стоимость вещей в рюкзаке: " +  bestPrice);
    }
}
