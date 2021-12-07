package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.data.DataForCreateNewOrder;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    final static private String ORDERS_PATH = "/api/v1/orders";

    @Step
    public ValidatableResponse create(DataForCreateNewOrder dataForCreateNewOrder) {
        return given()
                .spec(getBaseSpec())
                .body(dataForCreateNewOrder)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step
    public ValidatableResponse cancel(int track) {
        return given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .put(ORDERS_PATH + "/cancel")
                .then();
    }

    @Step
    public ValidatableResponse getListOrders(List<Object> orders) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH + "?courierId=")
                .then();
    }
}