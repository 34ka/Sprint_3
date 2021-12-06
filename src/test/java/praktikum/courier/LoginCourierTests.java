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
import static org.junit.Assert.assertThat;

public class LoginCourierTests {

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
        //Удалить курьера
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Successful authorization by courier")
    @Description("Courier authorization with all fields. Login and password")
    public void successfulAuthorization() {

        //Создать курьера
        courierClient.create(courier);

        //Логин курьером
        ValidatableResponse response = courierClient.login(new CourierCredentials(courier.login, courier.password));
        //Получить ID курьера
        courierId = response.extract().path("id");
        //Получить статус код запроса
        int statusCodeResponse = response.extract().statusCode();

        //Проверить ID курьера
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
        //Проверить статус код
        assertThat(statusCodeResponse, equalTo(200));
    }
}