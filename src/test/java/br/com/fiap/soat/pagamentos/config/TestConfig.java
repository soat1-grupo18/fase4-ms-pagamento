package br.com.fiap.soat.pagamentos.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.junit.AfterClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = {
        "br.com.fiap.soat.pagamentos",
        "br.com.fiap.soat.pagamentos.acceptance.steps"
})
@TestConfiguration
@Profile("test")
public class TestConfig {
    public TestConfig() {
        System.out.println("Spring Test Context Loaded! \n");
    }

    static {
        InitializeLocalDatabase.initialize();
    }

    public static class InitializeLocalDatabase {
        private static Boolean dbContainerHasStarted = false;
        private static GenericContainer<?> dynamoDBLocalContainer;
        private static final DockerImageName DYNAMODB_LOCAL_IMAGE = DockerImageName.parse("public.ecr.aws/aws-dynamodb-local/aws-dynamodb-local:latest");

        private static AmazonDynamoDB amazonDynamoDB;


        public static void initialize() {
            System.out.printf("Has container started? %s \n", dbContainerHasStarted);

            if (!dbContainerHasStarted) {
                startDynamoDBLocalContainer();
                dbContainerHasStarted = true;
            }
        }

        public static void startDynamoDBLocalContainer() {
            System.out.print("About to download dynamoDB-local container... \n");
            dynamoDBLocalContainer = new GenericContainer<>(DYNAMODB_LOCAL_IMAGE).withExposedPorts(8000);
            System.out.print("Downloaded dynamoDB-local container! \n");

            System.out.printf("About to start dynamoDB-local container...");
            dynamoDBLocalContainer.start();
            System.out.println("dynamoDB-local local container started.");

            createAmazonDynamoDBBean();
            System.out.print("Local database started. \n");
            createTable();
        }

        static AmazonDynamoDB createAmazonDynamoDBBean() {
            String dynamoDbLocalEndpoint = "http://" + dynamoDBLocalContainer.getContainerIpAddress() + ":" + dynamoDBLocalContainer.getMappedPort(8000);

            System.out.printf("Starting to build local DynamoDB with endpoint': %s\n", dynamoDbLocalEndpoint);

            BasicSessionCredentials awsCreds = new BasicSessionCredentials("111", "2222", "333");

            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbLocalEndpoint, "local"))
                    .build();

            System.out.printf("DynamoDB has been built! Returned: %s \n", amazonDynamoDB);
            return amazonDynamoDB;
        }

        static void createTable() {
            System.out.print("Checking if can create table... \n");
            if (amazonDynamoDB == null) {
                throw new IllegalStateException("AmazonDynamoDB must be initialized before creating the table.");
            }

            DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

            try {
                System.out.println("About to create table...\n");
                Table table = dynamoDB.createTable("Pagamentos",
                        Arrays.asList(new KeySchemaElement("id", KeyType.HASH)), // Partition key
                        Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                        new ProvisionedThroughput(5L, 5L));

                table.waitForActive();
                System.out.printf("Table created! Status: %s \n", table.getDescription().getTableStatus());
            }
            catch (Exception e) {
                System.err.println("Error creating table: \n");
                System.err.println(e.getMessage());
            }
        }

        @Bean(initMethod = "start", destroyMethod = "stop")
        public GenericContainer<?> dynamoDBLocalContainer() {
            return dynamoDBLocalContainer;
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB(GenericContainer<?> dynamoDBLocalContainer) {
            return createAmazonDynamoDBBean();
        }

        @AfterClass
        public static void stopDynamoDBLocalContainer() {
            if (dynamoDBLocalContainer != null && dynamoDBLocalContainer.isRunning()) {
                dynamoDBLocalContainer.stop();
                System.out.println("DynamoDB-local local container stopped.");
            }
        }
    }
}
