package com.marketplace.project.v1.ex;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class ProjectCreateException extends RuntimeException {

    public ProjectCreateException(String message) {
        super(message);
    }

    public ProjectCreateException(Throwable cause) {
        super(cause);
    }
}
