package shopping.db.repository;

import shopping.db.entity.Address;

import javax.persistence.EntityManager;

public class AddressRepository extends SimpleCRUDRepository<Address>{

    public AddressRepository(EntityManager entityManager) {
        super(entityManager, Address.class);
    }
}
