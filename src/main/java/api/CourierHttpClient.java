package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierDto;
import model.LoginCourierDto;

import static api.ConstUrl.COURIER_URI;
import static api.ConstUrl.LOGIN_COURIER_URI;

public class CourierHttpClient extends BaseHttpClient {

    private final String url;

    public CourierHttpClient(String baseUrl) {
        super();
        this.url = baseUrl + COURIER_URI;
    }

    @Step("POST request. Create courier.")
    public ValidatableResponse createCourier(CourierDto courierDto) {
        return postRequest(url, courierDto);
    }

    @Step("POST request. Log in courier.")
    public ValidatableResponse loginCourier(LoginCourierDto loginCourierDto) {
        return postRequest(url + LOGIN_COURIER_URI, loginCourierDto);
    }

    @Step("DELETE request. Delete courier.")
    public ValidatableResponse deleteCourier(Integer id) {
        return deleteRequest(url + "/" + id);
    }

}
