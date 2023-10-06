import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderDto;
import model.TrackDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest {

    private OrderDto orderDto;

    public CreateOrderTest(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new OrderDto("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK", "GRAY"))},
                {new OrderDto("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK"))},
                {new OrderDto("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("GRAY"))},
                {new OrderDto("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of())}
        };
    }


    @Test
    @DisplayName("Create a new order")
    @Description("Successful creation of a new order. Expected response code 201.")
    public void createNewCourierTest() {
        var response = orderHttpClient.createOrder(orderDto);
        response.statusCode(201).and().assertThat().body("track", notNullValue());
        TrackDto trackDto = response.extract().as(TrackDto.class);
        cleanOrderData(trackDto);
    }

}
