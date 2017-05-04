# Experiment  Setup

Original Method: 

    public static Long originalMethod(Object instance, Long a, Long b) {
        return a + b;
    }

Experiment pseudo-code:

    for (int i = 0; i < NUM_ROWS; i++) {
        bindedFunction = BIND(originalMethod, this, i)
        for (int j = 0; j < INVOKE_TIME; j++) {
            call bindedFunction(j)
        }
    }


# MethodHandle.BindTo without Customization 

```
-Djava.lang.invoke.MethodHandle.CUSTOMIZE_THRESHOLD=-1
```

## Basic

After binding, invoke 1, 10, 100, ... 10k times. Run for 10k rows. 

```
Benchmark                     Mode  Cnt   Score   Error   Units
BindToBenchmark.invoke1      thrpt   20   6.677 ± 0.428  ops/us
BindToBenchmark.invoke10     thrpt   20  40.498 ± 1.321  ops/us
BindToBenchmark.invoke100    thrpt   20  93.537 ± 2.419  ops/us
BindToBenchmark.invoke1000   thrpt   20  93.350 ± 3.229  ops/us
BindToBenchmark.invoke10000  thrpt   20  93.386 ± 1.529  ops/us
```

## More

After binding, invoke 100k, 1m, 10m times. Run for 100 rows. 

```
BindToBenchmark_MoreInvoke.invoke100k  thrpt   20  91.191 ± 1.503  ops/us
BindToBenchmark_MoreInvoke.invoke1m    thrpt   20  99.912 ± 0.988  ops/us
BindToBenchmark_MoreInvoke.invoke10m   thrpt   20  95.676 ± 2.020  ops/us
```

# MethodHandle.BindTo Always Customization 

```
-Djava.lang.invoke.MethodHandle.CUSTOMIZE_THRESHOLD=0
```

## Basic

After binding, invoke 1, 10, 100, ... 10k times. Run for 10k rows. 

```
Benchmark                     Mode  Cnt   Score   Error   Units
BindToBenchmark.invoke1      thrpt   20   0.027 ± 0.002  ops/us
BindToBenchmark.invoke10     thrpt   20   0.276 ± 0.014  ops/us
BindToBenchmark.invoke100    thrpt   20   2.291 ± 0.089  ops/us
BindToBenchmark.invoke1000   thrpt   20   5.432 ± 0.469  ops/us
BindToBenchmark.invoke10000  thrpt   20  14.445 ± 0.624  ops/us
```

## More 

After binding, invoke 100k, 1m, 10m times. Run for 100 rows. 

```
Benchmark                               Mode  Cnt    Score   Error   Units
BindToBenchmark_MoreInvoke.invoke100k  thrpt   20   55.300 ± 2.151  ops/us
BindToBenchmark_MoreInvoke.invoke1m    thrpt   20  119.919 ± 0.948  ops/us
BindToBenchmark_MoreInvoke.invoke10m   thrpt   20  134.768 ± 1.031  ops/us
```


