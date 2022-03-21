package pemja.benchmark.interpreters;

public interface Interpreter {
    void open(String pythonPath, String pythonFile);

    Object invoke(String name, Object... args);

    void close();
}
