package shopping.test.entity;

import shopping.db.entity.DbEntity;
import shopping.db.repository.CRUDRepository;
import shopping.test.entity.EntityTest;

public abstract class SimpleEntityTest<ID, T extends DbEntity<ID>> implements EntityTest<T> {

    protected final CRUDRepository<ID, T> repository;

    public SimpleEntityTest(CRUDRepository<ID, T> repository) {
        this.repository = repository;
    }

    public abstract void runTest();

    public void saveEntity(T entity) {
        repository.save(entity);
    }

    public void run(){
        repository.findAll().forEach(System.out::println);
        runTest();
        printAllRecords();
        System.out.println(PREFIX + PREFIX + SUFFIX);
    }

    private void printAllRecords(){
        System.out.println(PREFIX + " All Records " + SUFFIX);
        repository.findAll().forEach(System.out::println);
    }


}
