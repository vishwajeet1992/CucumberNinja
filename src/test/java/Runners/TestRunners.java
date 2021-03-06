package Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"Steps", "Hooks"},
        features = {
                "src/test/resources/Features"

        },
        plugin = {"pretty", "json:target/cucumber-json/cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml", "html:target/cucumber-reports/report.html"},

        dryRun = false,
        monochrome = true)


public class TestRunners {
}
