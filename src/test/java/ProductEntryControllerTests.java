import config.ControllerTestsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import system.fullfillment.dto.requests.DeleteProductEntryByIdRequest;
import system.fullfillment.dto.requests.NewProductEntryRequest;
import system.fullfillment.dto.requests.UpdateProductEntryRequest;
import system.fullfillment.exceptions.InvalidRequestException;
import system.fullfillment.mvc.controllers.ProductEntryController;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ApplicationRunner.class)
@ContextConfiguration(classes = ControllerTestsConfig.class)
public class ProductEntryControllerTests {

    private final static Long VALID_ID = 1L;
    private final static Long INVALID_ID = null;

    private final static String VALID_STR_DATA = "ABUNGA";
    private final static String INVALID_STR_DATA = null;

    private final static Integer VALID_INT_DATA = 1000;
    private final static Integer INVALID_INT_DATA = -1;


    @Autowired
    ProductEntryController controller;

    @Test
    void getByIdTest() {
        assertThrows(InvalidRequestException.class, () -> controller.getById(null));
        assertDoesNotThrow(() -> controller.getById(VALID_ID));
    }

    @Test
    void addNewProductEntryTest() {
        NewProductEntryRequest request = new NewProductEntryRequest(VALID_STR_DATA,
                VALID_STR_DATA, VALID_STR_DATA, VALID_INT_DATA, VALID_INT_DATA);
        assertDoesNotThrow(() -> controller.addNewProductEntry(request));

        request.setProductId(INVALID_STR_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.addNewProductEntry(request));
        request.setProductId(VALID_STR_DATA);

        request.setFc(INVALID_STR_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.addNewProductEntry(request));
        request.setFc(VALID_STR_DATA);

        request.setStatus(INVALID_STR_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.addNewProductEntry(request));
        request.setStatus(VALID_STR_DATA);

        request.setValue(INVALID_INT_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.addNewProductEntry(request));
        request.setValue(VALID_INT_DATA);
    }

    @Test
    void deletingTest() {
        DeleteProductEntryByIdRequest request = new DeleteProductEntryByIdRequest(VALID_ID);
        assertDoesNotThrow(() -> controller.deleteProductEntryById(request));

        request.setDeletingId(INVALID_ID);
        assertThrows(InvalidRequestException.class,
                () -> controller.deleteProductEntryById(request));
    }

    @Test
    void updateTest() {
        UpdateProductEntryRequest request = new UpdateProductEntryRequest(
                VALID_ID,
                VALID_STR_DATA,
                VALID_STR_DATA);
        assertDoesNotThrow(() -> controller.updateProductByColumnName(request));

        request.setId(INVALID_ID);
        assertThrows(InvalidRequestException.class,
                () -> controller.updateProductByColumnName(request));
        request.setId(VALID_ID);

        request.setUpdatingColumn(INVALID_STR_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.updateProductByColumnName(request));
        request.setUpdatingColumn(VALID_STR_DATA);

        request.setNewValue(INVALID_STR_DATA);
        assertThrows(InvalidRequestException.class,
                () -> controller.updateProductByColumnName(request));
        request.setNewValue(VALID_STR_DATA);
    }

    @Test
    void getAllByProductIdTest() {
        assertDoesNotThrow(() -> controller.getAllByProductId(VALID_STR_DATA));
        assertThrows(InvalidRequestException.class,
                () -> controller.getAllByProductId(INVALID_STR_DATA));
    }

    @Test
    void getAllByStatusTest() {
        assertDoesNotThrow(() -> controller.getAllByProductStatus(VALID_STR_DATA));
        assertThrows(InvalidRequestException.class,
                () -> controller.getAllByProductStatus(INVALID_STR_DATA));
    }

    @Test
    void getAllByFCTest(){
        assertDoesNotThrow(() -> controller.getAllByFulfillmentCenter(VALID_STR_DATA));
        assertThrows(InvalidRequestException.class,
                () -> controller.getAllByFulfillmentCenter(INVALID_STR_DATA));
    }
}
