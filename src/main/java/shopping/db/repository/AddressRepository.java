package shopping.db.repository;

import shopping.db.entity.Address;
import java.util.UUID;

public class AddressRepository extends SimpleCRUDRepository<UUID, Address>{

    public AddressRepository() {
        super(Address.class);
    }
}
