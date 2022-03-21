package pemja.benchmark.interpreters;

import pemja.core.PythonInterpreter;
import pemja.core.PythonInterpreterConfig;

public class PemjaInterpreter implements Interpreter {

    private PythonInterpreter interpreter;

    @Override
    public void open(String path, String file) {
        PythonInterpreterConfig config =
                PythonInterpreterConfig.newBuilder()
                        .setPythonExec("python3")
                        .setExcType(PythonInterpreterConfig.ExecType.SUB_INTERPRETER)
                        .addPythonPaths(path)
                        .build();
        this.interpreter = new PythonInterpreter(config);
        this.interpreter.exec("import " + file);
    }

    @Override
    public Object invoke(String name, Object... args) {
        return this.interpreter.invoke(name, args);
    }

    @Override
    public void close() {
        this.interpreter.close();
    }
}
