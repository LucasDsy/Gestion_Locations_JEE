package filter;

import servlet.EmployeeLoginServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static servlet.EmployeeLoginServlet.LOGIN_VIEW;
import static servlet.HomeServlet.INDEX_VIEW;

@WebFilter(
        urlPatterns = {"/*"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        filterName = "MainFilter"
)
public class MainFilter implements Filter {

    private static final String CSS_PATH = "/css";
    private static final String JS_PATH = "/js";


    public void init(FilterConfig config) {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        request.getServletContext().log(path);

        // Allow CSS and JS
        if (path.startsWith(JS_PATH) || path.startsWith(CSS_PATH)) {
            chain.doFilter(request,response);
            return;
        }

        // Allow login and home page
        if(path.equals("/login") || path.equals("/") || path.equals("/accueil") || path.equals(LOGIN_VIEW) || path.equals(INDEX_VIEW)) {
            request.getServletContext().log("IN : "+path);
            chain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute(EmployeeLoginServlet.NAME_USER_SESSION) == null)
            //request.getServletContext().getRequestDispatcher("/LocaJee/login").forward(request, response);
            response.sendRedirect("/LocaJee/login");
        else
            chain.doFilter(request,response);
    }

    public void destroy() {}
}