package pemja.benchmark.udfs;

public class StringUpperUdf implements Udf {

    @Override
    public Object invoke(Object... args) {
        return ((String) args[0]).toUpperCase();
    }
}
