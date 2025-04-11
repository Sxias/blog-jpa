package shop.mtcoding.blog._core.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.error.ex.*;
import shop.mtcoding.blog._core.util.Resp;
import shop.mtcoding.blog._core.util.Script;

@RestControllerAdvice // @ControllerAdvice : return view
public class GlobalExceptionHandler {

    // 400 Bad Request
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e) {
        return Script.back(e.getMessage());
    }

    // 401 Unauthorized
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e) {
        return Script.href(e.getMessage(), "/login-form");
    }

    // 403 Forbidden
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception403 e) {
        return Script.back(e.getMessage());
    }

    // 404 Not Found
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e) {
        return Script.back(e.getMessage());
    }

    // Unknown Server Error
    @ExceptionHandler(Exception.class)
    public String exUnknown(Exception e) {
        System.out.println("Error Log : " + e.getMessage());
        return Script.back("관리자에게 문의해주세요.");
    }

    // 400 Bad Request
    @ExceptionHandler(ExceptionApi400.class)
    public Resp<?> exApi400(ExceptionApi400 e) {
        return Resp.fail(400, e.getMessage());
    }

    // 401 Unauthorized
    @ExceptionHandler(ExceptionApi401.class)
    public Resp<?> exApi401(ExceptionApi401 e) {
        return Resp.fail(401, e.getMessage());
    }

    // 403 Forbidden
    @ExceptionHandler(ExceptionApi403.class)
    public Resp<?> exApi403(ExceptionApi403 e) {
        return Resp.fail(403, e.getMessage());
    }

    // 404 Not Found
    @ExceptionHandler(ExceptionApi404.class)
    public Resp<?> exApi404(ExceptionApi404 e) {
        return Resp.fail(404, e.getMessage());
    }

}
