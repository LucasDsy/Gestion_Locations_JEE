package filter;

import model.people.Employee;
import model.people.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static servlet.EmployeeLoginServlet.NAME_USER_SESSION;

@WebFilter(
        urlPatterns = {"/customer"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        filterName = "CustomerFilter"
)
public class CustomerFilter extends HttpFilter {

    public void init(FilterConfig config) {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Employee employee = (Employee) request.getSession().getAttribute(NAME_USER_SESSION);

        if(employee != null && (employee.getRoles().contains(Role.Administrator) || employee.getRoles().contains(Role.ClientManager))) {
            chain.doFilter(request,response);
            return;
        }
        response.sendError(response.SC_FORBIDDEN);
    }

    public void destroy() {}

}
