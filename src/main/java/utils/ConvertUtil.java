package utils;

import model.people.Role;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertUtil {
    /**
     * Convertit une String date en Calendar
     * @param birthDateString une date au format d'un input type date (yyyy-MM-dd)
     * @return une instance de Calendar à la date correspondante
     * @throws ParseException
     */
    public static Calendar convertDateString(String birthDateString) throws ParseException {
        DateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();

        date.setTime(formatter.parse(birthDateString));

        return date;
    }

    public static Set<Role> convertArrayStringToSet(String[] listArray){
        Set<Role> set = new HashSet<>();
        List<String> list = Arrays.asList(listArray);

        list.stream()
                .map(String::toString)
                .forEach(x->set.add(Role.valueOf(x)));

        return set;
    }
}
