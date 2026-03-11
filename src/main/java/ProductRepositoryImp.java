import java.util.ArrayList;
import java.util.Optional;

public class ProductRepositoryImp implements ProductRepository {
    private final ArrayList<Product> products;

    public ProductRepositoryImp() {
        this.products = new ArrayList<>();
    }

    public ProductRepositoryImp(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}
