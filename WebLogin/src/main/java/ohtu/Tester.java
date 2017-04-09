package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        wrongPassword(driver, element);
        wrongUsername(driver, element);
        creatingNewUser(driver, element, "");
        createNewAndLogout(driver, element);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void wrongPassword(WebDriver driver, WebElement element) {
        driver.get("http://localhost:4567");
        sleep(2);

        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdasd");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void wrongUsername(WebDriver driver, WebElement element) {
        driver.get("http://localhost:4567");
        sleep(2);

        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("asdasda");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdasd");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void creatingNewUser(WebDriver driver, WebElement element, String user) {
        if (user.equals("")) {
            user = "uusi";
        }
        driver.get("http://localhost:4567");
        sleep(2);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void createNewAndLogout(WebDriver driver, WebElement element) {
        creatingNewUser(driver, element, "uusi2");
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(3);

    }
}
