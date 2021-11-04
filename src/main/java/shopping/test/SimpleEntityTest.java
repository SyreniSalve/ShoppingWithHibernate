package shopping.test;

import shopping.db.entity.DbEntity;
import shopping.db.repository.CRUDRepository;

public abstract class SimpleEntityTest<T extends DbEntity> implements EntityTest<T>{

    protected final CRUDRepository<T> repository;

    public SimpleEntityTest(CRUDRepository<T> repository) {
        this.repository = repository;
    }
}
