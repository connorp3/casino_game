package ooga.view.data;

/***
 * An exception thrown when reflection files are misconfigured
 * @author Connor Penny
 */
public class ReflectionException extends RuntimeException  {

    public ReflectionException(String message) {
        super(message);
    }

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
