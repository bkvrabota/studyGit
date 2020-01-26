package task_3;

public class AssemblyLine implements IAssemblyLine {

    @Override
    public IProduct assembleProduct(IProduct product) {

        // Создаем необходимые элементы для сборки ноутбука:
        PartProduct body = (PartProduct) new BodyProductLine().buildProductPart();

        PartProduct motherboard = (PartProduct) new MotherboardProductLine().buildProductPart();

        PartProduct monitor = (PartProduct) new MonitorProductLine().buildProductPart();

        // Собираем ноутбук из полученых выше элементов:
        product.installFirstPart(body);
        product.installSecondPart(motherboard);
        product.installThirdPart(monitor);

        System.out.println("Ноутбук создан.");

        return product;
    }
}
