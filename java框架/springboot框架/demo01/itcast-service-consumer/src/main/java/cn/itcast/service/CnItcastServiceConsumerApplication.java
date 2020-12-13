package cn.itcast.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker  //开启熔断*/
@SpringCloudApplication //@SpringBootApplication  @EnableDiscoveryClient  @EnableCircuitBreaker

@EnableFeignClients  //打开feign的客户端
public class CnItcastServiceConsumerApplication {


   /* @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/



    public static void main(String[] args) {
        SpringApplication.run(CnItcastServiceConsumerApplication.class, args);
    }

}
