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
import org.openjdk.jmh.annotations.OperationsPerInvocation;
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
import org.openjdk.jmh.runner.options.WarmupMode;

import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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

        @Param({"1", "10", "100", "1000", "10000", "100000"})
        private int numInvokes;

        public MethodHandle mhJJJ;
        public MethodHandle mhJJJJ;
        public MethodHandle mhDJJ;

        private UnaryPrestoFunctionJJ[] unaryFunction;
        private UnaryPrestoFunctionJJ[] capturedLambdas;
        private MethodHandle[] bindedMethodHandle;

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

            MethodHandle factory2 = LambdaMetafactory.metafactory(
                    MethodHandles.lookup(),
                    "apply",
                    MethodType.methodType(UnaryPrestoFunctionJJ.class, InstanceClass.class, Long.class, Long.class),    // arg1 -> CapturedLambda
                    MethodType.methodType(Long.class, Long.class),              // arg2 -> ret, after type erasure
                    mhJJJJ,                                                          // Original method, (arg1, arg2) -> ret
                    MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
            ).getTarget();


            MethodHandle factory3 = LambdaMetafactory.metafactory(
                    MethodHandles.lookup(),
                    "apply",
                    MethodType.methodType(UnaryPrestoFunctionJJ.class, InstanceClass.class, Double.class),    // arg1 -> CapturedLambda
                    MethodType.methodType(Long.class, Long.class),              // arg2 -> ret, after type erasure
                    mhDJJ,                                                          // Original method, (arg1, arg2) -> ret
                    MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
            ).getTarget();

            InstanceClass fakedThis = new InstanceClass();

            Random rand = new Random(12345);

            unaryFunction = new UnaryPrestoFunctionJJ[numRows];
            capturedLambdas = new UnaryPrestoFunctionJJ[numRows];
            bindedMethodHandle = new MethodHandle[numRows];

            for (int i = 0; i < numRows; i++) {
                int state = rand.nextInt(3);
                if (state == 0) {
                    unaryFunction[i] = new UnaryPrestoFunctionJJ_123(mhJJJ, fakedThis, Long.valueOf(i));
                    capturedLambdas[i] = (UnaryPrestoFunctionJJ) factory1.invokeExact(fakedThis, Long.valueOf(i));
                    bindedMethodHandle[i] = MethodHandles.insertArguments(mhJJJ, 0, fakedThis, Long.valueOf(i));
                }
                else if (state == 1) {
                    unaryFunction[i] = new UnaryPrestoFunctionJJ_124(mhJJJJ, fakedThis, Long.valueOf(i), Long.valueOf(i * 2));
                    capturedLambdas[i] = (UnaryPrestoFunctionJJ) factory2.invokeExact(fakedThis, Long.valueOf(i), Long.valueOf(i * 2));
                    bindedMethodHandle[i] = MethodHandles.insertArguments(mhJJJJ, 0, fakedThis, Long.valueOf(i), Long.valueOf(i * 2));
                }
                else if (state == 2) {
                    unaryFunction[i] = new UnaryPrestoFunctionJJ_125(mhDJJ, fakedThis, Double.valueOf(i + 0.5));
                    capturedLambdas[i] = (UnaryPrestoFunctionJJ) factory3.invokeExact(fakedThis, Double.valueOf(i + 0.5));
                    bindedMethodHandle[i] = MethodHandles.insertArguments(mhDJJ, 0, fakedThis, Double.valueOf(i + 0.5));
                }
            }
        }
    }

    @Benchmark
    public long benchmarkManualClass(BenchmarkData data)
            throws Throwable
    {
        long sum = 0;

        for (int i = 0; i < data.numRows; i++) {
            UnaryPrestoFunctionJJ func = data.unaryFunction[i];
            for (int j = 0; j < data.numInvokes; j++) {
                sum += func.apply(Long.valueOf(i));
            }
        }
        return sum;
    }

    @Benchmark
    public long benchmarkMetafactoryExact(BenchmarkData data)
            throws Throwable
    {
        long sum = 0;

        for (int i = 0; i < data.numRows; i++) {
            UnaryPrestoFunctionJJ func = data.capturedLambdas[i];
            for (int j = 0; j < data.numInvokes; j++) {
                sum += func.apply(Long.valueOf(i));
            }
        }
        return sum;
    }

    @Benchmark
    public long benchmarkBindedMethodHandle(BenchmarkData data)
            throws Throwable
    {
        long sum = 0;

        for (int i = 0; i < data.numRows; i++) {
            MethodHandle func = data.bindedMethodHandle[i];
            for (int j = 0; j < data.numInvokes; j++) {
                sum += (Long) func.invokeExact(Long.valueOf(i));
            }
        }
        return sum;
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
