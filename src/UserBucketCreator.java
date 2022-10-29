
import LeakyBucket.LeakyBucket;
import SlidingWindow.SlidingWindow;
import models.BucketStrategy;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, BucketStrategy> bucket;

    UserBucketCreator(int id, int cap) {
        this.bucket = new HashMap<>();
        bucket.put(id, new LeakyBucket(cap));
    }

    void accessApplication(int id) {
        if (bucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> able to access the application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> Too many request, Please try after some time");
        }
    }
}
