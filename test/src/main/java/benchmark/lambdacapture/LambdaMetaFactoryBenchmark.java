package benchmark.lambdacapture;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SuppressWarnings("MethodMayBeStatic")
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Fork(2)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
public class LambdaMetaFactoryBenchmark
{
    private static final int NUM_ROWS = 10_000;

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 1)
    public void invoke1()
            throws Throwable
    {
        final int INVOKE_TIME = 1;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(LambdaMetaFactoryBenchmark.class.getMethod("originalMethod", LambdaMetaFactoryBenchmark.class, Long.class, Long.class));

        MethodHandle factory = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class, LambdaMetaFactoryBenchmark.class, Long.class),    // arg1 -> CapturedLambda
                MethodType.methodType(Object.class, Object.class),              // arg2 -> ret, after type erasure
                methodHandle,                                                   // Original method, (arg1, arg2) -> ret
                MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
        ).getTarget();

        for (int i = 0; i < NUM_ROWS; i++) {
            Function<Long, Long> capturedLambda = (Function<Long, Long>) factory.invokeExact(this, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 10)
    public void invoke10()
            throws Throwable
    {
        final int INVOKE_TIME = 10;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(LambdaMetaFactoryBenchmark.class.getMethod("originalMethod", LambdaMetaFactoryBenchmark.class, Long.class, Long.class));

        MethodHandle factory = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class, LambdaMetaFactoryBenchmark.class, Long.class),    // arg1 -> CapturedLambda
                MethodType.methodType(Object.class, Object.class),              // arg2 -> ret, after type erasure
                methodHandle,                                                   // Original method, (arg1, arg2) -> ret
                MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
        ).getTarget();

        for (int i = 0; i < NUM_ROWS; i++) {
            Function<Long, Long> capturedLambda = (Function<Long, Long>) factory.invokeExact(this, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 100)
    public void invoke100()
            throws Throwable
    {
        final int INVOKE_TIME = 100;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(LambdaMetaFactoryBenchmark.class.getMethod("originalMethod", LambdaMetaFactoryBenchmark.class, Long.class, Long.class));

        MethodHandle factory = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class, LambdaMetaFactoryBenchmark.class, Long.class),    // arg1 -> CapturedLambda
                MethodType.methodType(Object.class, Object.class),              // arg2 -> ret, after type erasure
                methodHandle,                                                   // Original method, (arg1, arg2) -> ret
                MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
        ).getTarget();

        for (int i = 0; i < NUM_ROWS; i++) {
            Function<Long, Long> capturedLambda = (Function<Long, Long>) factory.invokeExact(this, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 1000)
    public void invoke1000()
            throws Throwable
    {
        final int INVOKE_TIME = 1000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(LambdaMetaFactoryBenchmark.class.getMethod("originalMethod", LambdaMetaFactoryBenchmark.class, Long.class, Long.class));

        MethodHandle factory = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class, LambdaMetaFactoryBenchmark.class, Long.class),    // arg1 -> CapturedLambda
                MethodType.methodType(Object.class, Object.class),              // arg2 -> ret, after type erasure
                methodHandle,                                                   // Original method, (arg1, arg2) -> ret
                MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
        ).getTarget();

        for (int i = 0; i < NUM_ROWS; i++) {
            Function<Long, Long> capturedLambda = (Function<Long, Long>) factory.invokeExact(this, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 10_000)
    public void invoke10000()
            throws Throwable
    {
        final int INVOKE_TIME = 10_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(LambdaMetaFactoryBenchmark.class.getMethod("originalMethod", LambdaMetaFactoryBenchmark.class, Long.class, Long.class));

        MethodHandle factory = LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class, LambdaMetaFactoryBenchmark.class, Long.class),    // arg1 -> CapturedLambda
                MethodType.methodType(Object.class, Object.class),              // arg2 -> ret, after type erasure
                methodHandle,                                                   // Original method, (arg1, arg2) -> ret
                MethodType.methodType(Long.class, Long.class)                   // arg2 -> ret, original type
        ).getTarget();

        for (int i = 0; i < NUM_ROWS; i++) {
            Function<Long, Long> capturedLambda = (Function<Long, Long>) factory.invokeExact(this, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    public static void main(String[] args)
            throws Throwable
    {
        // assure the benchmarks are valid before running
        Options options = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .include(LambdaMetaFactoryBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    public static Long originalMethod(LambdaMetaFactoryBenchmark instance, Long a, Long b) {
        return a + b;
    }
}
