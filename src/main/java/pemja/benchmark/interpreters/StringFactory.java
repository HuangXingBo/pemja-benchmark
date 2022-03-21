package pemja.benchmark.interpreters;

import java.util.Random;

public class StringFactory {

    private static final int NUM = 1 << 10;
    private final Random random;
    private final int length;
    private final String[] stringArray;

    public StringFactory(int seed, int length) {
        this.random = new Random(seed);
        this.length = length;
        this.stringArray = new String[NUM];
        init();
    }

    public String nextString() {
        return stringArray[random.nextInt(NUM)];
    }

    private void init() {
        for (int i = 0; i < NUM; i++) {
            stringArray[i] = createNewJsonString(length);
        }
    }

    private String createNewJsonString(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"a\":\"");
        for (int i = 0; i < length; i++) {
            sb.append((char) (65 + random.nextInt(26)));
        }
        sb.append("\"");
        sb.append("}");
        return sb.toString();
    }
}
