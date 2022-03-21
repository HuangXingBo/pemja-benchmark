package pemja.benchmark;

import pemja.benchmark.interpreters.Interpreter;
import pemja.benchmark.interpreters.JythonInterpreter;

public class JythonInterpreterTest extends InterpreterTestBase {

    @Override
    Interpreter getInterpreterInstance() {
        return new JythonInterpreter();
    }

    @Override
    int recordsNum() {
        return 50000;
    }

    @Override
    public String toString() {
        return "JythonInterpreter";
    }
}
