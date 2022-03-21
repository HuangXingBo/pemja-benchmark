package pemja.benchmark;

import org.junit.Before;
import org.junit.Test;
import pemja.benchmark.interpreters.Interpreter;
import pemja.benchmark.interpreters.StringFactory;

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
    public void testStringUpper() throws Exception {
        testInSingleThread(100, "str_upper_udf", "str_upper_udf.upper");
        testInSingleThread(1 << 10, "str_upper_udf", "str_upper_udf.upper");
        testInMultiThreads(100, 3, "str_upper_udf", "str_upper_udf.upper");
        testInMultiThreads(1 << 10, 3, "str_upper_udf", "str_upper_udf.upper");
    }

    @Test
    public void testJsonString() throws Exception {
        testInSingleThread(100, "json_udf", "json_udf.json");
        testInSingleThread(1 << 10, "json_udf", "json_udf.json");
        testInMultiThreads(100, 3, "json_udf", "json_udf.json");
        testInMultiThreads(1 << 10, 3, "json_udf", "json_udf.json");
    }

    private void testInSingleThread(int dataLength, String pythonFile, String methodName) {

        Interpreter instance = getInterpreterInstance();

        StringFactory factory = new StringFactory(0, dataLength);
        long start = System.currentTimeMillis();
        int num = recordsNum();
        instance.open(path, pythonFile);
        for (int i = 0; i < num; i++) {
            instance.invoke(methodName, factory.nextString());
        }
        instance.close();
        long end = System.currentTimeMillis();
        long consume = end - start;
        System.out.println(String.format("Run time is %s s", consume));
        System.out.println(
                String.format(
                        "The interpreter %s QPS in %s is %s with input data length is %s",
                        this, methodName, (int) (num * 1000.0 / consume), dataLength));
    }

    private void testInMultiThreads(
            int dataLength, int threadsNum, String pythonFile, String methodName) throws Exception {
        int num = recordsNum();
        CountDownLatch latch = new CountDownLatch(threadsNum);
        ExecutorService service = Executors.newFixedThreadPool(threadsNum);
        long start = System.currentTimeMillis();
        Thread thread =
                new Thread(
                        () -> {
                            StringFactory factory = new StringFactory(0, dataLength);
                            Interpreter instance = getInterpreterInstance();
                            instance.open(path, pythonFile);
                            for (int i = 0; i < num; i++) {
                                instance.invoke(methodName, factory.nextString());
                            }
                            instance.close();
                            latch.countDown();
                        });
        for (int i = 0; i < threadsNum; i++) {
            service.submit(thread);
        }
        latch.await();
        service.shutdown();
        long end = System.currentTimeMillis();
        long consume = end - start;
        System.out.println(String.format("Run time is %s s", consume));
        System.out.println(
                String.format(
                        "The interpreter %s QPS in %s is %s with %s threads, input data length is %s",
                        this,
                        methodName,
                        (int) (num * threadsNum * 1000.0 / consume),
                        threadsNum,
                        dataLength));
    }

    abstract Interpreter getInterpreterInstance();

    abstract int recordsNum();
}
