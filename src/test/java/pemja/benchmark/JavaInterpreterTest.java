package pemja.benchmark;

import pemja.benchmark.interpreters.Interpreter;
import pemja.benchmark.interpreters.JavaInterpreter;

public class JavaInterpreterTest extends InterpreterTestBase {

    @Override
    Interpreter getInterpreterInstance() {
        return new JavaInterpreter();
    }

    @Override
    int recordsNum() {
        return 10000000;
    }

    @Override
    public String toString() {
        return "JavaInterpreter";
    }
}
