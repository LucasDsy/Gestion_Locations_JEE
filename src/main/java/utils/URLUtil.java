package utils;

public class URLUtil {
    private static final String baseUrl = "http://localhost:8080/LocaJee/";

    public static String baseUrl(String route) {
        return baseUrl + route.replace("/", "");
    }
}