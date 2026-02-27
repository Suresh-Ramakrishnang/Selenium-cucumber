package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import driver.DriverFactory;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
    }

    @After
    public void teardown() {
        DriverFactory.quitDriver();
    }
}