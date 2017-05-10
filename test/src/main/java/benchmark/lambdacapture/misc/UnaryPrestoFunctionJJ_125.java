package benchmark.lambdacapture.misc;

import java.lang.invoke.MethodHandle;

// Some hypothetical generated class
public class UnaryPrestoFunctionJJ_125
        implements UnaryPrestoFunctionJJ
{
    private MethodHandle targetMethod;
    private InstanceClass fakedThis;
    private Double captureD_1;

    public UnaryPrestoFunctionJJ_125(MethodHandle targetMethod, InstanceClass fakedThis, Double captureD_1) {
        this.targetMethod = targetMethod;
        this.fakedThis = fakedThis;
        this.captureD_1 = captureD_1;
    }

    @Override
    public Long apply(Long t)
            throws Throwable
    {
        return (Long) targetMethod.invokeExact(fakedThis, captureD_1, t);
    }

}
