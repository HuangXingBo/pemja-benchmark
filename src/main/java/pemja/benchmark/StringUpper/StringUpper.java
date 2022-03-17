package pemja.benchmark.StringUpper;

public class StringUpper {
    public static String createString(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"a\":\"");
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }
        sb.append("\"");
        sb.append("}");
        return sb.toString();
    }

    public String strUpper(String string) {
        return string.toUpperCase();
    }
}
