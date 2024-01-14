package api_page;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;
import java.util.Map;

import static helper.endpoint.*;
import static helper.models.*;
import static helper.utility.getJSONSchema;
import static org.assertj.core.api.Assertions.assertThat;

public class page {

    private static Response response;

    public void hitGetListUser(int page) {
        response = getUsers(USER_LIST(page));
    }

    public void hitGetSingleUser(int user_id) {
        response = getUsers(USER_SINGLE(user_id));
    }

    public void hitPostRegister(String dataType) {
        switch (dataType) {
            case "valid":
                response = postRegister_ValidData(USER_REGISTER);
                break;
            case "no password":
                response = postRegister_Unsuccessful_NoPassword(USER_REGISTER);
                break;
            case "no email":
                response = postRegister_Unsuccessful_NoEmail(USER_REGISTER);
                break;
            case "unregistered email":
                response = postRegister_Unsuccessful_UnregisteredEmail(USER_REGISTER);
                break;
            case "blank":
                response = postRegister_Unsuccessful_Blank(USER_REGISTER);
                break;
        }
    }

    public void validateTheStatusCode(int statusCode) {
        assertThat(response.statusCode()).isEqualTo(statusCode);
    }

    public void validateBodyResponse(String method) {
        JsonPath jsonPath = response.jsonPath();

        switch (method) {
            case "Get List Users":
                List<Object> dataArray = jsonPath.getList("data");

                for (Object dataObject : dataArray) {
                    JsonPath dataJsonPath = new JsonPath(dataObject.toString());

                    assertThat(dataJsonPath.getInt("id")).isNotNull();
                    assertThat(dataJsonPath.getString("email")).isNotNull();
                    assertThat(dataJsonPath.getString("email")).contains("@");
                    assertThat(dataJsonPath.getString("first_name")).isNotNull();
                    assertThat(dataJsonPath.getString("last_name")).isNotNull();
                    assertThat(dataJsonPath.getString("avatar")).isNotNull();
                }
                break;
            case "Get Single Users":
                assertThat(jsonPath.getInt("data.id")).isNotNull();
                assertThat(jsonPath.getString("data.email")).isNotNull();
                assertThat(jsonPath.getString("data.first_name")).isNotNull();
                assertThat(jsonPath.getString("data.last_name")).isNotNull();
                assertThat(jsonPath.getString("data.avatar")).isNotNull();
                break;
            case "Post User Register":
                assertThat(jsonPath.getInt("id")).isNotNull();
                assertThat(jsonPath.getString("token")).isNotNull();
                break;
            default:
                throw new IllegalArgumentException("Invalid method: " + method);
        }
    }

    public void validateJSONSchema(String fileName) {
        File JSONFile = getJSONSchema(fileName);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    public void validateTheResponseBodyShouldContain(Map<String, String> expectedValues) {
        for(Map.Entry<String, String> entry: expectedValues.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            String actualValue = response.path(key).toString();
            assertThat(actualValue).isEqualTo(value);
        }
    }

    public void validateResponseBodyContainErrorMessage(String errorMessage) {
        response.then().assertThat()
                .body("error", Matchers.equalTo(errorMessage));
    }
}
