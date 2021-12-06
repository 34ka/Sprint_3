package praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.client.CourierClient;
import praktikum.data.CourierCredentials;
import praktikum.data.DataForCreateNewCourier;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class LoginCourierRequestValidationTests {

    private CourierClient courierClient = new CourierClient();
    private static DataForCreateNewCourier courier = DataForCreateNewCourier.getRandom();
    private CourierCredentials courierCredentials;
    private int expectedStatus;
    private String expectedErrorTextMessage;

    public LoginCourierRequestValidationTests(CourierCredentials courierCredentials, int expectedStatus, String expectedErrorTextMessage) {
        this.courierCredentials = courierCredentials;
        this.expectedStatus = expectedStatus;
        this.expectedErrorTextMessage = expectedErrorTextMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {CourierCredentials.getWithLoginOnly(courier), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithPasswordOnly(courier), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithDoNotReallyLoginAndPassword(courier) , 404, "Учетная запись не найдена"}
        };
    }

    @Test
    @DisplayName("Check the login without fields")
    @Description("Courier login with the: " +
            "1. Only login field " +
            "2. Only password field " +
            "3. With do not really login and password fields")
    public void loginCouriersWithoutFields() {

        //Создать курьера
        courierClient.create(courier);

        //Логин курьером
        ValidatableResponse response = courierClient.login(courierCredentials);

        //Получить статус кода запроса
        int statusCode = response.extract().statusCode();
        //Получить значение ключа "message"
        String errorTextMessage = response.extract().path("message");

        //Проверить статус код
        assertThat(statusCode, equalTo(expectedStatus));
        //Проверить значение ключа "message"
        assertThat(errorTextMessage, equalTo(expectedErrorTextMessage));
    }
}