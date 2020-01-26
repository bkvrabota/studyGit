package task_4.task_43;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {

    private Queue<Integer> queue = new LinkedList<>();

    public Integer getQueue() {
        return queue.poll();
    }

    public Integer chekQueue() {
        return queue.peek();
    }

    public void setQueue(Integer num) {
        queue.add(num);
    }

    public int getSize() {
        return queue.size();
    }
}
