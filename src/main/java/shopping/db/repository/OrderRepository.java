package shopping.db.repository;

import shopping.db.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends SimpleCRUDRepository<Integer, Order>{

    public OrderRepository(EntityManager entityManager){
        super(entityManager, Order.class);
    }
}
