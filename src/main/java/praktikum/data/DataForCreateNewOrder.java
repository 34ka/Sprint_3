package praktikum.data;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataForCreateNewOrder {
    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<ColorOrder> color;

    public DataForCreateNewOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime,
                                 String deliveryDate, String comment, List<ColorOrder> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public DataForCreateNewOrder setColor(List <ColorOrder> color) {
        this.color = color;
        return this;
    }

    public static DataForCreateNewOrder getRandom() {
        Faker faker = new Faker(new Locale("ru"));

        final String orderFirstName = faker.name().firstName();
        final String orderLastName = faker.name().lastName();
        final String orderAddress = faker.address().streetAddress(true);
        final int orderMetroStation = faker.number().numberBetween(1,101);
        final String orderPhone = faker.phoneNumber().phoneNumber();
        final int orderRentTime = faker.number().numberBetween(1,366);
        final String orderDeliveryDate = faker.date().future(5, TimeUnit.DAYS).toString();
        final String orderComment = faker.address().streetAddress();
        final List <ColorOrder> orderColor = new ArrayList<>();
        return new DataForCreateNewOrder(orderFirstName, orderLastName, orderAddress, orderMetroStation, orderPhone,
                orderRentTime, orderDeliveryDate, orderComment, orderColor);
    }
}

