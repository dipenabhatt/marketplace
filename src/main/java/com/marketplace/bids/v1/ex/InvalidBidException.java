package com.marketplace.bids.v1.ex;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class InvalidBidException extends RuntimeException {

    public InvalidBidException(String message) {
        super(message);
    }
}
