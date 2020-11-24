package utils;

import model.people.Role;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertUtil {
    /**
     * Convertit une String date en Calendar
     * @param dateString une date au format d'un input type date (yyyy-MM-dd)
     * @return une instance de Calendar à la date correspondante
     * @throws ParseException
     */
    public static Calendar convertDateString(String dateString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();

        date.setTime(formatter.parse(dateString));

        return date;
    }

    public static String convertDateCalendar(Calendar calendar) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(calendar.getTime());
    }

    public static Set<Role> convertArrayStringToSet(String[] listArray){
        return Stream.of(listArray)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
