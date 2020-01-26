package task_2;

/*
Программа содержащая иерархии товаров для склада. Заполнить склад до предела и высчитать вес хранимого товара.
 */

import java.util.ArrayList;

public class StorageManager {

    private static final double MAX_WEIGHT = 1000;
    private static ArrayList<Furniture> furnitures = new ArrayList<>();

    public static void main(String[] args) {

        allFurnitures();

        Storage storage_1 = new Storage(MAX_WEIGHT, furnitures);

        storage_1.getSumWeight();
    }

    private static void allFurnitures() {
        furnitures.add(new Table("table_1", 100.55));
        furnitures.add(new Bed("bed_1", 900));
        furnitures.add(new Chair("chair_1", 50));
        furnitures.add(new Table("table_2", 200));
        furnitures.add(new Bed("bed_2", 350));
        furnitures.add(new Chair("chair_2", 270));
    }
}