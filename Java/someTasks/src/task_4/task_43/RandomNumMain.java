package task_4.task_43;

public class RandomNumMain {

    public static void main(String[] args) throws InterruptedException {

        DataQueue dataQueue = new DataQueue();

        Producer producer = new Producer(dataQueue);
        Consumer consumer = new Consumer(dataQueue);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();

        Thread.sleep(10000);

        producer.shutdown();
        consumer.shutdown();

        t1.join();
        t2.join();

        System.out.println("Главный поток завершён.");
    }
}
