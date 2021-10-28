package shopping.db.repository;

import shopping.db.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends DbEntity> {

    void save(T entity);

    List<T> list();

    default T getById(int id){
        return findById(id).orElse(null);
    }

    Optional<T> findById(int id);

    void delete(int id);

    default void delete(T entity){
        delete(entity.getId());
    }
}
