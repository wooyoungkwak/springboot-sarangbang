package com.young.sarangbang.exception;

import lombok.Getter;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */

public class SarangbangException extends RuntimeException{

    @Getter
    private SarangbangExceptionCode sarangbangExceptionCode;

    public SarangbangException(Throwable cause){
        super(cause);
    }

    public SarangbangException(SarangbangExceptionCode code) {
        super(code.getMessage());
        this.sarangbangExceptionCode = code;
    }

    public SarangbangException(SarangbangExceptionCode code, Throwable cause){
        super(code.getMessage(), cause);
        this.sarangbangExceptionCode = code;
    }

    public SarangbangException(String message, Throwable cause) {
        super(message, cause);
        this.sarangbangExceptionCode = SarangbangExceptionCode.NULL;
    }
}
