package task_3;

public class BodyProductLine implements ILineStep {

    private String name = "Корпус";

    public BodyProductLine() {
        System.out.println("Корпус создан.");
    }

    @Override
    public IProductPart buildProductPart() {
        return new PartProduct(name);
    }
}
