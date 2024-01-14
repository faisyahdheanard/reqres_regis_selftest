package stepDef;

import api_page.page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;


public class step {

    page Page;
    public step() {
        this.Page = new page();
    }

    @Given("User hits the API to Get List User from page {int}")
    public void hitsGetList(int pageNumber) {
        Page.hitGetListUser(pageNumber);
    }
    @Then("validate the status code is {int}")
    public void validateStatusCode(int statusCode) {
        Page.validateTheStatusCode(statusCode);
    }
    @Then("validate the response body of {string}")
    public void validateTheResponseBody(String rensponseBody) {
        Page.validateBodyResponse(rensponseBody);
    }
    @Then("validate the response JSON with JSONSchema {string}")
    public void validateResponseJSON(String jsonData) {
        Page.validateJSONSchema(jsonData);
    }
    @Given("User hits the API to Get Single User with user id {int}")
    public void hitsGetSingle(int user_id) {
        Page.hitGetSingleUser(user_id);
    }
    @Given("User hits the API to Post User Registration with {string} data")
    public void hitsPostRegistrasion(String dataType) {
        Page.hitPostRegister(dataType);
    }
    @Then("validate the response body should contain:")
    public void validateTheResponseBodyShouldContain(Map<String, String> expectedDataValues) {
        Page.validateTheResponseBodyShouldContain(expectedDataValues);
    }

    @Then("validate the response body should contain an error message said {string}")
    public void validateTheResponseBodyShouldContainAnErrorMessageSaid(String errorMessage) {

    }
}
