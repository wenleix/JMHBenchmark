package benchmark.lambdacapture;

import benchmark.lambdacapture.misc.InstanceClass;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ_123;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ_124;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ_125;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("MethodMayBeStatic")
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Fork(10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
public class BenchmarkLambdaCapture
{
    @SuppressWarnings("FieldMayBeFinal")
    @State(Scope.Thread)
    public static class BenchmarkData
    {
        @Param({"1000"})
        private int numRows;

        public MethodHandle mhJJJ;
        public MethodHandle mhJJJJ;
        public MethodHandle mhDJJ;

        public InstanceClass fakedThis = new InstanceClass();

        public MethodHandle factory1;

        public UnaryPrestoFunctionJJ[] unaryFunction;   //  Manual generated
        public UnaryPrestoFunctionJJ[] capturedLambdas;
        public MethodHandle[] bindedMethodHandle;

        @Setup
        public void setup()
                throws Throwable
        {
            mhJJJ = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethodJJJ", InstanceClass.class, Long.class, Long.class));
            mhJJJJ = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethodJJJJ", InstanceClass.class, Long.class, Long.class, Long.class));
            mhDJJ = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethodDJJ", InstanceClass.class, Double.class, Long.class));

            MethodHandle factory1 = LambdaMetafactory.metafactory(
                    MethodHandles.lookup(),
                    "apply",
                    MethodType.methodType(UnaryPrestoFunctionJJ.class, InstanceClass.class, Long.class),    // arg1 -> CapturedLambda
                    MethodType.methodType(Long.class, Long.class),              // arg2 -> ret, after type erasure
                    mhJJJ,                                                          // Original method, (arg1, arg2) -> ret
                    MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
            ).getTarget();



            Random rand = new Random(12345);

            unaryFunction = new UnaryPrestoFunctionJJ[numRows];
            capturedLambdas = new UnaryPrestoFunctionJJ[numRows];
            bindedMethodHandle = new MethodHandle[numRows];
        }
    }

    @Benchmark
    public UnaryPrestoFunctionJJ[] benchmarkManualClass(BenchmarkData data)
            throws Throwable
    {
        UnaryPrestoFunctionJJ[] unaryFunction = data.unaryFunction;
        InstanceClass fakedThis = data.fakedThis;
        MethodHandle mhJJJ = data.mhJJJ;

        for (int i = 0; i < data.numRows; i++) {
            unaryFunction[i] = new UnaryPrestoFunctionJJ_123(mhJJJ, fakedThis, Long.valueOf(i));
        }

        return unaryFunction;
    }

    @Benchmark
    public UnaryPrestoFunctionJJ[] benchmarkMetafactoryExact(BenchmarkData data)
            throws Throwable
    {
        UnaryPrestoFunctionJJ[] capturedLambdas = data.capturedLambdas;
        InstanceClass fakedThis = data.fakedThis;
        MethodHandle captureFactory = data.factory1;

        for (int i = 0; i < data.numRows; i++) {
            capturedLambdas[i] = (UnaryPrestoFunctionJJ) captureFactory.invokeExact(fakedThis, Long.valueOf(i));
        }

        return capturedLambdas;
    }

    @Benchmark
    public MethodHandle[] benchmarkBindedMethodHandle(BenchmarkData data)
            throws Throwable
    {
        MethodHandle[] bindedMethodHandle = data.bindedMethodHandle;
        InstanceClass fakedThis = data.fakedThis;
        MethodHandle mhJJJ = data.mhJJJ;

        for (int i = 0; i < data.numRows; i++) {
            bindedMethodHandle[i] = MethodHandles.insertArguments(mhJJJ, 0, fakedThis, Long.valueOf(i));
        }

        return bindedMethodHandle;
    }

    public static void main(String[] args)
            throws Throwable
    {
        // assure the benchmarks are valid before running
        Options options = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .include(BenchmarkLambdaCapture.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
