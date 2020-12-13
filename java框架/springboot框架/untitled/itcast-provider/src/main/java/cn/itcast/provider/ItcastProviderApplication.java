package cn.itcast.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.itcast.provider.mapper")
@EnableDiscoveryClient
public class ItcastProviderApplication {


	public static void main(String[] args){
		SpringApplication.run(ItcastProviderApplication.class, args);
	}

}
