package model;

public class CourierMapper {

    public LoginCourierDto toLoginCourierDto(CourierDto courierDto) {
        return new LoginCourierDto(courierDto.getLogin(), courierDto.getPassword());
    }

}
