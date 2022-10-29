package SlidingWindow;

import models.BucketStrategy;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements BucketStrategy {

    int capacity;
    int timeWindow;
    Queue<Long> queue;

    public SlidingWindow(int capacity, int timeWindow) {
        this.capacity = capacity;
        this.timeWindow = timeWindow;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess() {
        checkAndUpdateWindow();
        if(queue.size() < capacity) {
            queue.offer(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    private void checkAndUpdateWindow() {
        if (queue.isEmpty()) {
            return;
        }
        long currTime = System.currentTimeMillis();
        long time = (currTime - queue.peek())/1000;
        while (time > timeWindow) {
            queue.poll();
            if(queue.isEmpty()) {
                return;
            }
            time = (currTime - queue.peek())/1000;
        }
    }
}
