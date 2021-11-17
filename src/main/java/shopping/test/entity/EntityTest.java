package shopping.test.entity;

import shopping.db.entity.DbEntity;

public interface EntityTest<T extends DbEntity<?>> {

    String PREFIX = "===== ";
    String SUFFIX = " =========================";

    T newEntity();

    void saveEntity(T entity);

    void run();
}
