package benchmark.lambdacapture.misc;

import benchmark.lambdacapture.LambdaMetaFactoryBenchmark;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import static java.lang.Double.doubleToRawLongBits;

// Some random instance class
public class InstanceClass
{
    public int x;
    public double y;

    public static Long originalMethodJJJ(InstanceClass instance, Long a, Long b) {
        return a + b;
    }

    public static Long originalMethodJJJJ(InstanceClass instance, Long a, Long b, Long c) {
        return a + b + c;
    }

    public static Long originalMethodDJJ(InstanceClass instance, Double a, Long b) {
        return doubleToRawLongBits(a + b);
    }
}
