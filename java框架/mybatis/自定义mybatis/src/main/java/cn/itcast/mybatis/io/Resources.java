package cn.itcast.mybatis.io;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 使用类加载器，读取配置文件的类
 */
public class Resources {

    /**
     * 根据传入参数获取一个字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){

        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
