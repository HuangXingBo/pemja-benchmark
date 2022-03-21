package pemja.benchmark.interpreters;

import pemja.benchmark.udfs.JsonUdf;
import pemja.benchmark.udfs.StringUpperUdf;
import pemja.benchmark.udfs.Udf;

public class JavaInterpreter implements Interpreter {

    private Udf udf;

    @Override
    public void open(String pythonPath, String pythonFile) {
        if (pythonFile.equalsIgnoreCase("str_upper_udf")) {
            udf = new StringUpperUdf();
        } else if (pythonFile.equalsIgnoreCase("json_udf")) {
            udf = new JsonUdf();
        } else {
            throw new RuntimeException("Unknown udf type.");
        }
    }

    @Override
    public Object invoke(String name, Object... args) {
        return udf.invoke(args);
    }

    @Override
    public void close() {}
}
