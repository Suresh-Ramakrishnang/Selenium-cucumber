package hooks;

import org.openqa.selenium.WebDriver;

import base.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks extends BaseTest {


	@Before
    public void startScenario() {
        setup();
    }

    @After
    public void endScenario() {
        tearDown();
    }
}

