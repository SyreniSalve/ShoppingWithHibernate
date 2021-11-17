package shopping.db.repository;

import shopping.db.entity.User;
import shopping.db.entity.UserId;

import javax.persistence.EntityManager;

public class UserRepository extends SimpleCRUDRepository<UserId, User>{

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
