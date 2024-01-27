package br.com.fiap.soat.pagamentos.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

@Configuration
@Profile("!test")
public class DynamoDBConfig {
    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Value("${aws.accessKey}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretAccessKey;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
    }


    @Bean(name = "amazonDynamoDB")
    public AmazonDynamoDB amazonDynamoDB() {
        try {
            System.out.println("******** ABOUT TO CREATE DYNAMODBCLIENT *********");
            return AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(awsRegion).build();
        } catch (Exception e) {
            System.out.println("ERROR CONNECTION TO DYNAMODB CLIENT: ");
            e.printStackTrace();  // Print the stack trace for better error diagnostics
            throw e;
        }
    }
}
