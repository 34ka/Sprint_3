package praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.CourierClient;
import praktikum.data.CourierCredentials;
import praktikum.data.DataForCreateNewCourier;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CreateCourierTests {

    private CourierClient courierClient;
    private int courierId;
    private DataForCreateNewCourier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        //Сгенерировать случайные данные полей
        courier = DataForCreateNewCourier.getRandom();
    }

    @After
    public void tearDown() {
        //Удалить успешно созданного курьера
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Сheck the successful creation of a courier")
    @Description("Creating a courier with all fields. Login, password and firstname")
    public void successfulCreateCourier() {

        //Создать курьера
        ValidatableResponse response = courierClient.create(courier);

        //Получить статус кода запроса
        int statusCodePositiveResponseCreate = response.extract().statusCode();
        //Получить значение ключа "ok"
        boolean isCourierCreated = response.extract().path("ok");

        //Получить ID курьера
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password)).extract().path("id");

        //Проверить статус код
        assertThat(statusCodePositiveResponseCreate, equalTo(201));
        //Проверить создание курьера
        assertTrue("Courier is not created", isCourierCreated);
        //Проверить ID курьера
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    @Test
    @DisplayName("Сheck the unsuccessful creation of a courier")
    @Description("Creating a courier with a login, password and firstname twice")
    public void unsuccessfulCreateTwoIdenticalCouriers() {

        //Создать курьера
        courierClient.create(courier);

        //Повторно cоздать курьера
        ValidatableResponse response = courierClient.create(courier);

        //Получить статус кода запроса
        int statusCodeNegativeResponse = response.extract().statusCode();
        //Получить значение ключа "ok"
        String message = response.extract().path("message");

        //Получить ID курьера
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password)).extract().path("id");

        //Проверить статус код
        assertThat(statusCodeNegativeResponse, equalTo(409));
        //Проверить значение ключа "message"
        assertThat("Wrong body - massage", message, (equalTo("Этот логин уже используется")));
        //Проверить ID курьера
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }
}