package com.peterchang.currency_api.common.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.peterchang.currency_api.common.advice.res.APIResponse;
import com.peterchang.currency_api.common.exception.APIException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(APIException.class)
    public APIResponse<Void> handleAllException(APIException e) {
        return APIResponse.fail(e.getErrorCode(), e.getErrorMsg());
    }
}
