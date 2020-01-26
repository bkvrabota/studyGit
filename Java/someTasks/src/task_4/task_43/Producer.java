package task_4.task_43;

import java.util.Random;

public class Producer implements Runnable {

    private DataQueue dataQueue;
    private boolean ready = true;

    private final static int MAX_QUEUE_SIZE = 10;

    Producer(DataQueue queue) {
        this.dataQueue = queue;
    }

    @Override
    public void run() {
        while (ready) {
                try {
                        if (dataQueue.getSize() < MAX_QUEUE_SIZE) {
                            int rNum = randomNum();
                            Thread.sleep(500);
                            System.out.println("Put " + rNum);
                            dataQueue.setQueue(rNum);
                        } else {
                            Thread.sleep(10);
                        }
                } catch (Exception e) {
                    System.err.println(this.getClass().getName());
                }
        }
    }

    private int randomNum() {
        int num = new Random().nextInt(1000);
        return num;
    }

    public void shutdown() {
        ready = false;
    }
}
