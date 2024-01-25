import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.fiap.soat.pagamentos.config.CucumberSpringConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",  glue = {"br.com.fiap.soat.pagamentos.acceptance.steps", "br.com.fiap.soat.pagamentos.config"})
@CucumberContextConfiguration
@SpringBootTest(classes = CucumberSpringConfiguration.class)
public class CucumberRunner {
}
