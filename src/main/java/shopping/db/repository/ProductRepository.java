package shopping.db.repository;

import shopping.db.entity.Product;
import static shopping.test.DatabaseSessionManager.*;

public class ProductRepository extends SimpleCRUDRepository<Integer, Product> {

    public ProductRepository(){
        super(Product.class);
    }

    public Double getProductRating(int id) {
        return withEntityManager(entityManager ->
                entityManager.createQuery("" +
                                "SELECT AVG(r.rating) " +
                                "FROM Review r " +
                                "JOIN r.product " +
                                "WHERE r.product.id = ?1", Double.class)
                        .setParameter(1, id)
                        .getSingleResult());
    }
}
