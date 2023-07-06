import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestCases {

    public EdgeDriver driver;

    LoginElements varLogin = new LoginElements();
    SignupElements varSignup = new SignupElements();
    CategoriesElements varCat = new CategoriesElements();
    CartElements varCart = new CartElements();
    HomepageElements varHome = new HomepageElements();


    @BeforeMethod
    public void init() {
        System.setProperty("webdriver.edge.driver","msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void homePage(){

        WebElement logoHome = driver.findElement(By.xpath(varHome.logoHome));
        Assert.assertTrue(logoHome.isDisplayed());

        String url = driver.getCurrentUrl();
        Assert.assertEquals(varHome.urlHome,url);
    }

    @Test
    public void signup() {

        // checking homepage
        homePage();

        // click on signup label from the homepage
        WebElement signup = driver.findElement(By.xpath(varSignup.signup));
        signup.click();

        // wait until a certain action done (here is the sign up label appears)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varSignup.signupLabel)));

        // checking sign up form has been loaded
        WebElement signupLabel = driver.findElement(By.xpath(varSignup.signupLabel));
        Assert.assertTrue(signupLabel.isDisplayed());

        // enter username in username field
        WebElement usernameField = driver.findElement(By.xpath(varSignup.usernameSignup));
        usernameField.sendKeys("username");

        // enter password in password field
        WebElement passwordField = driver.findElement(By.xpath(varSignup.passwordSignup));
        passwordField.sendKeys("passw0rd");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // click on sign up button to register
        WebElement signupButton = driver.findElement(By.xpath(varSignup.signupButton));
        signupButton.click();
    }

    @Test
    public void login() {

        // checking homepage
        homePage();

        // click on login label from the homepage
        WebElement login = driver.findElement(By.xpath(varLogin.login));
        login.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varLogin.loginLabel)));

        // checking login form has been loaded
        WebElement loginLabel = driver.findElement(By.xpath(varLogin.loginLabel));
        Assert.assertTrue(loginLabel.isDisplayed());

        // enter username in username field
        WebElement usernameField = driver.findElement(By.xpath(varLogin.usernameLogin));
        usernameField.sendKeys("username");

        // enter password in password field
        WebElement passwordField = driver.findElement(By.xpath(varLogin.passwordLogin));
        passwordField.sendKeys("passw0rd");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // click on login button to log in
        WebElement loginButton = driver.findElement(By.xpath(varLogin.loginButton));
        loginButton.click();

    }

    @Test
    public void checkCategories(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varCat.categoriesList)));

        WebElement categoriesList = driver.findElement(By.xpath(varCat.categoriesList));
        Assert.assertTrue(categoriesList.isDisplayed());

        WebElement phone = driver.findElement(By.xpath(varCat.phone));
        Assert.assertTrue(phone.isDisplayed());

        WebElement laptop = driver.findElement(By.xpath(varCat.laptop));
        Assert.assertTrue(laptop.isDisplayed());

        WebElement monitor = driver.findElement(By.xpath(varCat.monitor));
        Assert.assertTrue(monitor.isDisplayed());

    }

    @Test
    public void addItemToCart(){

        // choosing random item to add it
        WebElement randomItem = driver.findElement(By.xpath(varCart.randomItem));
        randomItem.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varCart.addToCartButton)));

        // add this item to cart
        WebElement addToCartButton = driver.findElement(By.xpath(varCart.addToCartButton));
        addToCartButton.click();
    }

    @Test
    public void removeItemFromCart(){

        addItemToCart();

        WebElement cart = driver.findElement(By.xpath(varCart.cart));
        cart.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varCart.checkProductRow)));

        WebElement checkProductRowBeforeRemove = driver.findElement(By.xpath(varCart.checkProductRow));
        Assert.assertTrue(checkProductRowBeforeRemove.isDisplayed());

        WebElement deleteButton = driver.findElement(By.xpath(varCart.deleteButton));
        deleteButton.click();

    }

    @Test
    public void fullCyclePurchase(){

        addItemToCart();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement cart = driver.findElement(By.xpath(varCart.cart));
        cart.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varCart.totalBeforePlaceOrder)));

        // get total amount in the cart
        WebElement totalBeforePlaceOrder = driver.findElement(By.xpath(varCart.totalBeforePlaceOrder));
        String totalB = totalBeforePlaceOrder.getText();

        // click on place order button
        WebElement placeOrderButton = driver.findElement(By.xpath(varCart.placeOrderButton));
        placeOrderButton.click();

        // wait until the form opens
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(varCart.totalAfterPlaceOrder)));

        // check total amount in the cart after place order
        WebElement totalAfterPlaceOrder = driver.findElement(By.xpath(varCart.totalAfterPlaceOrder));
        String totalA = totalAfterPlaceOrder.getText();

        // substring(7) for getting the amount from total: 'amount' for checking with amount in the cart
        Assert.assertEquals(totalB,totalA.substring(7));

        // filling out the fields with data
        WebElement name = driver.findElement(By.xpath(varCart.name));
        name.sendKeys("Ziad");

        WebElement country = driver.findElement(By.xpath(varCart.country));
        country.sendKeys("Egypt");

        WebElement city = driver.findElement(By.xpath(varCart.city));
        city.sendKeys("Cairo");

        WebElement card = driver.findElement(By.xpath(varCart.card));
        card.sendKeys("1234567812345678");

        WebElement month = driver.findElement(By.xpath(varCart.month));
        month.sendKeys("07");

        WebElement year = driver.findElement(By.xpath(varCart.year));
        year.sendKeys("26");

        WebElement purchaseButton = driver.findElement(By.xpath(varCart.purchaseButton));
        purchaseButton.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // check purchase goes right
        WebElement tickLogo = driver.findElement(By.xpath(varCart.tickLogo));
        Assert.assertTrue(tickLogo.isDisplayed());

        WebElement okButton = driver.findElement(By.xpath(varCart.okButton));
        okButton.click();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
