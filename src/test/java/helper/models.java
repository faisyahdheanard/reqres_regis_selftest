package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class models {
    private static RequestSpecification request;

    public static void setupHeaders() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    public static Response getUsers(String endpoint) {
        setupHeaders();
        return request.when().get(endpoint);
    }

    public static Response postRegister_ValidData(String endpoint) {
        JSONObject payload = new JSONObject();
        payload.put("email", "eve.holt@reqres.in");
        payload.put("password", "pistol");

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response postRegister_Unsuccessful_NoPassword(String endpoint) {
        JSONObject payload = new JSONObject();
        payload.put("email", "sydney@fife");

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response postRegister_Unsuccessful_NoEmail(String endpoint) {
        JSONObject payload = new JSONObject();
        payload.put("password", "pistol");

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response postRegister_Unsuccessful_UnregisteredEmail(String endpoint) {
        JSONObject payload = new JSONObject();
        payload.put("email", "mnovas200@reqres.in");
        payload.put("password", "nova");

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response postRegister_Unsuccessful_Blank(String endpoint) {
        JSONObject payload = new JSONObject();
        payload.put("email", "");
        payload.put("password", "");

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }
}
