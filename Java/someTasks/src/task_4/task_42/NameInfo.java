package task_4.task_42;

public class NameInfo implements Runnable {

    private int loopCount;
    private Object object;

    NameInfo(int loopCount, Object object) {
        this.loopCount = loopCount;
        this.object = object;
    }

    @Override
    public void run() {
        for(int i = 0; i < loopCount; i++)
        {
            synchronized(object)
            {
                System.out.println(Thread.currentThread().getName());
                try
                {
                    Thread.sleep(1000);
                    object.notify();
                    if(i == (loopCount - 1))
                    {
                        break;
                    }
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.err.println(Thread.currentThread().getName() + " error.");
                }
            }
        }
    }
}
