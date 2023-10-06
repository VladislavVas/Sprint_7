import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;

import static api.ConstMessages.*;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest extends BaseTest {

    @Test
    @DisplayName("Log in courier")
    @Description("Successful log in of a courier. Expected response code 200")
    public void loginCourierTest() {
        var courierDto = courierGenerator.getCourier();
        courierHttpClient.createCourier(courierDto);
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        response.statusCode(200).and().assertThat().body("id", notNullValue());
        cleanCourierData(courierDto);
    }

    @Test
    @DisplayName("Log in courier without login")
    @Description("Failed courier authorization. Expected response code 400")
    public void loginCourierWithEmptyLoginTest() {
        var courierDto = courierGenerator.getCourier();
        courierDto.setLogin("");
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        response.statusCode(400).and().assertThat().body(MASSAGE, Matchers.equalTo(INVALID_LOGIN));
        cleanCourierData(courierDto);
    }

    @Test
    @DisplayName("Log in courier without password")
    @Description("Failed courier authorization. Expected response code 400")
    public void loginCourierWithEmptyPasswordTest() {
        var courierDto = courierGenerator.getCourier();
        courierDto.setPassword("");
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        response.statusCode(400).and().assertThat().body(MASSAGE, Matchers.equalTo(INVALID_LOGIN));
        cleanCourierData(courierDto);
    }

    @Test
    @DisplayName("Log in courier with wrong login")
    @Description("Failed courier authorization. Expected response code 404")
    public void loginCourierWithWrongLoginTest() {
        var courierDto = courierGenerator.getCourier();
        courierDto.setLogin("wrong_login");
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        response.statusCode(404).and().assertThat().body(MASSAGE, Matchers.equalTo(NOT_FOUND_LOGIN));
        cleanCourierData(courierDto);
    }

    @Test
    @DisplayName("Log in courier with wrong password")
    @Description("Failed courier authorization. Expected response code 404")
    public void loginCourierWithWrongPasswordTest() {
        var courierDto = courierGenerator.getCourier();
        courierDto.setPassword("wrong_password");
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        response.statusCode(404).and().assertThat().body(MASSAGE, Matchers.equalTo(NOT_FOUND_LOGIN));
        cleanCourierData(courierDto);
    }

}
