package shopping.test.entity;

import shopping.db.entity.Address;
import shopping.db.entity.User;
import shopping.db.repository.UserRepository;
import shopping.test.EntityTestProvider;

import java.util.UUID;

public class UserEntityTest extends SimpleEntityTest<UUID, User> {


    public UserEntityTest(UserRepository repository) {
        super(repository);
    }

    @Override
    public void runTest() {
        User user = newEntity();

        EntityTest<Address> addressEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Address.class);
        Address address1 = addressEntityTest.newEntity();
        user.addAddress(address1);

        Address address2 = addressEntityTest.newEntity();
        address2.setName("Work");
        user.addAddress(address2);

        repository.save(user);
        System.out.println("Found User: " + repository.find(user.getId()));
    }

    @Override
    public User newEntity() {
        User user = new User();
        user.setFirstName("Vardenis");
        user.setLastName("Pavardenis");
        user.setUsername("vardenis");
        user.setPassword("1235");
        return user;
    }
}
