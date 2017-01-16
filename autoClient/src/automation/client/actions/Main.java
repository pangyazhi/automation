package com.ibm.ie.spm.automation.client;

import com.google.common.collect.Queues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.SynchronousQueue;

public class Main {

    public static void main(String[] args) {

        final SynchronousQueue<String> commands = Queues.newSynchronousQueue();
        final SynchronousQueue<String> results = Queues.newSynchronousQueue();
        Thread requestCommand = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        if (commands.size() > 10)
                            Thread.sleep(10 * 1000);
                        //TODO request command from rest api or local test data file
                        commands.put("something");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        requestCommand.start();
        Thread respondResult = new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        if (results.size() == 0) {
                            Thread.sleep(1000);
                            continue;
                        }
                        //TODO send result to server
                    }
                    catch(InterruptedException ex){
                            ex.printStackTrace();
                    }

                }

            }
        });
        respondResult.start();

        Thread action = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        if (commands.size() == 0) {
                            Thread.sleep(1000);
                            continue;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        action.start();
    }

    private static void simpleTest() {
        // write your code here
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new ChromeDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }
}
