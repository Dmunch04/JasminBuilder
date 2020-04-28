package me.Munchii.JasminBuilder.Logging;

public class Logger
{

    public static void Tip (String Message)
    {
        System.out.println (ColorCode.BoldGreen + "TIP " + ColorCode.Reset + ColorCode.Green + Message + ColorCode.Reset);
    }

    public static void Debug (String Message)
    {
        System.out.println (ColorCode.BoldBlue + "DEBUG " + ColorCode.Reset + ColorCode.Blue + Message + ColorCode.Reset);
    }

    public static void Warning (String Message)
    {
        System.out.println (ColorCode.BoldYellow + "WARNING " + ColorCode.Reset + ColorCode.Yellow + Message + ColorCode.Reset);
    }

    public static void Error (String Message)
    {
        System.out.println (ColorCode.BoldRed + "ERROR " + ColorCode.Reset + ColorCode.Red + Message + ColorCode.Reset);
    }

}
