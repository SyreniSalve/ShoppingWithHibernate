package shopping.db.repository;

import shopping.db.entity.DbEntity;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends DbEntity<ID>> {

    /**
     *
     * Create / Update
     */
    void save(T entity);

    /**
     *
     * Read
     */

    T get(ID id);

    default Optional<T> find(ID id){
       return Optional.ofNullable(get(id));
    }

    List<T> findAll();

    /**
     *
     * Delete
     */
    void delete(T entity);

    default void delete(ID id){
        find(id).ifPresent(this::delete);
    }

}
