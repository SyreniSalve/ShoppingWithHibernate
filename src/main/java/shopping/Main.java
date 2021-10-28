package shopping;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import shopping.db.entity.Order;
import shopping.db.entity.Product;
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;
import shopping.exception.ShoppingException;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Main(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        orderRepository = new OrderRepository(entityManager, productRepository);
        productRepository = new ProductRepository(entityManager);
    }

    public void runProducts(){
        Product newEntity = new Product();
        newEntity.setName("bla bla");
        newEntity.setPrice(9.99);
        productRepository.save(newEntity);
        System.out.println("________________________________");
        Product entity = productRepository.getById(8);
        entity.setName("even newer name");
        productRepository.save(entity);
        System.out.println("________________________________");
        productRepository.delete(28);
        productRepository.list().forEach(System.out::println);
        System.out.println("________________________________");
        System.out.println(productRepository.getById(8));
        System.out.println(productRepository.findById(100));
    }

    public void runOrders(){
        System.out.println("======================================");
        System.out.println("======================================");
        Order entity = new Order();
//        productRepository.list().stream()
//                .limit(3)
//                .forEach(product -> entity.addItems(product,5));
//        orderRepository.save(entity);
        orderRepository.list().forEach(System.out::println);
        orderRepository.delete(100);
    }

    public static void main(String[] args) {
        try(Main m = new Main()){
            m.runProducts();
            m.runOrders();
        }
    }

//    private static Properties loadDatabaseProperties(){
//        try (InputStream is = ClassLoader.getSystemResourceAsStream("database.properties")){
//            Properties properties = new Properties();
//            properties.load(is);
//            return properties;
//        }catch (IOException e){
//            throw new ShoppingException(e);
//        }
//    }

    @Override
    public void close(){
        entityManager.close();
    }
}
