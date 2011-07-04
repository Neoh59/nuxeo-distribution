/*
 * (C) Copyright 2011 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Thierry Delprat
 */

package org.nuxeo.functionaltests.pages.admincenter;

import org.nuxeo.functionaltests.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SystemHomePage extends AdminCenterBasePage {

    public static final String HOST_SUBTAB = "Host";

    public static final String SETUP_SUBTAB = "Setup";

    public SystemHomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage restart() {
        if (!HOST_SUBTAB.equals(getSelectedSubTab())) {
            selectSubTab(HOST_SUBTAB);
        }
        WebElement restartButton = findElementWithTimeout(By.xpath("//input[@type='submit' and @value='Restart server']"));
        if (restartButton != null) {
            restartButton.click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } else {
            return null;
        }
        findElementWithTimeout(By.id("username"), 4 * 60 * 1000);
        return asPage(LoginPage.class);
    }

    public boolean setConfig(String id, String value) {
        if (!SETUP_SUBTAB.equals(getSelectedSubTab())) {
            selectSubTab(SETUP_SUBTAB);
        }
        WebElement input = findElementWithTimeout(By.xpath("//td[@id='" + id + "']/input"));
        if (input != null) {
            input.sendKeys(value);
            return true;
        }
        return false;
    }

    public String getConfig(String id) {
        if (!SETUP_SUBTAB.equals(getSelectedSubTab())) {
            selectSubTab(SETUP_SUBTAB);
        }
        WebElement input = findElementWithTimeout(By.xpath("//td[@id='" + id + "']/input"));
        if (input != null) {
            return input.getValue();
        }
        return null;
    }

}
