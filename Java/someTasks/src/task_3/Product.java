package task_3;

public class Product implements IProduct {

    public Product() {
        System.out.println("Начинаем создание ноутбука: ");
    }

    @Override
    public void installFirstPart(IProductPart productPart) {
        System.out.println("Прикручиваем корпус.");
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        System.out.println("Прикручиваем материнскую плату.");
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        System.out.println("Прикручиваем монитор.");
    }
}
