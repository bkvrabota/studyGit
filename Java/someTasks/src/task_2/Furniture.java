package task_2;

public abstract class Furniture {
    private final String name;
    private final double weight;

    protected Furniture(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
