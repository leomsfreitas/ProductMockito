import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UpdateProductTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    UpdateProductImp updateProductImp;

    @Test
    @DisplayName("Should update products successfully using Mockito")
    public void shouldUpdateProductSuccessfully() {

        final Product product = new Product(1, "Macbook Air", 1000, 5);
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
}
