package shopping;

import shopping.db.repository.AddressRepository;
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;
import shopping.db.repository.UserRepository;
import shopping.test.*;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Main(){

        entityManager = HibernateEntityManagerBuilder.build();
        userRepository = new UserRepository(entityManager);
        orderRepository = new OrderRepository(entityManager);
        addressRepository = new AddressRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runEntityTests(){
        EntityTestRunner.runTests(
                new ProductEntityTest(productRepository),
                new OrderEntityTest(orderRepository, productRepository),
                new AddressEntityTest(addressRepository),
                new UserEntityTest(userRepository));
    }

    public static void main(String[] args) {
        try(Main m = new Main()){
            m.runEntityTests();
        }
    }

    @Override
    public void close(){
        entityManager.close();
    }
}
