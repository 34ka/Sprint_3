package praktikum.data;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {
    public String login;
    public String password;
    private static final int DATA_SIZE = 10;

    public CourierCredentials() {

    }

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials setLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public static CourierCredentials getWithLoginOnly(DataForCreateNewCourier courier) {
        return new CourierCredentials().setLogin(courier.login);
    }

    public static CourierCredentials getWithPasswordOnly(DataForCreateNewCourier courier) {
        return new CourierCredentials().setPassword(courier.password);
    }

    public static CourierCredentials getWithDoNotReallyLoginAndPassword(DataForCreateNewCourier courier) {
        return new CourierCredentials().setLogin(RandomStringUtils.randomAlphabetic(DATA_SIZE))
                .setPassword(RandomStringUtils.randomAlphabetic(DATA_SIZE));
    }
}