package shop.mtcoding.blog._core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog._core.error.ex.Exception400;

import java.util.List;

// Aspect, Pointcut, Advice

@Aspect
@Component
public class GlobalValidationHandler {
    // 관심사를 분리

    // PostMapping 혹은 PutMapping이 붙어 있는 메서드를 실행하기 직전에 Advice 호출
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void badRequestAdvice(JoinPoint jp) { // 실행될 실제 메서드의 모든 것을 투영 (Reflect)
        Object[] args = jp.getArgs(); // 메서드의 매개변수들 (배열로 리턴)
        for (Object arg : args) { // 매개변수 갯수만큼 (Annotation 제외)
            // execute when method's args have Errors
            if (arg instanceof Errors) {
                System.out.println("Exception400 처리 필요");
                Errors errors = (Errors) arg; // Downcasting
                // execute when Errors.size() > 0 -> errors exist
                if (errors.hasErrors()) {
                    // gather errors
                    List<FieldError> fErrors = errors.getFieldErrors();

                    // alert users (
                    for (FieldError fieldError : fErrors) {
                        throw new Exception400(fieldError.getField() + " : " + fieldError.getDefaultMessage());
                    }
                }
            }
        }
    }
}
