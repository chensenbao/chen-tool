package chen.chentool.sort;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: sen
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MapSortTest {
    @Autowired
    private MapSort mapSort;

    @Test
    public void linkMapSort() {
        Map<String, Map<String,Object>> param = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            param.put("map"+i,getMap());
        }
        Map<String, Object> destMap = mapSort.linkMapSort(param, "number");
        System.out.println(destMap.toString());
    }

    /**
     * @return 随机map对象
     */
    private Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        String name = RandomUtil.randomString(5);
        int number = RandomUtil.randomInt(1000, 9999);
        map.put("name",name);
        map.put(name,name);
        map.put("number", number);
        return map;
    }
}