package shopping.db.repository;

import shopping.db.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ProductRepository extends SimpleCRUDRepository<Product> {

    public ProductRepository(EntityManager entityManager){
        super(entityManager, Product.class);
    }

    public Double getProductRating(int id) {
        return entityManager.createQuery("" +
                        "SELECT AVG(r.rating) " +
                        "FROM Review r " +
                        "JOIN r.product " +
                        "WHERE r.product.id = ?1", Double.class)
                .setParameter(1, id)
                .getSingleResult();
    }
}
