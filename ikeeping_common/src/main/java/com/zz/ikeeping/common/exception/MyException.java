package com.zz.ikeeping.common.exception;

import lombok.Data;

@Data
public class MyException extends Exception {
    public MyException() {}

    public MyException(String msg) {
        super(msg);
    }
}
