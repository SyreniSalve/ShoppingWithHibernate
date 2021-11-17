package shopping.test.entity;

import shopping.db.entity.Product;
import shopping.db.entity.Tag;
import shopping.db.repository.ProductRepository;
import shopping.db.repository.TagRepository;

public class ProductEntityTest extends SimpleEntityTest<Integer, Product> {

    private final ProductRepository repository;
    private final TagRepository tagRepository;

    public ProductEntityTest(ProductRepository repository, TagRepository tagRepository) {
        super(repository);
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Product newEntity() {
        Product product = new Product();
        product.setName("bla bla");
        product.setPrice(9.99);
        repository.save(product);
        return product;
    }

    @Override
    public void runTest() {
        Product product = newEntity();
        product.setName("Fridge");

        Tag bigTag = new Tag();
        bigTag.setName("big");
        product.addTag(bigTag);

        Tag electronicsTag = new Tag();
        electronicsTag.setName("electronics");
        product.addTag(electronicsTag);

        Tag kitchenTag = new Tag();
        kitchenTag.setName("kitchen");
        product.addTag(kitchenTag);

        repository.save(product);

        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.addTag(electronicsTag);
        repository.save(laptop);


        System.out.println("________________________________");
        Product entity = repository.findAll().get(0);
        entity.setName("even newer name");
        repository.save(entity);
        System.out.println("________________________________");
        repository.delete(28);
        System.out.println("________________________________");
        System.out.println(repository.get(8));
        System.out.println(repository.find(100));System.out.println("=====================================");
        product = repository.findAll().get(0);
        System.out.println("Rating: " + repository.getProductRating(product.getId()));
    }

    @Override
    public void runAfter() {
        Tag tag = tagRepository.findByName("electronics");
        System.out.println("Electronics Tag: " + tag);
        tag.getProducts().forEach(System.out::println);
    }
}
