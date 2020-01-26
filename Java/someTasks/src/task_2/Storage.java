package task_2;

import java.util.ArrayList;

class Storage {

    private double maxWeight;

    private ArrayList<Furniture> furnitures;

    Storage(double maxWeight, ArrayList<Furniture> furnitures) {
        this.maxWeight = maxWeight;
        this.furnitures = furnitures;
    }

    void getSumWeight() {
        double sumWeight = 0;

        for (Furniture item : furnitures) {
            if (sumWeight + item.getWeight() <= maxWeight) {
                sumWeight += item.getWeight();
                System.out.println("Отправляем на склад: " + item.getName() + " (его масса " + item.getWeight() + ")");
            }
        }
        System.out.println("Общая масса вещей на складе: " + sumWeight);
    }
}
