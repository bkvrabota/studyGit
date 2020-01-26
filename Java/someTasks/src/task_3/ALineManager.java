package task_3;

public class ALineManager {

    public static void main(String[] args) {
       // Создаем сборочную линию для ноутбуков:
       AssemblyLine notebookALine = new AssemblyLine();
       // Создаем ноутбук:
       notebookALine.assembleProduct(new Product());
    }
}
