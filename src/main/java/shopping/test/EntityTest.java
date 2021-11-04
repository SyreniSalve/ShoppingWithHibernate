package shopping.test;

import shopping.db.entity.DbEntity;

public interface EntityTest<T extends DbEntity> {
    void run();
}
