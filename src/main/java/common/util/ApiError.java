package common.util;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class ApiError {

    private String message;


    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
