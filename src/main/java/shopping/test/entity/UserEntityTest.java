package shopping.test.entity;

import shopping.db.entity.Address;
import shopping.db.entity.Product;
import shopping.db.entity.User;
import shopping.db.entity.WishList;
import shopping.db.repository.UserRepository;
import shopping.test.DatabaseSessionManager;
import shopping.test.EntityTestProvider;

import java.util.UUID;

public class UserEntityTest extends SimpleEntityTest<UUID, User> {


    public UserEntityTest(UserRepository repository) {
        super(repository);
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

    @Override
    public void runTest() {
        User user = newEntity();

        EntityTest<Address> addressEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Address.class);
        Address address1 = addressEntityTest.newEntity();
        user.addAddress(address1);

        Address address2 = addressEntityTest.newEntity();
        address2.setName("Work");
        user.addAddress(address2);

        WishList wishList = new WishList();
        wishList.setName("gifts");
        wishList.setPublic(true);
        user.addWishList(wishList);

        EntityTest<Product> productEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Product.class);
        Product product1 = productEntityTest.newEntity();
        wishList.addProduct(product1);

        Product product2 = productEntityTest.newEntity();
        wishList.addProduct(product2);

        repository.save(user);
        System.out.println("Found User: " + repository.find(user.getId()));

        createsMultipleUsersInOneTransactionWithRollback();
    }

    public void createsMultipleUsersInOneTransactionWithRollback(){
        DatabaseSessionManager.runInTransaction(entityManager -> {
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            throw new RuntimeException("something bad happened!");
        });
    }
}
