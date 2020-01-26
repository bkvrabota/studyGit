package task_4.task_43;

public class Consumer implements Runnable {

    private DataQueue dataQueue;
    private boolean ready = true;

    Consumer(DataQueue queue) {
        this.dataQueue = queue;
    }

    @Override
    public void run() {
        while (ready) {
            try {
                    if (dataQueue.chekQueue() != null) {
                        Thread.sleep(1000);
                        System.err.println("Out " + dataQueue.getQueue());
                    } else {
                        Thread.sleep(10);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(this.getClass().getName());
            }
        }
    }

    public void shutdown() {
        ready = false;
    }
}
