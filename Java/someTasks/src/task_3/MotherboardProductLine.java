package task_3;

public class MotherboardProductLine implements ILineStep {

    private String name = "Материнская плата";

    public MotherboardProductLine() {
        System.out.println("Материнская плата создана.");
    }

    @Override
    public IProductPart buildProductPart() {
        return new PartProduct(name);
    }
}
