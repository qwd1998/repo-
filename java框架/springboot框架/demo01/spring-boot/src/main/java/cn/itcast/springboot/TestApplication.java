package cn.itcast.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 引导类，springboot应用的入口
 */
//@EnableAutoConfiguration //启用自动配置
//@ComponentScan  //类似<context:component-scan base-package=""> 扫描该类所在的包，以及子包
@SpringBootApplication //使用组合注解 相当于//@EnableAutoConfiguration @ComponentScan @SpringBootConfig
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
