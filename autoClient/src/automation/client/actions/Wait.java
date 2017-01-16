package com.ibm.ie.spm.automation.client.actions;

import org.openqa.selenium.WebElement;

/**
 * Created by jien.huang on 18/10/2016.
 */
public class Wait extends Action {

    long k = 1000;

    public WebElement findTestObject() {
        return null;
    }

    protected void handle(WebElement testObject) {
        try {
            if (!data.equals("") && data != null) {
                k = Long.parseLong(data) * k;

                Thread.sleep(k);

            } else {
                Thread.sleep(10 * k);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
