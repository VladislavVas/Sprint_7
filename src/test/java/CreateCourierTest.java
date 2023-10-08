import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierDto;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static api.ConstMessages.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest extends BaseTest {

    private CourierDto courierDto;

    @Before
    public void setUp() {
        courierDto = courierGenerator.getCourier();
    }

    @After
    public void cleanUp() {
        cleanCourierData(courierDto);
    }

    @Test
    @DisplayName("Create a new courier")
    @Description("Successful creation of a new courier. Expected response code 201.")
    public void createNewCourierTest() {
        var response = courierHttpClient.createCourier(courierDto);
        response.statusCode(201).and().assertThat().body(OK, equalTo(true));
    }

    @Test
    @DisplayName("The uniqueness of the courier's login.")
    @Description("It is not possible to use the login twice. Expected response code 409.")
    public void duplicateLoginCourierTest() {
        var duplicateDto = new CourierDto(courierDto.getLogin(), courierDto.getPassword(), courierDto.getFirstName());
        courierHttpClient.createCourier(courierDto);
        var response = courierHttpClient.createCourier(duplicateDto);
        response.statusCode(409).and().assertThat().body(MASSAGE, equalTo(DUPLICATE_LOGIN));
    }

    @Test
    @DisplayName("Login can't be empty")
    @Description("It is impossible to create a courier if the login field is empty.")
    public void courierWithEmptyLoginTest() {
        courierDto.setLogin("");
        var response = courierHttpClient.createCourier(courierDto);
        response.statusCode(400).and().assertThat().body(MASSAGE, Matchers.equalTo(INVALID_FIELDS));
    }


    @Test
    @DisplayName("Password can't be empty")
    @Description("It is impossible to create a courier if the password field is empty.")
    public void courierWithEmptyPasswordTest() {
        courierDto.setPassword("");
        var response = courierHttpClient.createCourier(courierDto);
        response.statusCode(400).and().assertThat().body(MASSAGE, Matchers.equalTo(INVALID_FIELDS));
    }

}
