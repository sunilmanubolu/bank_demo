package com.example.banking.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

//Contains application configuration details from properties file
@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationConfiguration {
	private int chunkSize;
	private float interestPercentPerMinute;
	private float minimumBalanceSaving;
	private float minimumBalanceCurrent;
}
