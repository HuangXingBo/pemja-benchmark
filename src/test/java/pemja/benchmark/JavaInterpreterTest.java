package pemja.benchmark;

import pemja.benchmark.StringUpper.Interpreter;
import pemja.benchmark.StringUpper.JavaInterpreter;

public class JavaInterpreterTest extends InterpreterTestBase {
    @Override
    Interpreter getInterpreterInstance() {
        return new JavaInterpreter();
    }

    @Override
    int getRounds() {
        return 1000000;
    }
}
