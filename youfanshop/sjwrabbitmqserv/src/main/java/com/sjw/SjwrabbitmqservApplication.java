package com.sjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class SjwrabbitmqservApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SjwrabbitmqservApplication.class, args);
	}

}
