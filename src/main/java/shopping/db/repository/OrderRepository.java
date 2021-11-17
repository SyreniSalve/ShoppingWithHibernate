package shopping.db.repository;

import shopping.db.entity.Order;

public class OrderRepository extends SimpleCRUDRepository<Integer, Order>{

    public OrderRepository(){
        super(Order.class);
    }
}
