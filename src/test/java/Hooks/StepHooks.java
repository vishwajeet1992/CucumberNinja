package Hooks;


import WebDriver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class StepHooks {

    @Before
    public void initializeTest() {
        // Code to setup initial configurations
    }

    @After
    public void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                //take screenshot here
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DriverFactory driverFactory = DriverFactory.getInstanceOfDriverFactory();
        driverFactory.clear();
    }
}
