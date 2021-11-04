package shopping;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import shopping.db.entity.Order;
import shopping.db.entity.OrderItem;
import shopping.db.entity.Product;
import shopping.db.entity.Review;
import shopping.db.repository.AddressRepository;
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;
import shopping.test.AddressEntityTest;
import shopping.test.EntityTestRunner;
import shopping.test.OrderEntityTest;
import shopping.test.ProductEntityTest;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    public Main(){

        entityManager = HibernateEntityManagerBuilder.build();
        orderRepository = new OrderRepository(entityManager);
        addressRepository = new AddressRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runEntityTests(){
        EntityTestRunner.runTests(
                new ProductEntityTest(productRepository),
                new OrderEntityTest(orderRepository, productRepository),
                new AddressEntityTest(addressRepository));
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
