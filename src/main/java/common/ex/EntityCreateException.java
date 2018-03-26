package common.ex;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class EntityCreateException extends RuntimeException {

    public EntityCreateException(String message) {
        super(message);
    }

    public EntityCreateException(Throwable cause) {
        super(cause);
    }
}
