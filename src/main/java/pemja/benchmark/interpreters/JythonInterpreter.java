package pemja.benchmark.interpreters;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.util.Properties;

public class JythonInterpreter implements Interpreter {
    private PythonInterpreter interpreter;

    @Override
    public void open(String pythonPath, String pythonFile) {
        Properties properties = new Properties();
        properties.put("python.path", pythonPath);
        properties.put("python.console.encoding", "UTF-8");

        properties.put("python.security.respectJavaAccessibility", "false");

        properties.put("python.import.site", "false");
        PythonInterpreter.initialize(System.getProperties(), properties, new String[] {""});
        this.interpreter = new PythonInterpreter();
        this.interpreter.exec("import " + pythonFile);
    }

    @Override
    public Object invoke(String name, Object... args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            // TODO: other types support.
            if (!(args[i] instanceof String)) {
                throw new RuntimeException("Currently only String data are supported.");
            }
            PyString in = new PyString((String) args[i]);
            interpreter.set("in" + i, in);
            sb.append("in").append(i).append(",");
        }
        interpreter.exec(
                String.format(
                        "result = %s(%s)", name, sb.deleteCharAt(sb.length() - 1).toString()));
        PyString out = (PyString) interpreter.get("result");
        return out.toString();
    }

    @Override
    public void close() {
        interpreter.close();
    }
}
