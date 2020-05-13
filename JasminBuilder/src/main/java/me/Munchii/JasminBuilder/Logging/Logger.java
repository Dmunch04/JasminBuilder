package me.Munchii.JasminBuilder.Logging;

import java.util.concurrent.TimeUnit;

public class Logger
{

    public static void Tip (String Message)
    {
        Log (ColorCode.BoldGreen + "TIP " + ColorCode.Reset + ColorCode.Green + Message + ColorCode.Reset);
    }

    public static void Debug (String Message)
    {
        Log (ColorCode.BoldBlue + "DEBUG " + ColorCode.Reset + ColorCode.Blue + Message + ColorCode.Reset);
    }

    public static void Warning (String Message)
    {
        Log (ColorCode.BoldYellow + "WARNING " + ColorCode.Reset + ColorCode.Yellow + Message + ColorCode.Reset);
    }

    public static void Error (String Message)
    {
        Log (ColorCode.BoldRed + "ERROR " + ColorCode.Reset + ColorCode.Red + Message + ColorCode.Reset);
    }

    public static void Log (String Message)
    {
        System.out.println (Message);

        //* We wait because else when we throw an abort exception, the error will be sent afterwards
        try
        {
            TimeUnit.NANOSECONDS.sleep (1);
        }

        catch (InterruptedException Error)
        {
            Error.printStackTrace ();
        }
    }

}
