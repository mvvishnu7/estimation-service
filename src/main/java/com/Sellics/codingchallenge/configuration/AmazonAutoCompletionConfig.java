package com.Sellics.codingchallenge.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("com.sellics.coding-challenge.amazon-auto-completion")
public class AmazonAutoCompletionConfig {
    private String searchAlias;
    private String client;
    private int mkt;
}
