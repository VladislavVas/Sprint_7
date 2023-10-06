import api.CourierHttpClient;
import api.OrderHttpClient;
import data.CourierGenerator;
import model.CourierDto;
import model.CourierMapper;
import model.TrackDto;

import static api.ConstUrl.BASE_URI;

public class BaseTest {

    protected CourierMapper courierMapper = new CourierMapper();
    protected CourierGenerator courierGenerator = new CourierGenerator();
    protected CourierHttpClient courierHttpClient = new CourierHttpClient(BASE_URI);
    protected OrderHttpClient orderHttpClient = new OrderHttpClient(BASE_URI);


    protected void cleanCourierData(CourierDto courierDto) { //get to baseTest
        var response = courierHttpClient.loginCourier(courierMapper.toLoginCourierDto(courierDto));
        Integer id = response.extract().path("id");
        courierHttpClient.deleteCourier(id);
    }

    protected void cleanOrderData(TrackDto trackDto) {
        orderHttpClient.cancelOrder(trackDto);
    }

}
