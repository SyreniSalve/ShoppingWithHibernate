package shopping;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import shopping.db.entity.Order;
import shopping.db.entity.OrderItem;
import shopping.db.entity.Product;
import shopping.db.entity.Review;

import javax.persistence.EntityManager;

public class HibernateEntityManagerBuilder {

    public static EntityManager build(){
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


        return sessionFactory.createEntityManager();
    }
}
