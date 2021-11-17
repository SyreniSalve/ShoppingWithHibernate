package shopping.test.entity;

import shopping.db.entity.DbEntity;

public interface EntityTest<T extends DbEntity<?>> {

    T newEntity();

    void saveEntity(T entity);

    default void runBefore(){}

    void run();

    default void runAfter(){}
}
