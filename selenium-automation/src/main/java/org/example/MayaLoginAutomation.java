package org.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MayaLoginAutomation {

    private static final String MAYA_URL = "https://maya.adityauniversity.in/";
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Usage: java org.example.MayaLoginAutomation <roll-number> <password>");
        }

        String rollNumber = args[0];
        String password = args[1];

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);

        try {
            driver.manage().window().maximize();
            driver.get(MAYA_URL);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")))
                    .click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("roll_no")));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

            driver.findElement(By.name("roll_no")).sendKeys(rollNumber);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
                    .sendKeys(password);
        } finally {
            driver.quit();
        }
    }
}
