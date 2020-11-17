package service;

import dao.DAO;

import java.util.List;

public abstract class Service<T> {

    protected DAO<T> dao;

    public Service(DAO<T> customDAO){
        this.dao = customDAO;
    }

    /**
     * Persist the enitity in the database
     * @param entity the entity to create
     */
    public void create(T entity){
        this.dao.startSession();
        this.dao.persist(entity);
        this.dao.closeSession();
    }

    /**
     * Persist all the entities given in parameter, in the database
     * @param entities List of entities to persist
     */
    public void createAll(List<T> entities){
        this.dao.startSession();
        this.dao.persistAll(entities);
        this.dao.closeSession();
    }

    /**
     * Update the entity in the database. If the entity does not exists, then the entity is created
     * @param entity The entity to merge
     */
    public void update(T entity){
        this.dao.startSession();
        this.dao.merge(entity);
        this.dao.closeSession();
    }

    /**
     * Update all the entities given in paramaeter, in the database. If an entity does not exists, then the entity is created
     * @param entities The entities to merge
     */
    public void updateAll(List<T> entities){
        this.dao.startSession();
        this.dao.mergeAll(entities);
        this.dao.closeSession();
    }

    /**
     * Remove an entity from databse
     * @param entity The entity to be removed
     * @return true if the entity is successfully deleted
     */
    public boolean delete(T entity){
        boolean res = false;
        this.dao.startSession();
        res = this.dao.delete(entity);
        this.dao.closeSession();
        return res;
    }

    /**
     * Remove all entities of type T given in parameters, from database
     * @param entities The entities to delete
     * @return The number of entities deleted
     */
    public int deleteAll(List<T> entities){
        int res = 0;
        this.dao.startSession();
        this.dao.deleteAll(entities);
        this.dao.closeSession();
        return res;
    }

    /**
     * Remove all entities of type T from database
     * @return The number of entities deleted
     */
    public int deleteAllEntitiesFromTable(){
        int res = 0;
        this.dao.startSession();
        res = this.dao.deleteAll();
        this.dao.closeSession();
        return res;
    }

    /**
     * Find the entity of type T with the given id
     * @param id Id of the entity
     */
    public T findById(Integer id){
        T res = null;
        this.dao.startSession();
        res = this.dao.findById(id);
        this.dao.closeSession();
        return res;
    }

    /**
     * Find all the entities of type T
     * @return List of entities
     */
    public List<T> findAll(){
        List<T> res = null;
        this.dao.startSession();
        res = this.dao.findAll();
        this.dao.closeSession();
        return res;
    }

    protected abstract DAO<T> getDAO();
}
