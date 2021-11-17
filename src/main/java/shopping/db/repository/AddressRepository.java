package shopping.db.repository;

import shopping.db.entity.Address;

import javax.persistence.EntityManager;
import java.util.UUID;

public class AddressRepository extends SimpleCRUDRepository<UUID, Address>{

    public AddressRepository(EntityManager entityManager) {
        super(entityManager, Address.class);
    }
}
