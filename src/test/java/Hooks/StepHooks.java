package Hooks;


import WebDriver.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

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
