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

For 1, 10, ... 10k invokes, run for 10k rows.
For 100k, 1m, 10m invokes, run for 100 rows.


# MethodHandle.BindTo without Customization 

```
-Djava.lang.invoke.MethodHandle.CUSTOMIZE_THRESHOLD=-1
```


```
Benchmark                              Mode  Cnt       Score      Error  Units
BindToBenchmark.invoke1                avgt  100       0.217 ±    0.003  us/op
BindToBenchmark.invoke10               avgt  100       0.344 ±    0.004  us/op
BindToBenchmark.invoke100              avgt  100       1.539 ±    0.018  us/op
BindToBenchmark.invoke1000             avgt  100      15.897 ±    0.433  us/op
BindToBenchmark.invoke10000            avgt  100     158.186 ±    2.575  us/op
BindToBenchmark_MoreInvoke.invoke100k  avgt  100    1607.438 ±   44.127  us/op
BindToBenchmark_MoreInvoke.invoke1m    avgt  100   15915.380 ±  229.557  us/op
BindToBenchmark_MoreInvoke.invoke10m   avgt  100  160297.494 ± 8771.185  us/op
```

# LambdaMetaFactory


```
Benchmark                                         Mode  Cnt       Score     Error  Units
LambdaMetaFactoryBenchmark.invoke1                avgt  100       0.153 ±   0.006  us/op
LambdaMetaFactoryBenchmark.invoke10               avgt  100       0.327 ±   0.014  us/op
LambdaMetaFactoryBenchmark.invoke100              avgt  100       1.192 ±   0.074  us/op
LambdaMetaFactoryBenchmark.invoke1000             avgt  100      11.390 ±   0.178  us/op
LambdaMetaFactoryBenchmark.invoke10000            avgt  100     107.840 ±   1.035  us/op
LambdaMetaFactoryBenchmark_MoreInvoke.invoke100k  avgt  100    1128.142 ±  63.504  us/op
LambdaMetaFactoryBenchmark_MoreInvoke.invoke1m    avgt  100   10786.595 ± 119.753  us/op
LambdaMetaFactoryBenchmark_MoreInvoke.invoke10m   avgt  100  107097.476 ± 889.614  us/op
```



# MethodHandle.BindTo Always Customization 

```
-Djava.lang.invoke.MethodHandle.CUSTOMIZE_THRESHOLD=0
```

```
Benchmark                              Mode  Cnt       Score     Error  Units
BindToBenchmark.invoke1                avgt  100      68.926 ±   1.592  us/op
BindToBenchmark.invoke10               avgt  100      69.884 ±   1.573  us/op
BindToBenchmark.invoke100              avgt  100      79.209 ±   1.827  us/op
BindToBenchmark.invoke1000             avgt  100     278.536 ±   2.949  us/op
BindToBenchmark.invoke10000            avgt  100     991.040 ±   9.499  us/op
BindToBenchmark_MoreInvoke.invoke100k  avgt  100    3037.386 ± 151.547  us/op
BindToBenchmark_MoreInvoke.invoke1m    avgt  100   12278.194 ± 406.465  us/op
BindToBenchmark_MoreInvoke.invoke10m   avgt  100  100694.957 ± 965.196  us/op
```

# LambdaMetaFactory ExactType


```
Benchmark                                        Mode  Cnt      Score      Error  Units
LambdaMetaFactoryExactTypeBenchmark.invoke1      avgt  100      0.153 ±    0.006  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke10     avgt  100      0.298 ±    0.014  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke100    avgt  100      1.069 ±    0.066  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke1000   avgt  100     10.515 ±    0.087  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke10000  avgt  100    101.550 ±    1.135  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke100k   avgt  100   1028.997 ±   49.433  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke1m     avgt  100   9966.684 ±  109.365  us/op
LambdaMetaFactoryExactTypeBenchmark.invoke10m    avgt  100  97638.429 ± 1014.697  us/op
```


