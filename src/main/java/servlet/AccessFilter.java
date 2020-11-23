package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(
        urlPatterns = "/views/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        filterName = "AccessFilter"
)
public class AccessFilter implements Filter {

    private static final String REDIRECT_LOGIN = "/views/employee-login.jsp";
    private static final String NAME_USER_SESSION = "user";

    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        /** Allow CSS and JS **/
        if (path.startsWith("/css") || path.startsWith("/js")) {
            chain.doFilter(request,response);
            return;
        }

        /** Allow login page **/
        if(path.equals(REDIRECT_LOGIN)) {
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