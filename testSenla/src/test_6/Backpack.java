package test_6;

/* Имеется набор вещей, которые необходимо поместить в рюкзак.
Рюкзак обладает заданной грузоподъемностью.
Вещи в свою очередь обладают двумя параметрами — весом и стоимостью.
Цель задачи заполнить рюкзак не превысив его грузоподъемность и
максимизировать суммарную ценность груза. */

import java.util.*;

public class Backpack {

    // Максимальная вместимость рюкзака:
    static final int MAX_WEIGHT = 100;

    // Набор вещей, которые необходимо поместить в рюказак:
    static Items[] items = {
            new Items("item_1",25, 30),
            new Items("item_2",100, 90),
            new Items("item_3",50, 100),
            new Items("item_4",70, 150),
            new Items("item_5",40, 80)
    };

    public static void main(String[] args) {
            // Выводим общую стоимость вещей уложенных в рюкзак:
            System.out.println("Стоимость вещей в рюкзаке: " + fillBackpack(items.length - 1, MAX_WEIGHT));
    }

    private static int fillBackpack(int i, int weigth) {
        if (i < 0) {
            return 0;
        }
        if (items[i].getWeight() > weigth) {
            return fillBackpack(i-1, weigth);
        }
        else {
            //System.out.println(items[i].getName());
            return Math.max(fillBackpack(i-1, weigth),
                    fillBackpack(i-1, weigth - items[i].getWeight()) + items[i].getValue());
        }
    }


}
