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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("MethodMayBeStatic")
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Fork(2)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
public class BindToBenchmark
{
    private static final int NUM_ROWS = 10_000;
    private static final int NUM_SMALL_ROWS = 10;

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 1)
    public void invoke1()
            throws Throwable
    {
        final int INVOKE_TIME = 1;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(BindToBenchmark.class.getMethod("originalMethod", BindToBenchmark.class, Long.class, Long.class));
        for (int i = 0; i < NUM_ROWS; i++) {
            MethodHandle bindedMethodHandle = MethodHandles.insertArguments(methodHandle, 0, this, Long.valueOf(i));
            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = (Long) bindedMethodHandle.invokeExact(Long.valueOf(j));
            }
        }
    }


    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 10)
    public void invoke10()
            throws Throwable
    {
        final int INVOKE_TIME = 10;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(BindToBenchmark.class.getMethod("originalMethod", BindToBenchmark.class, Long.class, Long.class));
        for (int i = 0; i < NUM_ROWS; i++) {
            MethodHandle bindedMethodHandle = MethodHandles.insertArguments(methodHandle, 0, this, Long.valueOf(i));
            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = (Long) bindedMethodHandle.invokeExact(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 100)
    public void invoke100()
            throws Throwable
    {
        final int INVOKE_TIME = 100;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(BindToBenchmark.class.getMethod("originalMethod", BindToBenchmark.class, Long.class, Long.class));
        for (int i = 0; i < NUM_ROWS; i++) {
            MethodHandle bindedMethodHandle = MethodHandles.insertArguments(methodHandle, 0, this, Long.valueOf(i));
            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = (Long) bindedMethodHandle.invokeExact(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 1000)
    public void invoke1000()
            throws Throwable
    {
        final int INVOKE_TIME = 1000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(BindToBenchmark.class.getMethod("originalMethod", BindToBenchmark.class, Long.class, Long.class));
        for (int i = 0; i < NUM_ROWS; i++) {
            MethodHandle bindedMethodHandle = MethodHandles.insertArguments(methodHandle, 0, this, Long.valueOf(i));
            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = (Long) bindedMethodHandle.invokeExact(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS * 10_000)
    public void invoke10000()
            throws Throwable
    {
        final int INVOKE_TIME = 10_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(BindToBenchmark.class.getMethod("originalMethod", BindToBenchmark.class, Long.class, Long.class));
        for (int i = 0; i < NUM_ROWS; i++) {
            MethodHandle bindedMethodHandle = MethodHandles.insertArguments(methodHandle, 0, this, Long.valueOf(i));
            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = (Long) bindedMethodHandle.invokeExact(Long.valueOf(j));
            }
        }
    }


    public static void main(String[] args)
            throws Throwable
    {
        // assure the benchmarks are valid before running
        Options options = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .include(BindToBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    public static Long originalMethod(BindToBenchmark instance, Long a, Long b) {
        return a + b;
    }
}
