package cn.itcast.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class CnItcastServiceConsumerApplicationTests {

    @Autowired
    private RibbonLoadBalancerClient client;

    @Test
    void contextLoads() {
        for (int i = 0; i < 50; i++) {
            ServiceInstance choose = client.choose("service-provider");
            System.out.println(choose.getHost()+":"+choose.getPort());
        }
    }

}
