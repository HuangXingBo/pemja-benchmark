package pemja.benchmark.interpreters;

import jep.Jep;
import jep.JepConfig;
import jep.JepException;
import jep.SharedInterpreter;

public class JepInterpreter implements Interpreter {
    private Jep interp;

    static {
        System.loadLibrary("jep");
    }

    @Override
    public void open(String pythonPath, String pythonFile) {
        System.loadLibrary("jep");
        JepConfig jepConfig = new JepConfig();
        jepConfig.addIncludePaths(pythonPath);
        try {
            SharedInterpreter.setConfig(jepConfig);
            this.interp = new SharedInterpreter();
            interp.exec("import " + pythonFile);
        } catch (JepException e) {
            throw new RuntimeException("Failed to initialize interpreters.", e);
        }
    }

    @Override
    public Object invoke(String name, Object... args) {
        try {
            return interp.invoke(name, args);
        } catch (JepException e) {
            throw new RuntimeException("Failed to invoke functions.", e);
        }
    }

    @Override
    public void close() {
        try {
            interp.close();
        } catch (JepException e) {
            throw new RuntimeException("Failed to close Jep Interpreter.", e);
        }
    }
}
