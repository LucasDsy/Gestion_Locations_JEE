import dao.DAO;
import model.people.Customer;
import model.people.Person;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        DAO<Customer> dao = new DAO<>(Customer.class);

        // Choups le iencli
        Customer c = new Customer("Choupault", "Alexis", "alexis.choupault@gmail.com", new GregorianCalendar(1998,8,18));

        // On oubli pas de démarrer le session
        dao.startSession();

        // l'objet renvoyé contient l'id qui a été créé.
        c = dao.persist(c);

        //Utilisation de java 8, le premier que je vois faire des for(...){} je lui casse les dents
        dao.findAll()
                .stream()
                .map(Customer::toString)
                .forEach(System.out::println);

        if(dao.deleteAll()) {
            System.out.println("Tous les objets ont été supprimés");
        }

        dao.findAll()
                .stream()
                .map(Customer::toString)
                .forEach(System.out::println);

        //et de la fermer
        dao.closeSession();
    }
}
