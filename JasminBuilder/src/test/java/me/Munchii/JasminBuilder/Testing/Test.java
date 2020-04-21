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

        for (TestCase Case : TestCases)
        {
            System.out.println ("--- Running test: " + Case.GetName () + " ---\n");
            Case.Run ();
            System.out.println ("--- - ---");
        }
    }

}
