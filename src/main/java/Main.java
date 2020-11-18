import dao.DAO;
import model.people.Customer;
import model.people.Employee;
import model.people.Person;
import model.people.Role;
import service.CustomerService;
import service.PersonService;
import service.Service;
import utils.HibernateUtil;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        CustomerService customerService = new CustomerService();
        PersonService personService = new PersonService();

        // Choups le iencli
        Customer c = new Customer("Choupault", "Alexis", "alexis.choupault@gmail.com", new GregorianCalendar(1998,8,18));

        //Employé avec adresse email invalide
        Employee e = new Employee("test", "test","  ", new GregorianCalendar(1999,1,1), new HashSet<Role>(Collections.singleton(Role.ClientManager)) ,"test", "test");

        Set<ConstraintViolation<Employee>> violations =  HibernateUtil.getValidator().validate(e);

        violations.stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);

        if(violations.isEmpty()) personService.createAll(Arrays.asList(c, e));
        else customerService.create(c);

        // Choups est en bdd


        //On voit que "id" est rempli
        System.out.println("ID: "+c.getId());

        //Utilisation de java 8, le premier que je vois faire des for(...){} je lui casse les dents
        //On affiche la classe des objets <Person> trouvés en bdd, ici cela devrait afficher "Customer" pour choups
        personService.findAll()
                .stream()
                .map(Person::toString)
                .forEach(System.out::println);

        // Ici l'objet renvoyé est Person car un employé et un client ont tout les deux une email
        Person employeeOrCustomer = personService.findByEmail("alexis.choupault@gmail.com");
        //On détermine donc si c'est un employé ou un client et on cast après....
        if(employeeOrCustomer instanceof Customer) System.out.println("Choups est Customer");
        if(employeeOrCustomer instanceof Employee) System.out.println("Choups est Employee");

        //Truncate table
        if(personService.deleteAllEntitiesFromTable() > 0 && personService.findAll().isEmpty()) {
            System.out.println("Tous les objets ont été supprimés");
        }
    }
}
