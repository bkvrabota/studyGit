package task_3;

public class MonitorProductLine implements ILineStep {

    private String name = "Монитор";

    public MonitorProductLine() {
        System.out.println("Монитор создан.");
    }

    @Override
    public IProductPart buildProductPart() {
        return new PartProduct(name);
    }
}
