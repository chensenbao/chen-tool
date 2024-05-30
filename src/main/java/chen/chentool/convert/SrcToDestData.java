package chen.chentool.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description: 目标数据是空是用源数据覆盖
 * @author: sen
 */
@Component
public class SrcToDestData {
    /**
     * 转换数据类型是list或map
     * @param destData 目标数据
     * @param srcData 源数据
     * @return 目标数据
     * @param <T> T属于list或map
     */
    public <T> T completeData(T destData, T srcData) {
//        目标数据空时，返回源数据
        if (destData == null) {
            return srcData;
        }
//        判断数据类型
        if (srcData instanceof List) {
            List<Object> srcDataList = (List<Object>) srcData;
            List<Object> destDataList = Convert.toList(Object.class, destData);
            int srcSize = srcDataList.size();
            int destSize = destDataList.size();
//            补齐目标数据长度
            if (srcSize > destSize) {
                int diff = srcSize - destSize;
                for (int i = 0; i < diff; i++) {
                    destDataList.add(null);
                }
            }
//            判断目标数据是否需要替换
            for (int i = 0; i < srcSize; i++) {
                Object src = srcDataList.get(i);
                Object dest = destDataList.get(i);
//                值如果是map或list继续递归
                if (src instanceof List || src instanceof Map) {
                    dest = completeData(dest, src);
                }
//                目标值为空时替换
                if (dest == null || StrUtil.isBlank(dest.toString())) {
                    dest = src;
                }
                destDataList.set(i, dest);
            }
            destData = (T) destDataList;
        }
        if (srcData instanceof Map) {
            Map<Object, Object> srcDataMap = (Map<Object, Object>) srcData;
            Map<Object, Object> destDataMap = Convert.toMap(Object.class, Object.class, destData);
            for (Map.Entry<Object, Object> srcEntry : srcDataMap.entrySet()) {
                Object key = srcEntry.getKey();
                Object value = srcEntry.getValue();
                if (destDataMap.containsKey(key)) {
                    Object destValue = destDataMap.get(key);
                    if (value instanceof List || value instanceof Map) {
                        destValue = completeData(destDataMap.get(key), value);
                    }
                    if (destValue == null || StrUtil.isBlank(destValue.toString())) {
                        destValue = value;
                    }
                    destDataMap.put(key, destValue);
                } else {
                    destDataMap.put(key, value);
                }
            }
            destData = (T) destDataMap;
        }
        return destData;
    }
}
