public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserBucketCreator userBucketCreator = new UserBucketCreator(1, 10);
        for(int i = 1; i <= 15; i ++) {
            new Thread(() -> userBucketCreator.accessApplication(1)).start();
            //Thread.sleep(5);
        }
    }
}
