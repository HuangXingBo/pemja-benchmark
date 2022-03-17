package pemja.benchmark;

import pemja.benchmark.StringUpper.Interpreter;
import pemja.benchmark.StringUpper.JythonInterpreter;


public class JythonInterpreterTest extends InterpreterTestBase {

    @Override
    Interpreter getInterpreterInstance() {
        return new JythonInterpreter();
    }

    @Override
    int getRounds() {
        return 50000;
    }
}
