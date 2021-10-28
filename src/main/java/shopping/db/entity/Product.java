package shopping.db.entity;


import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "products")
public class Product implements DbEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

    private Instant updated = Instant.now();

    private Instant created = Instant.now();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instant getUpdate() {
        return updated;
    }

    public void setUpdate(Instant update) {
        this.updated = update;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", update=" + updated +
                ", created=" + created +
                '}';
    }
}
