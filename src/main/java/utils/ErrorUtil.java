package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class ErrorUtil {

    public static void sendError(HttpServletResponse resp, String resultTag, String result, String errorTag, HashSet<String> errors) throws IOException {
        JSONObject resultJson = new JSONObject();
        resultJson.put(resultTag, result);

        JSONArray errorJsonArray = new JSONArray();
        errors.forEach(errorJsonArray::put);

        resultJson.put(errorTag, errorJsonArray);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(400);

        PrintWriter out = resp.getWriter();
        out.write(resultJson.toString());
        out.flush();
        out.close();
    }

}
