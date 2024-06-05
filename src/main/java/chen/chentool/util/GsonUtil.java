package chen.chentool.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @description: json解析工具
 * @author: sen
 */
public class GsonUtil {

    /**
     * 实现格式化的时间字符串转时间对象
     */
    private static final String DATEFORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static <T> T fromJson(String json, Class<T> type) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(DATEFORMAT_DEFAULT);
        Gson gson = builder.create();
        return gson.fromJson(json,type);
    }
}
