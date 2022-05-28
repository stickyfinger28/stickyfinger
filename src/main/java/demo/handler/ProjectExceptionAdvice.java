package demo.handler;


import demo.handler.exception.BusinessException;
import demo.util.Result;
import org.omg.CORBA.SystemException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

//@RestControllerAdvice用于标识当前类为REST风格对应的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // @ExceptionHandler用于设置当前处理器类对应的异常类型
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex) {

        return new Result(ex.minor, null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    // 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex) {

        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "系统繁忙，请稍后再试！");

    }

    @ExceptionHandler(AuthenticationException.class)
    public Result doEntry(Exception ex) {

        return new Result(HttpStatus.UNAUTHORIZED.value(), null, "用户认证失败请重新登录");

    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result doDenied(Exception ex) {

        return new Result(HttpStatus.FORBIDDEN.value(), null, "您的权限不足");

    }



    //定义参数异常处理器
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result d(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new Result(HttpStatus.BAD_REQUEST.value(),message);
    }

}

