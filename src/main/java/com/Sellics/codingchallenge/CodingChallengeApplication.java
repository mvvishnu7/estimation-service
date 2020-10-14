package com.Sellics.codingchallenge;

import com.Sellics.codingchallenge.client.AmazonAutoCompleteApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {AmazonAutoCompleteApi.class})
public class CodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingChallengeApplication.class, args);
    }

}
