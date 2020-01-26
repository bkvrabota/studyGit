package task_4.task_42;

public class ThreadNameMain {

    private static Object object = new Object();
    private static final int LOOP_COUNT = 5;

    public static void main(String[] args) {

        Thread firstThread = new Thread(new NameInfo(LOOP_COUNT, object));
        Thread secondThread = new Thread(new NameInfo(LOOP_COUNT, object));

        firstThread.start();
        secondThread.start();
    }
}
