package config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import system.fullfillment.models.ProductEntry;
import system.fullfillment.mvc.controllers.ProductEntryController;
import system.fullfillment.mvc.repositories.ProductEntryJpaRepository;
import system.fullfillment.mvc.services.ProductEntryService;

import java.util.List;

@TestConfiguration
public class ControllerTestsConfig {

    @Bean
    ProductEntryController controller(){
        return new ProductEntryController();
    }

    @Bean
    ProductEntryService service(){
        ProductEntryService result = Mockito.mock(ProductEntryService.class);
        ProductEntry mockEntry = Mockito.mock(ProductEntry.class);

        Mockito.when(result.getProductEntryById(1L)).thenReturn(mockEntry);
        Mockito.when(result.getAllByProductId("ABUNGA")).thenReturn(List.of(mockEntry));
        Mockito.when(result.getAllByProductStatus("ABUNGA")).thenReturn(List.of(mockEntry));
        Mockito.when(result.getAllByFulfillmentCenter("ABUNGA")).thenReturn(List.of(mockEntry));

        return result;
    }

    @Bean
    ProductEntryJpaRepository repository(){
        return Mockito.mock(ProductEntryJpaRepository.class);
    }
}
