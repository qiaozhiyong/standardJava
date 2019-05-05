package com.qiaozhy.standardjava.controller;

import com.qiaozhy.standardjava.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:43 PM
 */
@ControllerAdvice
@RestController
@Slf4j
public class ExceptionController {
    @Autowired
    private HttpServletRequest httpServletRequest;

    /*@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleGlobalException(Exception e) {
        return JsonResult.buildError();
    }

    @ExceptionHandler(UserNotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResult handleUserNotLoginException(UserNotLoginException e){
        log.warn("[UserNotLoginException]url={},error={}", httpServletRequest.getRequestURL(), e);
        return JsonResult.build(CommonSysResponseCode.unauthorized);
    }*/

    /**
     * Handle exceptions thrown by handlers.
     */
    // TODO: 2019/4/25 ResponseEntity
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDTO> exception(Exception e, HttpServletResponse response) {
        log.error("[exception][global_exception]url={},error={}", httpServletRequest.getRequestURL(), e);
        ErrorDTO errorDTO = new ErrorDTO();
        if( e instanceof ApiException){//api异常
            ApiException apiException = (ApiException)e;
            errorDTO.setErrorCode(apiException.getErrorCode());
        }else{//未知异常
            errorDTO.setErrorCode(0L);
        }
        errorDTO.setTip(e.getMessage());
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO,HttpStatus.valueOf(response.getStatus()));
        return responseEntity;
    }

    @Setter
    @Getter
    @ToString
    class ErrorDTO{
        private Long errorCode;
        private String tip;
    }
}
