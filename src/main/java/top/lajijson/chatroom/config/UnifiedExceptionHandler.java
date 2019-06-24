package top.lajijson.chatroom.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lajijson.chatroom.entity.base.Result;
import top.lajijson.chatroom.enums.ResultEnum;

import java.util.List;

/**
 * 统一异常处理
 *
 * @author liuwei
 */
@ControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        log.error("捕获异常");
        e.printStackTrace();

        if (e instanceof BindException) {
            List<FieldError> errors = ((BindException) e).getBindingResult().getFieldErrors();
            StringBuilder builder = new StringBuilder();
            errors.forEach(error -> builder.append("字段").append(error.getField()).append(error.getDefaultMessage()).append(";"));
            // 返回400异常，message为校验出的异常
            return Result.failResult(ResultEnum.BAD_REQUEST.getCode(), builder.toString());
        }
        //返回 未知异常
        return Result.failResult();
    }
}
