package shopping.test;

import shopping.db.entity.Address;
import shopping.db.repository.AddressRepository;

import java.util.UUID;

public class AddressEntityTest extends SimpleEntityTest<UUID, Address> {

    public AddressEntityTest(AddressRepository repository) {
        super(repository);
    }

    @Override
    public void runTest() {
        Address address = new Address();
        address.setName("Home");
        address.setCountry("Lithuania");
        address.setCity("Gargždai");
        address.setState("Gargždų raj.");
        address.setStreet1("Vėjų g. 1-2");
        address.setStreet2("D/K 123");
        address.setPost("54321");
        repository.save(address);


    }
}
