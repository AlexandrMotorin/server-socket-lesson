package org.motorin.serverSocket.lesson.filter;

import lombok.SneakyThrows;
import org.motorin.serverSocket.lesson.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.motorin.serverSocket.lesson.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGES, LOCALE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if(isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            reject(servletRequest, servletResponse);
        }

    }

    @SneakyThrows
    private void reject(ServletRequest servletRequest, ServletResponse servletResponse) {
        var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
        ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto)((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }


}
