package shopping.test;

import shopping.db.entity.Address;
import shopping.db.entity.User;
import shopping.db.entity.UserId;
import shopping.db.repository.UserRepository;

public class UserEntityTest extends SimpleEntityTest<UserId, User>{

    public UserEntityTest(UserRepository repository) {
        super(repository);
    }

    @Override
    public void runTest() {
        User user = new User();
        UserId userId = new UserId("Vardenis", "Pavardenis");
        user.setId(userId);
        user.setUsername("vardenis");
        user.setPassword("1235");

        Address address1 = AddressEntityTest.newAddress();
        user.addAddress(address1);

        Address address2 = AddressEntityTest.newAddress();
        address2.setName("Work");
        user.addAddress(address2);

        repository.save(user);
        System.out.println("Found User: " + repository.find(userId));
    }
}
