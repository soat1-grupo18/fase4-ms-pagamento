import br.com.fiap.soat.pagamentos.config.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",  glue = "br.com.fiap.soat.pagamentos")
@CucumberContextConfiguration
@SpringBootTest(classes = CucumberSpringConfiguration.class)
@Import({ TestConfig.class, UseCaseBeanConfig.class, ControllerBeanConfig.class })
@ActiveProfiles("test")
public class CucumberRunner {
    @BeforeClass
    public static void setUp() {
        System.setProperty("spring.profiles.active", "test");
    }
}
