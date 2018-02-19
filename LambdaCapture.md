- Capture (Binding) Speed

```
Benchmark                                           (numRows)  Mode  Cnt    Score   Error  Units
BenchmarkLambdaCapture.benchmarkBindedMethodHandle       1000  avgt  100  206.341 ± 1.864  us/op
BenchmarkLambdaCapture.benchmarkManualClass              1000  avgt  100   11.086 ± 0.851  us/op
BenchmarkLambdaCapture.benchmarkMetafactoryExact         1000  avgt  100   12.697 ± 0.303  us/op
```

- Invocation Speed

```
Benchmark                                                 (numInvokes)  (numRows)  Mode  Cnt        Score       Error  Units
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle             1       1000  avgt  100       78.176 ±     0.487  us/op
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle            10       1000  avgt  100      214.947 ±     1.694  us/op
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle           100       1000  avgt  100     1197.327 ±    22.216  us/op
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle          1000       1000  avgt  100    11603.837 ±   272.146  us/op
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle         10000       1000  avgt  100   110179.648 ±  1176.101  us/op
BenchmarkLambdaCaptureInvoke.benchmarkBindedMethodHandle        100000       1000  avgt  100  1086046.230 ±  9617.518  us/op

BenchmarkLambdaCaptureInvoke.benchmarkManualClass                    1       1000  avgt  100       31.511 ±     0.519  us/op
BenchmarkLambdaCaptureInvoke.benchmarkManualClass                   10       1000  avgt  100      183.039 ±     1.860  us/op
BenchmarkLambdaCaptureInvoke.benchmarkManualClass                  100       1000  avgt  100     1682.984 ±    14.614  us/op
BenchmarkLambdaCaptureInvoke.benchmarkManualClass                 1000       1000  avgt  100    16818.867 ±   614.756  us/op
BenchmarkLambdaCaptureInvoke.benchmarkManualClass                10000       1000  avgt  100   163508.354 ±  2955.828  us/op
BenchmarkLambdaCaptureInvoke.benchmarkManualClass               100000       1000  avgt  100  1617802.584 ± 11485.638  us/op

BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact               1       1000  avgt  100       25.685 ±     0.155  us/op
BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact              10       1000  avgt  100      140.457 ±     2.124  us/op
BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact             100       1000  avgt  100     1268.159 ±    12.673  us/op
BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact            1000       1000  avgt  100    12315.581 ±    80.210  us/op
BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact           10000       1000  avgt  100   122422.557 ±  1219.947  us/op
BenchmarkLambdaCaptureInvoke.benchmarkMetafactoryExact          100000       1000  avgt  100  1211100.367 ±  8294.416  us/op
```



