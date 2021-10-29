package shopping.db.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements DbEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status = "NEW";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    private Instant created = Instant.now();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItems(Product product, Integer quantity){
        OrderItem orderItem = items.stream()
                .filter(items -> product.equals(items.getProduct()))
                .findFirst()
                .orElseGet(() -> {
                    OrderItem newOrderItem = new OrderItem(this, product, 0);
                    items.add(newOrderItem);
                    return newOrderItem;
                });
        orderItem.setQuantity(orderItem.getQuantity() + quantity);
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", items=" + items +
                ", created=" + created +
                '}';
    }
}