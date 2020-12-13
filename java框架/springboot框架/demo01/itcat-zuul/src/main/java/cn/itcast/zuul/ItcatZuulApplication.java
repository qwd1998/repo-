package cn.itcast.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient //开启eureka的客户端
public class ItcatZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItcatZuulApplication.class, args);
    }

}
