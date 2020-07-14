package Jokseia.uti;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtil {

    private static volatile ObjectMapper MAPPER;

    public static ObjectMapper get() {
        if (MAPPER == null) { // 双重校验所完成线程安全模式的单例模式
            synchronized (JSONUtil.class) {
                if (MAPPER == null) {
                    MAPPER = new ObjectMapper();
                }
            }
        }
        return MAPPER;
    }

    /*
    * java对象序列化为json字符串
    * o 对象
    * 返回 json字符串
    * */

    public static String serialize(Object o) {
        try {
            return get().writeValueAsString(o);
        } catch (JsonProcessingException e) { //编译时异常转化为运行时异常，编译不处理
            throw new RuntimeException("JSON序列化失败，对象为:"+o,e);
        }
    }

    /*
    * 将json字符串反序列化为java对象
    * json字符串
    * clazz java类型
    * <T> 泛型
    * 反序列化后的java对象
    */
    public static <T> T deserialize(String json,Class<T> clazz) {
        try {
            return get().readValue(json,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败，JSON字符串为:"+json,e);
        }
    }

    public static <T> T deserialize(InputStream is, Class<T> clazz) {
        try {
            return get().readValue(is,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败，JSON字符串为:",e);
        }
    }
}
