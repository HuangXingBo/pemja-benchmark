package pemja.benchmark.StringUpper;

public class JavaInterpreter implements Interpreter {
    private StringUpper upper;

    @Override
    public void open(String pythonPath, String pythonFile) {
        // TODO: currently only support StringUpper()
        this.upper = new StringUpper();
    }

    @Override
    public void invoke(String name, Object... args) {
        upper.strUpper((String) args[0]);
    }

    @Override
    public void close() {
    }
}
