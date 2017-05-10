package benchmark.lambdacapture.misc;

import java.lang.invoke.MethodHandle;

// Some hypothetical generated class
public class UnaryPrestoFunctionJJ_124
        implements UnaryPrestoFunctionJJ
{
    private MethodHandle targetMethod;
    private InstanceClass fakedThis;
    private Long captureL_1;
    private Long captureL_2;

    public UnaryPrestoFunctionJJ_124(MethodHandle targetMethod, InstanceClass fakedThis, Long captureL_1, Long captureL_2) {
        this.targetMethod = targetMethod;
        this.fakedThis = fakedThis;
        this.captureL_1 = captureL_1;
        this.captureL_2 = captureL_2;
    }

    @Override
    public Long apply(Long t)
            throws Throwable
    {
        return (Long) targetMethod.invokeExact(fakedThis, captureL_1, captureL_2, t);
    }

}
