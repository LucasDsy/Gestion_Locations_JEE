package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


// Pour des requêtes spécifiques, il faudra étendre cette classe
public class DAO<T> {

    private Session session;
    final private Class<T> typeParameterClass;

    public DAO(Class<T> typeParameterClass){
        this.typeParameterClass = typeParameterClass;
    }

    public T persist(T entity) {
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return entity;
    }

    public List<T> persistAll(List<T> entities) {
        try {
            session.beginTransaction();
            entities.forEach(session::persist);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return entities;
    }

    public T merge(T entity) {
        try {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return entity;
    }

    public List<T> mergeAll(List<T> entities) {
        try {
            session.beginTransaction();
            entities.forEach(session::merge);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return entities;
    }

    public T findById(Integer id) {
        T res = session.get(typeParameterClass, id);
        return res;
    }

    public List<T> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeParameterClass);
        Root<T> rootEntry = cq.from(typeParameterClass);
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public boolean delete(T entity) {
        try {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean deleteAll() {
        try {
            session.beginTransaction();
            findAll().forEach(session::remove);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public void startSession() {
        this.session = HibernateUtil.getSession();
    }

    public void closeSession() {
        this.session.close();
    }

}
