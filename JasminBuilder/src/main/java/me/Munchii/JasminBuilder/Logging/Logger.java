package me.Munchii.JasminBuilder.Logging;

import java.util.concurrent.TimeUnit;

public class Logger {

    public static void tip(String s) {
        log(ColorCode.BOLD_GREEN + "TIP " + ColorCode.RESET + ColorCode.GREEN + s + ColorCode.RESET);
    }

    public static void debug(String s) {
        log(ColorCode.BOLD_BLUE + "DEBUG " + ColorCode.RESET + ColorCode.BLUE + s + ColorCode.RESET);
    }

    public static void warning(String s) {
        log(ColorCode.BOLD_YELLOW + "WARNING " + ColorCode.RESET + ColorCode.YELLOW + s + ColorCode.RESET);
    }

    public static void error(String s) {
        log(ColorCode.BOLD_RED + "ERROR " + ColorCode.RESET + ColorCode.RED + s + ColorCode.RESET);
    }

    public static void log(String s) {
        System.out.println(s);

        //* We wait because else when we throw an abort exception, the error will be sent afterwards
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
