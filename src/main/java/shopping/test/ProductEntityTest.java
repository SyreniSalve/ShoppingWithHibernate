package shopping.test;

import shopping.db.entity.Product;
import shopping.db.repository.ProductRepository;

public class ProductEntityTest extends SimpleEntityTest<Integer, Product>{

    private final ProductRepository repository;

    public ProductEntityTest(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void runTest() {
        Product newEntity = new Product();
        newEntity.setName("bla bla");
        newEntity.setPrice(9.99);
        repository.save(newEntity);
        System.out.println("________________________________");
        Product entity = repository.findAll().get(0);
        entity.setName("even newer name");
        repository.save(entity);
        System.out.println("________________________________");
        repository.delete(28);
        System.out.println("________________________________");
        System.out.println(repository.get(8));
        System.out.println(repository.find(100));System.out.println("=====================================");
        Product product = repository.findAll().get(0);
        System.out.println("Rating: " + repository.getProductRating(product.getId()));
    }
}
