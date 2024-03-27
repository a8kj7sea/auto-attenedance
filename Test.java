import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MoodleAttendanceAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Navigate to the Moodle login page
            driver.get("https://s2.ebalqa.courses/nfetmoodle/login/index.php");

            // Enter username and password
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("ni");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("no");

            // Click the login button
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();

            // Wait until the page loads after login
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frontpage-course-list")));

            // Check if successfully logged in
            String pageTitle = driver.getTitle();
            if (pageTitle.contains("Dashboard")) {
                System.out.println("Successfully logged in!");

                // Navigate to the attendance page
                driver.get("https://s2.ebalqa.courses/nfetmoodle/mod/attendance/view.php?id=30401");

                // Click on "Submit attendance" link
                WebElement submitAttendanceLink = driver.findElement(By.xpath("//a[text()='Submit attendance']"));
                submitAttendanceLink.click();

                // Select attendance status
                WebElement attendanceStatus = driver.findElement(By.id("id_status_10853"));
                attendanceStatus.click();

                // Click on submit button
                WebElement submitButton = driver.findElement(By.cssSelector("span[data-fieldtype='submit']"));
                submitButton.click();
            } else {
                System.err.println("Failed to login. Please check your credentials.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
