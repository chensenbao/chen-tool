package chen.chentool.sort;

import cn.hutool.core.convert.Convert;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description: map排序
 * 记一种map歪门邪道的排序方式
 * @author: sen
 */
@Component
public class MapSort {

    /**
     *  利用LinkedHashMap的插入有序性进行map重排序
     */
    public Map<String,Object> linkMapSort(Map<String, Map<String, Object>> param, String sortKey) {
//        把原始map对象转成list对象
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> entry : param.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> value = entry.getValue();
//            添加map对象的key
            value.put("key", key);
            list.add(value);
        }
//        保持插入顺序的map有序对象
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
//        对list的目标字段进行排序，遍历写入linkedHashMap
        list.stream().sorted(Comparator.comparing(i -> Convert.toInt(i.get(sortKey), 0)
                , Comparator.reverseOrder()))
                .forEach(i->{
                    String key = Convert.toStr(i.get("key"));
                    i.remove("key");
                    linkedHashMap.put(key, i);
                });
        return linkedHashMap;
    }
}
