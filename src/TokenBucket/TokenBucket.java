package TokenBucket;

import models.BucketStrategy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements BucketStrategy {
    int capacity;
    int refreshRate;
    AtomicInteger currentCapacity;
    AtomicLong lastUpdatedTime;

    public TokenBucket(int capacity, int refreshRete) {
        this.capacity = capacity;
        this.refreshRate = refreshRete;
        this.currentCapacity = new AtomicInteger(capacity);
        this.lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean grantAccess() {
        refreshBucket();
        if(currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refreshBucket() {
        long currentTime = System.currentTimeMillis();
        long additionalToken = (currentTime - lastUpdatedTime.get())/1000 * refreshRate;
        currentCapacity.getAndSet((int) Math.min(currentCapacity.get() + additionalToken, capacity));
        lastUpdatedTime.getAndSet(currentTime);
    }
}
