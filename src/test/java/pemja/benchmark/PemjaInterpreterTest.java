package pemja.benchmark;

import pemja.benchmark.interpreters.Interpreter;
import pemja.benchmark.interpreters.PemjaInterpreter;

public class PemjaInterpreterTest extends InterpreterTestBase {
    @Override
    Interpreter getInterpreterInstance() {
        return new PemjaInterpreter();
    }

    @Override
    int recordsNum() {
        return 5000000;
    }

    @Override
    public String toString() {
        return "PemjaInterpreter";
    }
}
