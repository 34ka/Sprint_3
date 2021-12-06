package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.data.CourierCredentials;
import praktikum.data.DataForCreateNewCourier;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    final static private String COURIER_PATH = "/api/v1/courier/";

    @Step
    public ValidatableResponse create(DataForCreateNewCourier dataForCreateNewCourier) {
        return given()
                .spec(getBaseSpec())
                .body(dataForCreateNewCourier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step
    public ValidatableResponse login(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    @Step
    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then();
    }
}