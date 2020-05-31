package me.Munchii.JasminBuilder.Testing;

import me.Munchii.JasminBuilder.Testing.Tests.FibonacciTest;
import me.Munchii.JasminBuilder.Testing.Tests.HelloWorldTest;

import java.util.ArrayList;
import java.util.List;

public class Test {

    static List<TestCase> testCases = new ArrayList<>();

    public static void main(String... args) {
        testCases.add(new HelloWorldTest());
        testCases.add(new FibonacciTest());

        long startTime = System.nanoTime();

        // TODO: Why does the helloworld test take 42-50ms, while fib takes around 2-3ms?
        //? I think it's java's fault, because it takes time to startup? Because if the tests get switched, so does the times

        for (TestCase testCase : testCases) {
            long testStartTime = System.nanoTime();
            System.out.println("--- Running test: " + testCase.getName() + " ---\n");
            testCase.run();
            System.out.println("--- - ---");
            System.out.println(testCase.getName() + " total time elapsed: " + ((System.nanoTime() - testStartTime) / 1000000) + "ms");
            System.out.println("--- - ---\n");
        }

        System.out.println("Total time elapsed: " + ((System.nanoTime() - startTime) / 1000000) + "ms");
    }

}
