package task_4.task_41;

public class ThreadWaitTest extends Thread {

    public void run() {
        try {
            Thread.sleep(10);
            synchronized (this) {
                Thread.sleep(10);
                wait();
            }
        } catch (InterruptedException e) {
            System.err.print("ThreadWaitTest error.");
        }
    }
}
