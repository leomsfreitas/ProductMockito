public class UpdateProductImp implements UpdateProduct{
    private final ProductRepository productRepository;

    public UpdateProductImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProduct(ProductDTO request) {
        Product product = productRepository.findById(request.id())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (request.name() == null || request.name().isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        if (request.price() <= 0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }

        if (request.quantity() <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }

        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());

        productRepository.save(product);
    }
}
