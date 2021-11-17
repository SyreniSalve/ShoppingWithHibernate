package shopping;

import shopping.db.entity.Address;
import shopping.db.entity.Order;
import shopping.db.entity.Product;
import shopping.db.entity.User;
import shopping.db.repository.AddressRepository;
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;
import shopping.db.repository.UserRepository;
import shopping.test.*;
import shopping.test.entity.AddressEntityTest;
import shopping.test.entity.OrderEntityTest;
import shopping.test.entity.ProductEntityTest;
import shopping.test.entity.UserEntityTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main implements AutoCloseable{

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Main(){
        userRepository = new UserRepository();
        orderRepository = new OrderRepository();
        addressRepository = new AddressRepository();
        productRepository = new ProductRepository();
    }

    public void runEntityTests(){
        EntityTestProvider entityTestProvider = EntityTestProvider.INSTANCE;
        entityTestProvider.registerEntityTest(Product.class, new ProductEntityTest(productRepository));
        entityTestProvider.registerEntityTest(Order.class, new OrderEntityTest(orderRepository));
        entityTestProvider.registerEntityTest(Address.class, new AddressEntityTest(addressRepository));
        entityTestProvider.registerEntityTest(User.class, new UserEntityTest(userRepository));
        EntityTestRunner.runTests();
    }

    public static void main(String[] args) {
        try(Main m = new Main()){
            m.runEntityTests();
        }
    }

    @Override
    public void close(){
        DatabaseSessionManager.closeSession();
    }
}
