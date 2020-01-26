package task_4.task_41;

public class ThreadTest extends Thread {

    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.print("ThreadTest error.");
        }
    }
}
