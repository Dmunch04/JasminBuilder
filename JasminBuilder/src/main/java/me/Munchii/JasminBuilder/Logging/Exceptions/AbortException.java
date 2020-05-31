package me.Munchii.JasminBuilder.Logging.Exceptions;

public class AbortException extends RuntimeException {

    public AbortException() {
        super("Aborted due to previous error(s)!");
    }

}
