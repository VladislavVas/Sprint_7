package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderDto;
import model.TrackDto;

import static api.ConstUrl.ORDER_CANCEL;
import static api.ConstUrl.ORDER_URI;

public class OrderHttpClient extends BaseHttpClient {

    private final String url;

    public OrderHttpClient(String baseUrl) {
        super();
        this.url = baseUrl + ORDER_URI;
    }

    @Step("POST request. Create new order.")
    public ValidatableResponse createOrder(OrderDto orderDto) {
        return postRequest(url, orderDto);
    }

    @Step("PUT request. Cancel order.")
    public ValidatableResponse cancelOrder(TrackDto trackDto) {
        return putRequest(url + ORDER_CANCEL, trackDto);
    }

    @Step("GET request. Get order list")
    public ValidatableResponse getOrderList() {
        return getRequest(url);

    }

}
