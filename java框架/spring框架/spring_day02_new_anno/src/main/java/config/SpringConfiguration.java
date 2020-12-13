package config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.PoolableWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

/**
 * 该类是一个配置类,作用和bean.xml是一样的
 *
 * Spring中的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性
 *          value：它和basePackages的作用是一样的，都是创建容器时要创建的包
 *                 使用这个注解，相当于在xml中配置了
 *                 <context:component-scan base-package="cn.itcast">
 *                     </context:component-scan>
 *Bean
 *      作用：用于把当前方法的返回值作为bean对象，存入ioc容器中
 *      属性：
 *          name：用于指定bean的id，不写时，就是当前方法的名称
 *      细节：当我们使用注解配置方式时，如果方法有参数，spring框架回去容器中查找有木有可用的bean对象
 *          查找方法和Autowired注解的方式一样的
 *Import
 *      作用：导入其他的配置类
 *      属性：
 *         value：用于指定其他配置类的字节码。
 *          使用import注解时，有import注解是主配置类（父配置类），其他的都是子配置类
 *
 * PropertySource
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value 指定文件的名称和路径
 *              关键字，classpath表示类路径下
 */

@Configuration
@ComponentScan(basePackages = "cn.itcast")
@PropertySource(value = "classpath:jdbc.properties")
public class SpringConfiguration {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    /**
     * 创建一个JdbcTemplate对象
     * @param dataSource
     * @return
     */
    @Bean(name="template")
    @Scope(value = "prototype")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    /**
     * 创建数据源
     * @return
     */
    @Bean(name="dataSource")
    @Scope(value = "prototype")

    public DataSource createDataSource(){
        DruidDataSource ds = new DruidDataSource();

        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
