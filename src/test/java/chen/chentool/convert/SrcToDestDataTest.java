package chen.chentool.convert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: sen
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SrcToDestDataTest {
    @Autowired
    private SrcToDestData srcToDestData;

    @Test
    public void completeDataByMap() {
        Map<Object,Object> srcMap=new HashMap<>();
        srcMap.put("name1","s1");
        srcMap.put("name2","s2");
        List<String> sctList=new ArrayList<>();
        sctList.add("sl1");
        sctList.add("sl2");
        sctList.add("sl3");
        srcMap.put("list1",sctList);
        Map<String,Object> sMap=new HashMap<>();
        sMap.put("age1",45);
        sMap.put("age2",5);
        sMap.put("age3",82);
        srcMap.put("map1",sMap);

        Map<Object,Object> destMap=new HashMap<>();
        destMap.put("name1","d1");
        destMap.put("name2",null);
        List<String> destList=new ArrayList<>();
        destList.add("dl1");
        destList.add(null);
        destList.add("dl3");
        destMap.put("list1",destList);
        Map<String,Object> dMap=new HashMap<>();
        dMap.put("age1", null);
        dMap.put("age2",11);
        dMap.put("age3",null);
        destMap.put("map1",dMap);
        Map<Object, Object> completeData = srcToDestData.completeData(destMap, srcMap);
        System.out.println(completeData.toString());
    }
    @Test
    public void completeDataByList() {
        List<Object> srcList=new ArrayList<>();
        srcList.add("s1");
        srcList.add("s2");
        srcList.add("s3");
        List<Object> destList=new ArrayList<>();
        destList.add("d1");
        destList.add(null);
        destList.add("d3");
        List<Object> completeData = srcToDestData.completeData(destList, srcList);
        System.out.println(completeData);
    }
}