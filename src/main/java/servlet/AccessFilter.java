package servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import java.io.IOException;

public class AccessFilter implements Filter {

    private static final String REDIRECT_LOGIN = "/views/employee-login.jsp";
    private static final String NAME_USER_SESSION = "user";

    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /** Allow CSS and JS **/
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/css") || path.startsWith("/js")) {
            chain.doFilter(request,response);
            return;
        }

        HttpSession session = request.getSession();

        if (session.getAttribute(NAME_USER_SESSION) == null)
            request.getRequestDispatcher(REDIRECT_LOGIN).forward(request, response);
        else
            chain.doFilter(request,response);
    }

    public void destroy() {}
}