package com.record.Handler;

import com.record.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result fail(Exception e){
        e.printStackTrace();
        return Result.fail("执行了全局异常处理...");
    }
}
