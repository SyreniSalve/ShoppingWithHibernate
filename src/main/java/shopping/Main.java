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
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;
import shopping.test.OrderEntityTest;
import shopping.test.ProductEntityTest;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Main(){

        entityManager = HibernateEntityManagerBuilder.build();
        orderRepository = new OrderRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runEntityTests(){
        new ProductEntityTest(productRepository).run();
        new OrderEntityTest(orderRepository, productRepository).run();
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
