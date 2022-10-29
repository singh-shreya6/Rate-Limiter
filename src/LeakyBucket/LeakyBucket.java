package LeakyBucket;

import models.BucketStrategy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket implements BucketStrategy {
    BlockingQueue<Integer> queue;

    public LeakyBucket(int cap) {
        this.queue = new LinkedBlockingQueue(cap);
    }

    @Override
    public boolean grantAccess() {
        if(queue.remainingCapacity() > 0) {
            queue.offer(1);
            return true;
        }
        return false;
    }
}
