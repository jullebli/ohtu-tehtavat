package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message for giving credentials is given")
    public void userIsNotLoggedInAndErrorMessageGiveYourCredentialsToLoginIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and valid password {string} and matching password confirmation are entered")
    public void validNewUsernameAndPasswordAndConfirmationAreEntered(String username, String password) {
        createNewUserWith(username, password, password);
    }

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("a too short username {string} and valid password {string} and matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAndConfirmationAreEntered(String username, String password) {
        createNewUserWith(username, password, password);
    }

    @Then("user is not created and error message for too short username is reported")
    public void userNotCreatedAndErrorMessageUsernameShouldHaveAtLeast3CharactersIsGiven() {
        pageHasContent("Create username and give password");
        pageHasContent("username should have at least 3 characters");
    }

    @When("a valid username {string} and too short password {string} are given")
    public void correctUsernameAndTooShortPasswordAreGiven(String username, String password) {
        createNewUserWith(username, password, password);
    }

    @Then("user is not created and error for too short password is reported")
    public void userNotCreatedAndErrorMessagePasswordShouldHaveAtLeast8CharactersIsGiven() {
        pageHasContent("Create username and give password");
        pageHasContent("password should have at least 8 characters");
    }

    @When("a valid username {string} and valid password {string} and incorrect conformation {string} are entered")
    public void correctUsernameValidPasswordAndIncorrectConfirmationAreEntered(String username, String password, String confirmation) {
        createNewUserWith(username, password, confirmation);
    }

    @Then("user is not created and error for incompatible password and password confirmation is reported")
    public void userNotCreatedAndErrorMessagePasswordAndConfirmationDoNotMatchIsGiven() {
        pageHasContent("Create username and give password");
        pageHasContent("password and password confirmation do not match");
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userIsCreated(String username, String password) {
        newUserIsSelected();
        createNewUserWith(username, password, password);
    }

    @When("correct username {string} and correct password {string} for generated account are given")
    public void correctUsernameAndCorrectPasswordForGeneratedAccount(String username, String password) {
        logInWith(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userIsTriedToBeCreated(String username, String password) {
        newUserIsSelected();
        createNewUserWith(username, password, password);
    }

    @When("username {string} and the password {string} of a failed creation attempt are given")
    public void loggingParametersOfFailedCreationAttemptIsGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and two error messages are given")
    public void userIsNotLoggedInAndTwoErrorMessagesAreGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createNewUserWith(String username, String password, String confirmation) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    //private void is
}
