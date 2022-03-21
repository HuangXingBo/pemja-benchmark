package pemja.benchmark;

import org.junit.Ignore;
import pemja.benchmark.interpreters.Interpreter;
import pemja.benchmark.interpreters.JepInterpreter;

@Ignore
public class JepInterpreterTest extends InterpreterTestBase {
    @Override
    Interpreter getInterpreterInstance() {
        return new JepInterpreter();
    }

    @Override
    int recordsNum() {
        return 5000000;
    }

    @Override
    public String toString() {
        return "JepInterpreter";
    }
}
