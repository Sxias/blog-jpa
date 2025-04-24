package shop.mtcoding.blog._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.user.UserRepository;

import java.io.IOException;

@RequiredArgsConstructor
public class LogFilter implements Filter {

    private final UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String uri = req.getRequestURI();
        String ip = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");

        System.out.printf("[로그] %s | IP: %s | UA: %s\n", uri, ip, userAgent);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}