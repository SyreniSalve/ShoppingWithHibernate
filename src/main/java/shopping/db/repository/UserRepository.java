package shopping.db.repository;

import shopping.db.entity.User;
import java.util.UUID;

public class UserRepository extends SimpleCRUDRepository<UUID, User>{

    public UserRepository() {
        super(User.class);
    }
}
