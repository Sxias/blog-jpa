package shop.mtcoding.blog._core.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.error.ex.Exception401;
import shop.mtcoding.blog._core.error.ex.ExceptionApi401;
import shop.mtcoding.blog._core.util.Resp;
import shop.mtcoding.blog._core.util.Script;
import shop.mtcoding.blog.user.User;

import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            if (uri.contains("/api")) {
                throw new ExceptionApi401("인증이 필요합니다.");
//                response.setStatus(401);
//                response.setHeader("Content-Type", "application/json");
//                PrintWriter out = response.getWriter();
//                Resp<?> resp = Resp.fail(401, "인증이 필요합니다");
//                ObjectMapper mapper = new ObjectMapper();
//                String responseBody = mapper.writeValueAsString(resp);
//                out.println(responseBody);
//                return false;
            } else {
                throw new Exception401("인증이 필요합니다.");
//                response.setStatus(401);
//                response.setHeader("Content-Type", "text/html");
//                PrintWriter out = response.getWriter();
//                out.println(Script.href("인증이 필요합니다", "/login-form"));
//                return false;
            }
        }

        return true;
    }
}