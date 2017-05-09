package benchmark.lambdacapture.misc;

// Some random instance class
public class InstanceClass
{
    public int x;
    public double y;

    public static Long originalMethod(InstanceClass instance, Long a, Long b) {
        return a + b;
    }
}
