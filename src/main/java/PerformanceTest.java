package main.java;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PerformanceTest {
    private static List<Object> arrayList;
    private static List<Object> linkedList;

    private static final int count = 100_000_0;

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Setup
    public static void setup() {
        arrayList = new ArrayList<>(count);
        linkedList = new LinkedList<>();

        for (int i = 0; i < count; i++)
            arrayList.add(new Object());

        linkedList.addAll(arrayList);
    }

/*    @Benchmark
    public static void setupArray() {
        arrayList = new ArrayList<>(count);


        for (int i = 0; i < count; i++)
            arrayList.add(new Object());

    }
    @Benchmark
    public static void setupLink() {

        linkedList = new LinkedList<Object>();

        for (int i = 0; i < count; i++)
            linkedList.add(new Object());


    }*/
    @Benchmark
    public static void getLink() {
        linkedList.get(999999);
    }
    @Benchmark
    public static void getArray() {
        arrayList.get(999999);

    }
    @Benchmark
    public static void addLink() {
        linkedList.add(50000,new Object());
    }
    @Benchmark
    public static void addArray() {
        arrayList.add(50000,new Object());

    }
    @Benchmark
    public static void addFirstLink() {
        linkedList.add(0,new Object());
    }
    @Benchmark
    public static void addFirstArray() {
        arrayList.add(0,new Object());
    }
    @Benchmark
    public static void addLastLink() {
        linkedList.add(count,new Object());
    }
    @Benchmark
    public static void addLastArray() {
        arrayList.add(count,new Object());
    }


    public void removeFromLinkedList(Blackhole blackhole) throws Exception {
        Object object = new Object();
        linkedList.remove(count / 2);
        linkedList.add(count / 2, object);
    }


    public void removeFromArrayList(Blackhole blackhole) throws Exception {
        Object object = new Object();
        arrayList.remove(count / 2);
        arrayList.add(count / 2, object);
    }
}