package com.parndt.zehntausend.data;

public class NoDataException extends Exception {

    public NoDataException(String message) {
        super(message);
    }

    public NoDataException(String message, Throwable e) {
        super(message, e);
    }
}
