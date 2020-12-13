package cn.itcast.springboot.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
//@PropertySource(value = "classpath:jdbc.properties")

@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration {

/*    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    */

    /*
    @Autowired
    private JdbcProperties jdbcProperties;

    @Bean
    public DataSource dataSource(){

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(this.jdbcProperties.getDriverClassName());
        ds.setUrl(this.jdbcProperties.getUrl());
        ds.setUsername(this.jdbcProperties.getUsername());
        ds.setPassword(this.jdbcProperties.getPassword());
        return ds;
    }
    */



   /* //第二种配置方式

    private JdbcProperties jdbcProperties;
    public JdbcConfiguration(JdbcProperties jdbcProperties){
        this.jdbcProperties = jdbcProperties;
    }

    @Bean
    public DataSource dataSource(){

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(this.jdbcProperties.getDriverClassName());
        ds.setUrl(this.jdbcProperties.getUrl());
        ds.setUsername(this.jdbcProperties.getUsername());
        ds.setPassword(this.jdbcProperties.getPassword());
        return ds;
    }*/

    /* //第三种配置方式
    @Bean //将方法的返回值注入到spring容器中
    public DataSource dataSource(JdbcProperties jdbcProperties){

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(jdbcProperties.getDriverClassName());
        ds.setUrl(jdbcProperties.getUrl());
        ds.setUsername(jdbcProperties.getUsername());
        ds.setPassword(jdbcProperties.getPassword());
        return ds;
    }*/

    //第四种方法
    @Bean //将方法的返回值注入到spring容器中
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }
}
