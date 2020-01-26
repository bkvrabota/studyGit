package task_4.task_44;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeInfo implements Runnable {

    private long timeInterval;

    TimeInfo(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                LocalTime time = LocalTime.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String output = formatter.format(time);

                System.out.println("Time: " + output);
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Time thread error.");
            }
        }
    }
}
