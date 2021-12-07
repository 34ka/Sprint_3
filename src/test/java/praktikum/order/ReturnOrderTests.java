package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.client.OrderClient;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class ReturnOrderTests {

    List<Object> orders;
    OrderClient orderClient;

    @Test
    @DisplayName("Check the return order list")
    @Description("Returning the list of orders")
    public void getReturnListOrder() {

        orderClient = new OrderClient();

        //Получить заказы
        ValidatableResponse response = orderClient.getListOrders(orders);

        //Получить значение ключа "orders"
        orders = response.extract().jsonPath().getList("orders");

        //Сравнить заказы
        assertFalse(orders.isEmpty());
    }
}