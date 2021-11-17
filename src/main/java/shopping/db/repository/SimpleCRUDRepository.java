package shopping.db.repository;

import shopping.db.entity.DbEntity;
import java.util.List;

import static shopping.test.DatabaseSessionManager.*;

public class SimpleCRUDRepository<ID, T extends DbEntity<ID>> implements CRUDRepository<ID, T>{

    private final Class<T> entityClass;

    public SimpleCRUDRepository( Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        runInTransaction((entityManager) ->
            entityManager.persist(entity)
        );
    }

    @Override
    public T get(ID id) {
       return withEntityManager(entityManager ->
               entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return withEntityManager(entityManager ->
                entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                .getResultList());
    }

    @Override
    public void delete(T entity) {
        runInTransaction(entityManager ->
                entityManager.remove(entity));
    }
}
