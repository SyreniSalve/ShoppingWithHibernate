package shopping;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import shopping.db.entity.Order;
import shopping.db.entity.OrderItem;
import shopping.db.entity.Product;
import shopping.db.repository.OrderRepository;
import shopping.db.repository.ProductRepository;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Main(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(OrderItem.class)
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        orderRepository = new OrderRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runProducts(){
        Product newEntity = new Product();
        newEntity.setName("bla bla");
        newEntity.setPrice(9.99);
        productRepository.save(newEntity);
        System.out.println("________________________________");
        Product entity = productRepository.list().get(0);
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
        productRepository.list().stream()
                .limit(3)
                .forEach(product -> entity.addItems(product,5));
        productRepository.list().stream()
                .limit(3)
                .forEach(product -> entity.addItems(product,2));
        orderRepository.save(entity);
        orderRepository.list().forEach(System.out::println);
        orderRepository.delete(100);
    }

    public static void main(String[] args) {
        try(Main m = new Main()){
            m.runProducts();
            m.runOrders();
        }
    }

    @Override
    public void close(){
        entityManager.close();
    }
}
