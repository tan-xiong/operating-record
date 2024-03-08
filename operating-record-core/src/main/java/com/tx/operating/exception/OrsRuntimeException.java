package com.tx.operating.exception;

import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author tanxiong
 * @date 2024/3/8 10:13
 */
public class OrsRuntimeException extends RuntimeException {

    /**
     * the message used to describe the error
     * the errormessage must be clearly , friendly
     * 错误信息，错误信息需要考虑提示对象，必须清晰友好
     * Set 方法作为保护方法
     */
    @Getter
    @Setter(PROTECTED)
    private String errorMsg;

    public OrsRuntimeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    @Override
    public String getMessage() {
        return this.errorMsg;
    }


}
