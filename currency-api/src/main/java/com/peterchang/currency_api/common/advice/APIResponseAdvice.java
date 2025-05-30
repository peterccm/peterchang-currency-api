package com.peterchang.currency_api.common.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.peterchang.currency_api.common.abs.AbstractResBody;
import com.peterchang.currency_api.common.advice.res.APIResponse;

@RestControllerAdvice
public class APIResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
     
        return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
        if (body instanceof APIResponse) {
            return body;
        }
        
        if (!(body instanceof AbstractResBody)) {
        	return body;
        }

        return APIResponse.success(body);
	}

}
