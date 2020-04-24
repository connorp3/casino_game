package ooga.view.data;

/***
 * An exception thrown when a resource file is misconfigured
 * @author Connor Penny
 */
public class ResourcesException extends RuntimeException  {

    public ResourcesException(String message) {
        super(message);
    }

    public ResourcesException(String message, Throwable cause) {
        super(message, cause);
    }
}

