package task_4.task_41;

public class AllStateMain {

    public static void main(String[] args) {

        try{

            // NEW – поток создан, но ещё не запущен
            Thread threadOne = new ThreadTest();
            Thread threadTwo = new ThreadWaitTest();
            threadTwo.setDaemon(true);
            System.out.println("1: " + threadOne.getState());
            Thread.sleep(50);

            // RUNNABLE – поток выполняется
            threadOne.start();
            threadTwo.start();
            System.out.println("2: " + threadOne.getState());

            synchronized (threadTwo) {

                // BLOCKED – поток блокирован
                Thread.sleep(50);
                System.out.println("3: " + threadTwo.getState());
            }

            // WAITING –  поток ждет окончания работы другого потока
            Thread.sleep(15);
            System.out.println("4: " + threadTwo.getState());

            // TIMED_WAITING - поток некоторое время ждет окончания другого потока
            Thread.sleep(10);
            System.out.println("5: " + threadOne.getState());

            // TERMINATED – поток завершен
            Thread.sleep(10);
            threadOne.join();
            System.out.println("6: " + threadOne.getState());

        } catch (InterruptedException e) {
            System.err.print("Ошибка потока");
        }
    }
}
