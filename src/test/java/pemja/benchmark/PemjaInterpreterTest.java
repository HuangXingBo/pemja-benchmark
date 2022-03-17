package pemja.benchmark;

import pemja.benchmark.StringUpper.Interpreter;
import pemja.benchmark.StringUpper.PemjaInterpreter;

public class PemjaInterpreterTest extends InterpreterTestBase {
    @Override
    Interpreter getInterpreterInstance() {
        return new PemjaInterpreter();
    }

    @Override
    int getRounds() {
        return 10000000;
    }
}
