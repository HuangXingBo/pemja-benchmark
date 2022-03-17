package pemja.benchmark;

import org.junit.Before;
import org.junit.Test;
import pemja.benchmark.StringUpper.Interpreter;
import pemja.benchmark.StringUpper.StringUpper;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class InterpreterTestBase {

    private String path;

    @Before
    public void prepare() {
        path = String.join(File.separator, System.getProperty("user.dir"), "src", "main", "python");
    }

    @Test
    public void testStringUpper() {

        Interpreter instance = getInterpreterInstance();

        String str = StringUpper.createString(1 << 10);
        long start = System.currentTimeMillis();
        int num = getRounds();
        instance.open(path, "upper");
        for (int i = 0; i < num; i++) {
            instance.invoke("upper.upper", str);
        }
        instance.close();
        long end = System.currentTimeMillis();
        long consume = end - start;
        System.out.println(String.format("Run time is %s s", consume));
        System.out.println(String.format("The interpreter %s QPS is %s", instance.getClass().getName(), num * 1000.0 / consume));
    }

    @Test
    public void testStringUpperInMultiThreads() throws InterruptedException {
        int num = getRounds();
        int threadsNum = 3;
        CountDownLatch latch = new CountDownLatch(threadsNum);
        ExecutorService service = Executors.newFixedThreadPool(threadsNum);
        String str = StringUpper.createString(1 << 10);
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            Interpreter instance = getInterpreterInstance();
            instance.open(path, "upper");
            for (int i = 0; i < num; i++) {
                instance.invoke("upper.upper", str);
            }
            instance.close();
            long end = System.currentTimeMillis();
            long consume = end - start;
            System.out.println(String.format("Run time is %s s", consume));
            System.out.println(String.format("The interpreter %s QPS is %s", instance.getClass().getName(), num * 1000.0 / consume));
            latch.countDown();
        });
        for (int i = 0; i < threadsNum; i++) {
            service.submit(thread);
        }
        latch.await();
        service.shutdown();
    }

    abstract Interpreter getInterpreterInstance();

    abstract int getRounds();
}
