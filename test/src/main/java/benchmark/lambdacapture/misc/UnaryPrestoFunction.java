package benchmark.lambdacapture.misc;

public interface UnaryPrestoFunction<T, R>
{
    R apply(T t)
            throws Throwable;
}
