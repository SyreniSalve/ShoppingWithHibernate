package shopping.test;

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
        repository.save(user);
        System.out.println("Found User: " + repository.find(userId));
    }
}
