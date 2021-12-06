package praktikum.data;

import org.apache.commons.lang3.RandomStringUtils;

public class DataForCreateNewCourier {
    public String login;
    public String password;
    public String firstName;
    private static final int DATA_SIZE = 10;

    public DataForCreateNewCourier() {

    }

    public DataForCreateNewCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static DataForCreateNewCourier getRandom() {
        final String courierLogin = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        final String courierPassword = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        final String courierFirstName = RandomStringUtils.randomAlphabetic(DATA_SIZE);
        return new DataForCreateNewCourier(courierLogin, courierPassword, courierFirstName);
    }

    public DataForCreateNewCourier setLogin (String login) {
        this.login = login;
        return this;
    }

    public DataForCreateNewCourier setPassword (String password) {
        this.password = password;
        return this;
    }

    public DataForCreateNewCourier setFirstName (String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static DataForCreateNewCourier getWithLoginOnly() {
        return new DataForCreateNewCourier().setLogin(RandomStringUtils.randomAlphabetic(DATA_SIZE));
    }

    public static DataForCreateNewCourier getWithPasswordOnly() {
        return new DataForCreateNewCourier().setPassword(RandomStringUtils.randomAlphabetic(DATA_SIZE));
    }

    public static DataForCreateNewCourier getWithLoginAndPasswordOnly() {
        return new DataForCreateNewCourier().setLogin(RandomStringUtils.randomAlphabetic(DATA_SIZE))
                .setPassword(RandomStringUtils.randomAlphabetic(DATA_SIZE));
    }
}