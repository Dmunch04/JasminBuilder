package me.Munchii.JasminBuilder.Testing;

import me.Munchii.JasminBuilder.Testing.Tests.FibonacciTest;
import me.Munchii.JasminBuilder.Testing.Tests.HelloWorldTest;

import java.util.ArrayList;
import java.util.List;

public class Test
{

    static List<TestCase> TestCases = new ArrayList<TestCase> ();

    public static void main (String... Args)
    {
        TestCases.add (new HelloWorldTest());
        TestCases.add (new FibonacciTest ());

        long StartTime = System.nanoTime ();

        // TODO: Why does the helloworld test take 42-50ms, while fib takes around 2-3ms?

        for (TestCase Case : TestCases)
        {
            long TestStartTime = System.nanoTime ();
            System.out.println ("--- Running test: " + Case.GetName () + " ---\n");
            Case.Run ();
            System.out.println ("--- - ---");
            System.out.println (Case.GetName () + " total time elapsed: " + ((System.nanoTime () - TestStartTime) / 1000000) + "ms");
            System.out.println ("--- - ---\n");
        }

        System.out.println ("Total time elapsed: " + ((System.nanoTime () - StartTime) / 1000000) + "ms");
    }

}
