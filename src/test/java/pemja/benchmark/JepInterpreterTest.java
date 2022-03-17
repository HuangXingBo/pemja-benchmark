package pemja.benchmark;

import org.junit.Ignore;
import pemja.benchmark.StringUpper.Interpreter;
import pemja.benchmark.StringUpper.JepInterpreter;

@Ignore
public class JepInterpreterTest extends InterpreterTestBase {
    @Override
    Interpreter getInterpreterInstance() {
        return new JepInterpreter();
    }

    @Override
    int getRounds() {
        return 5000000;
    }
}
