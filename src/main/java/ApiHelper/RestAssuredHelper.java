package ApiHelper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import Commons.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class RestAssuredHelper {
    public static Response getPendingOrder(String sessionId) {
        String baseUrl = "https://admin.pi-x.sg/web/dataset/search_read";
        // Set the base URI for RestAssured
        RestAssured.baseURI = baseUrl;
        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("cookie", "session_id=" + sessionId)
                .body("{\"jsonrpc\":\"2.0\",\"method\":\"call\",\"params\":{\"model\":\"ps.pending.order\",\"domain\":[],\"fields\":[\"account_id\",\"external_order_id\",\"order_id\",\"symbol_code\",\"order_type\",\"order_side\",\"order_status\",\"price\",\"volume\",\"exec_price_display\",\"exec_volume\",\"order_time_display\",\"submitted_by\",\"last_updated_time_display\"],\"limit\":80,\"sort\":\"id DESC\",\"context\":{\"lang\":\"en_US\",\"tz\":\"Asia/Bangkok\",\"uid\":52,\"allowed_company_ids\":[1],\"params\":{\"action\":174,\"cids\":1,\"menu_id\":175,\"model\":\"ps.pending.order\",\"view_type\":\"list\"},\"bin_size\":true}},\"id\":600653087}")
                .when()
                .post();
        return response;
    }
    public static List<Long> extractExecTimes(Response response) {
        List<Long> execTimes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody().asString());
            JsonNode results = root.path("results");
            if (results.isArray()) {
                for (JsonNode node : results) {
                    long execTime = node.path("exec_time").asLong();
                    execTimes.add(Long.valueOf(execTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execTimes;
    }
}
