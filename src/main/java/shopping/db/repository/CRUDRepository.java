package shopping.db.repository;

import shopping.db.entity.DbEntity;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T extends DbEntity> {

    /**
     *
     * Create / Update
     */
    void save(T entity);

    /**
     *
     * Read
     */

    T get(int id);

    default Optional<T> find(int id){
       return Optional.ofNullable(get(id));
    }

    List<T> findAll();

    /**
     *
     * Delete
     */
    void delete(T entity);

    default void delete(int id){
        find(id).ifPresent(this::delete);
    }

}
