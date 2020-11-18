package dao;

import model.people.Person;

public class PersonDAO extends DAO<Person> {

    public PersonDAO() {
        super(Person.class);
    }

    /**
     * Find a Person with their email
     * @param email Email of the Person
     * @return The Person (Must be a child of Person)
     */
    public Person findByEmail(String email){
        // Utilisation de HQL pour faire des requêtes
        Person res = (Person) session.createQuery("from Person p where p.email = :email")
                .setParameter("email", email)
                .setMaxResults(1)
                .setFirstResult(0)
                .uniqueResult();

        return res;
    }

}
