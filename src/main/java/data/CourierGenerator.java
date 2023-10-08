package data;


import model.CourierDto;

import java.util.Random;

public class CourierGenerator {

    private final String[] courierNames = {"Ivan", "Anna", "John", "Stan", "David"};
    private final String[] courierPasswords = {"password", "1234567", "qwerty123", "vivaLaRevolution", "q"};
    private final String[] courierLogins = {"GiperMan", "TurboCatt", "RedMachine3000", "JackSparow", "DartV"};
    private final Random random = new Random();

    public CourierDto getCourier() {
        String name = courierNames[random.nextInt(4)];
        String password = courierPasswords[random.nextInt(4)];
        String login = courierLogins[random.nextInt(4)];
        return new CourierDto(login, password, name);
    }

}
