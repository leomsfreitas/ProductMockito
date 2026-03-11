import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UpdateProductTest {
    private Product product;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    UpdateProductImp updateProductImp;

    @BeforeEach
    void setUp() {
        product = new Product(1, "Macbook Air", 1000, 5);
    }

    @Test
    @DisplayName("Should update products successfully using Mockito")
    public void shouldUpdateProductSuccessfully() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        updateProductImp.updateProduct(
                new ProductDTO(
                        1,
                        "Macbook Pro",
                        1800,
                        10
                )
        );

        assertThat(product.getName()).isEqualTo("Macbook Pro");
        assertThat(product.getPrice()).isEqualTo(1800);
        assertThat(product.getQuantity()).isEqualTo(10);

        verify(productRepository).save(product);

    }

    @Test
    @DisplayName("Should throw when price is zero (boundary value)")
    void shouldFailWhenPriceIsZero() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                updateProductImp.updateProduct(
                        new ProductDTO(1,"Macbook Neo",0,5)
                ));
    }

    @Test
    @DisplayName("Should throw when price is negative.")
    void shouldFailWhenPriceIsNegative() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        updateProductImp.updateProduct(
                                new ProductDTO(1, "Macbook Neo", -1, 5)
                        ));
    }

    @Test
    @DisplayName("Should throw when quantity is zero.")
    void shouldFailWhenQuantityIsZero() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        updateProductImp.updateProduct(
                                new ProductDTO(1, "Macbook Neo", 10, 0)
                        ));
    }

    @Test
    @DisplayName("Should throw when product does not exists.")
    void shouldFailWhenProductDoesNotExist() {
        when(productRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThatRuntimeException()
                .isThrownBy(() ->
                        updateProductImp.updateProduct(
                                new ProductDTO(99, "Macbook Neo", 10, 5)
                        ));
    }
}
