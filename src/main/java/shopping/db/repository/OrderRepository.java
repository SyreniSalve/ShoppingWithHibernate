package shopping.db.repository;

import shopping.db.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements Repository<Order>{

    private final EntityManager entityManager;
    private ProductRepository productRepository;

    public OrderRepository(EntityManager entityManager, ProductRepository productRepository){
        this.entityManager = entityManager;
        this.productRepository = productRepository;
    }

    @Override
    public void save(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(order);
        transaction.commit();
    }

    @Override
    public List<Order> list() {
        return entityManager.createQuery("FROM Order", Order.class).getResultList();
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(order -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(getById(id));
            transaction.commit();
        });
    }
}
