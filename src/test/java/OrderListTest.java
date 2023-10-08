import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class OrderListTest extends BaseTest {

    @Test
    @DisplayName("Get order list")
    @Description("Order list can be gotten. Expected response code 200.")
    public void getOrdersListTest() {
        var response = orderHttpClient.getOrderList();
        response.assertThat().statusCode(200).and().assertThat().body("orders", hasSize(greaterThan(0)));
    }

}
