package cn.itcast.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {

        private static Properties pro;
        private static Map<String,Object> map;
        static {
            try {
                pro= new Properties();
                //1.获取字节码文件
                InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("BeanFactory.properties");
                //2.加载配置文件
                pro.load(in);
                //3.实体化容器
                map = new HashMap<String, Object>();
                //4.取出配置文件中的所有的key
                Enumeration keys = pro.keys();
                //5.遍历枚举
                while(keys.hasMoreElements()){
                    //取出每一个key
                    String key = keys.nextElement().toString();
                    //根据key获取value
                    String beanPath = pro.getProperty(key);
                    //反射创建对象
                    Object value = Class.forName(beanPath).newInstance();
                    //把key和value存入容器之中
                    map.put(key,value);
                }

            } catch (Exception e) {
                System.out.println("文件加载失败");
            }
        }

        public static Object getBean(String beanPath){
            return  map.get(beanPath);

        }


       /* public static Object getBean(String s){
            String name = pro.getProperty(s);
            Object instance = null;
            try {
                instance = Class.forName(name).newInstance();
            } catch (Exception e) {
                e.printStackTrace();


            return  instance;
        }
*/

}


