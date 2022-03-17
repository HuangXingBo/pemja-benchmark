package pemja.benchmark.StringUpper;

public interface Interpreter {
    void open(String pythonPath, String pythonFile);

    void invoke(String name, Object... args);

    void close();
}
