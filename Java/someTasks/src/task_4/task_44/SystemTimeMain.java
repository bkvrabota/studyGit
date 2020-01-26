package task_4.task_44;

public class SystemTimeMain {

    private static final long TIME_INTERVAL = 5000;
    private static final long MAIN_TIME = 25000;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new TimeInfo(TIME_INTERVAL));

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(MAIN_TIME);
    }
}
