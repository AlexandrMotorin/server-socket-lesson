package org.motorin.serverSocket.lesson.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(
        servletNames = {
        "RegistrationServlet"
        },
        initParams = {
        @WebInitParam(name = "param1", value = "value1")
        }
)
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
