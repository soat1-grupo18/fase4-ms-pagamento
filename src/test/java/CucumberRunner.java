import br.com.fiap.soat.pagamentos.config.ControllerBeanConfig;
import br.com.fiap.soat.pagamentos.config.TestConfig;
import br.com.fiap.soat.pagamentos.config.UseCaseBeanConfig;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import br.com.fiap.soat.pagamentos.config.CucumberSpringConfiguration;
import org.springframework.context.annotation.Import;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",  glue = "br.com.fiap.soat.pagamentos")
@CucumberContextConfiguration
@SpringBootTest(classes = CucumberSpringConfiguration.class)
@Import({UseCaseBeanConfig.class, ControllerBeanConfig.class, TestConfig.class})
@ActiveProfiles("test")
public class CucumberRunner {
    @BeforeClass
    public static void setUp() {
        System.setProperty("spring.profiles.active", "test");
    }
}
