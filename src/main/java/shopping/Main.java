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

import javax.persistence.EntityManager;

public class Main implements AutoCloseable{

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Main(){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Order.class);
        metadataSources.addAnnotatedClass(Review.class);
        metadataSources.addAnnotatedClass(Product.class);
        metadataSources.addAnnotatedClass(OrderItem.class);

        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();


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
        Product entity = productRepository.findAll().get(0);
        entity.setName("even newer name");
        productRepository.save(entity);
        System.out.println("________________________________");
        productRepository.delete(28);
        productRepository.findAll().forEach(System.out::println);
        System.out.println("________________________________");
        System.out.println(productRepository.get(8));
        System.out.println(productRepository.find(100));
    }

    public void runOrders(){
        System.out.println("======================================");
        System.out.println("======================================");
        Order entity = new Order();
        productRepository.findAll().stream()
                .limit(3)
                .forEach(product -> entity.addItems(product,5));
        productRepository.findAll().stream()
                .limit(3)
                .forEach(product -> entity.addItems(product,2));
        orderRepository.save(entity);
        orderRepository.findAll().forEach(System.out::println);
        orderRepository.delete(100);

        Order order = orderRepository.findAll().get(0);
        OrderItem orderItem = order.getItems().get(0);
        Product product = orderItem.getProduct();

        product.addReview(newReview(orderItem, 7, "meh"));
        product.addReview(newReview(orderItem, 4, "meh"));
        product.addReview(newReview(orderItem, 10, "meh"));
        product.addReview(newReview(orderItem, 1, "meh"));
        productRepository.save(product);
        System.out.println("=====================================");
        productRepository.findAll().forEach(System.out::println);
        System.out.println("=====================================");
        System.out.println("Rating: " + productRepository.getProductRating(product.getId()));
    }

    private Review newReview(OrderItem orderItem, int rating, String comment){
        Review review = new Review();
        review.setOrderItem(orderItem);
        review.setRating(rating);
        review.setReview(comment);
        return review;
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
