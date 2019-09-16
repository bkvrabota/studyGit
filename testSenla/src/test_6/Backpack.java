package test_6;

import java.util.ArrayList;

public class Backpack {

    private int MAX_WEIGHT;
    private ArrayList<Items[]> items;

    private ArrayList<Items[]> bestItems = null;
    private int bestPrice;

    public Backpack(ArrayList<Items[]> items, int MAX_WEIGHT) {
        this.items = items;
        this.MAX_WEIGHT = MAX_WEIGHT;
    }

    //Общий вес всех вещей:
    private int CalcWeigth(ArrayList<Items[]> items)
    {
        int sumWeight = 0;

        for (Items[] item : items) {
            sumWeight += item[0].getWeight();
        }
        return sumWeight;
    }

    //Общая стоимость всех вещей:
    private int CalcPrice(ArrayList<Items[]> items)
    {
        int sumPrice = 0;

        for (Items[] item : items) {
            sumPrice += item[0].getPrice();
        }
        return sumPrice;
    }

    //Является ли текущий набор вещей оптимальным:
    private void CheckSet(ArrayList<Items[]> items)
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
    private void MakeAllSets(ArrayList<Items[]> items) {
        if (items.size() > 0)
            CheckSet(items);
        for (int i = 0; i < items.size(); i++) {
            ArrayList<Items[]> newSet = new ArrayList<Items[]>(items);
            newSet.remove(i);
            MakeAllSets(newSet);
        }
    }

    // Вывод результата:
    public void GetBestSet() {
        MakeAllSets(items);
        System.out.println("Максимальная вместимость рюкзака: " + MAX_WEIGHT);
        System.out.println("Список вещей уложенных в рюкзак: ");
        for (Items[] bestItem : bestItems) {
            System.out.println("Наименование: " + bestItem[0].getName() +
                    ", Вес предмета: " + bestItem[0].getWeight());
        }
        System.out.println("Суммарная стоимость вещей в рюкзаке: " +  bestPrice);
    }
}
