package benchmark.lambdacapture.misc;

import java.lang.invoke.MethodHandle;

// Some hypothetical generated class
public class UnaryPrestoFunctionJJ_123
        implements UnaryPrestoFunctionJJ
{
    private MethodHandle targetMethod;
    private InstanceClass fakedThis;
    private Long captureJ_1;

    public UnaryPrestoFunctionJJ_123(MethodHandle targetMethod, InstanceClass fakedThis, Long captureJ_1) {
        this.targetMethod = targetMethod;
        this.fakedThis = fakedThis;
        this.captureJ_1 = captureJ_1;
    }

    @Override
    public Long apply(Long t)
            throws Throwable
    {
        return (Long) targetMethod.invokeExact(fakedThis, captureJ_1, t);
    }

}
