package benchmark.lambdacapture;

import benchmark.lambdacapture.misc.InstanceClass;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ;
import benchmark.lambdacapture.misc.UnaryPrestoFunctionJJ_123;
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

@SuppressWarnings("MethodMayBeStatic")
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Fork(10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
public class ManualCodeGenBenchmark
{
    private static final int NUM_ROWS = 10_000;
    private static final int NUM_LESS_ROWS = 100;
    private static final InstanceClass fakedThis = new InstanceClass();

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS)
    public void invoke1()
            throws Throwable
    {
        final int INVOKE_TIME = 1;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS)
    public void invoke10()
            throws Throwable
    {
        final int INVOKE_TIME = 10;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS)
    public void invoke100()
            throws Throwable
    {
        final int INVOKE_TIME = 100;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS)
    public void invoke1000()
            throws Throwable
    {
        final int INVOKE_TIME = 1000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_ROWS)
    public void invoke10k()
            throws Throwable
    {
        final int INVOKE_TIME = 10_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_LESS_ROWS)
    public void invoke100k()
            throws Throwable
    {
        final int INVOKE_TIME = 100_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_LESS_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_LESS_ROWS)
    public void invoke1m()
            throws Throwable
    {
        final int INVOKE_TIME = 1_000_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_LESS_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

            for (int j = 0; j < INVOKE_TIME; j++) {
                Long ret = capturedLambda.apply(Long.valueOf(j));
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(NUM_LESS_ROWS)
    public void invoke10m()
            throws Throwable
    {
        final int INVOKE_TIME = 10_000_000;

        MethodHandle methodHandle = MethodHandles.lookup().unreflect(InstanceClass.class.getMethod("originalMethod", InstanceClass.class, Long.class, Long.class));

        for (int i = 0; i < NUM_LESS_ROWS; i++) {
            // It should be from some factory method. But for benchmark let's just cheat and directly call the constructor
            UnaryPrestoFunctionJJ capturedLambda = new UnaryPrestoFunctionJJ_123(methodHandle, fakedThis, Long.valueOf(i));

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
                .include(ManualCodeGenBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
