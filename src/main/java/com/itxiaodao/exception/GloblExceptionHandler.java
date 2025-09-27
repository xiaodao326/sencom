package com.itxiaodao.exception;

import com.itxiaodao.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GloblExceptionHandler {

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return Result.error(4001, "空指针异常，请检查请求参数或对象是否为空");
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        return Result.error(4002, StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "参数不合法");
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.error(5000, StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "运行时异常");
    }

    /**
     * 兜底：处理所有未明确处理的异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(500, StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "系统内部错误，请联系管理员");
    }
}
