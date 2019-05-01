// package ro.codecamp.sixth;
//
// import java.lang.reflect.InvocationHandler;
// import java.lang.reflect.Method;
// import java.lang.reflect.Proxy;
// import java.util.concurrent.TimeUnit;
//
// import org.openjdk.jmh.annotations.Benchmark;
// import org.openjdk.jmh.annotations.BenchmarkMode;
// import org.openjdk.jmh.annotations.Mode;
// import org.openjdk.jmh.annotations.Scope;
// import org.openjdk.jmh.annotations.State;
// import org.openjdk.jmh.runner.Runner;
// import org.openjdk.jmh.runner.RunnerException;
// import org.openjdk.jmh.runner.options.Options;
// import org.openjdk.jmh.runner.options.OptionsBuilder;
// import org.openjdk.jmh.runner.options.TimeValue;
// import org.springframework.boot.SpringApplication;
// import org.springframework.cglib.proxy.Callback;
// import org.springframework.cglib.proxy.Enhancer;
// import org.springframework.cglib.proxy.MethodInterceptor;
// import org.springframework.cglib.proxy.MethodProxy;
// import org.springframework.context.ApplicationContext;
//
// public class ProxyBenchmark {
//
//     @State(Scope.Benchmark)
//     public static class StateHolder {
//         private FooServiceImpl fooService = new FooServiceImpl();
//     }
//
//     @Benchmark
//     @BenchmarkMode(Mode.All)
//     public void jdk_dynamic_proxy_benchmark(StateHolder stateHolder) {
//         final FooService proxy = (FooService) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] {FooService.class}, new InvocationHandler() {
//             public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
//                 System.out.println("Intercepting method call...");
//                 return method.invoke(stateHolder.fooService, args);
//             }
//         });
//         proxy.foo();
//     }
//
//     @Benchmark
//     @BenchmarkMode(Mode.All)
//     public void cglib_proxy_benchmark(StateHolder stateHolder) {
//         final FooService proxy = (FooService) Enhancer.create(stateHolder.fooService.getClass(), new MethodInterceptor() {
//
//             @Override
//             public Object intercept(final Object o, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {
//                 System.out.println("Intercepting method call...");
//                 return method.invoke(stateHolder.fooService, args);
//             }
//         });
//         proxy.foo();
//     }
//
//     public static void main(String[] args) throws RunnerException {
//         Options opt = new OptionsBuilder()
//                 .include(ProxyBenchmark.class.getSimpleName())
//                 .forks(1)
//                 .threads(4)
//                 .timeout(new TimeValue(1, TimeUnit.MINUTES))
//                 .warmupIterations(20)
//                 .measurementIterations(20)
//                 .build();
//
//         new Runner(opt).run();
//     }
// }
