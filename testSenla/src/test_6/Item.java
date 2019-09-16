package test_6;

public class Item {

    private String name;
    private int weight;
    private int price;

    public Item(String name, int weight, int price){
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
