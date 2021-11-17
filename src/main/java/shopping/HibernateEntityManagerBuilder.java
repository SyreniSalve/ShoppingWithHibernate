package shopping;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import shopping.db.entity.*;

import javax.persistence.EntityManager;

public class HibernateEntityManagerBuilder {

    public static EntityManager build(){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Order.class);
        metadataSources.addAnnotatedClass(Review.class);
        metadataSources.addAnnotatedClass(Product.class);
        metadataSources.addAnnotatedClass(OrderItem.class);
        metadataSources.addAnnotatedClass(Address.class);
        metadataSources.addAnnotatedClass(WishList.class);
        metadataSources.addAnnotatedClass(Tag.class);

        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();


        return sessionFactory.createEntityManager();
    }
}
