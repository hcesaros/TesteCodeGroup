package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@Teste08",
        glue = "steps",
        plugin = {"pretty","html:target/cucumber-reports"}
)
public class RunnerTest {

}