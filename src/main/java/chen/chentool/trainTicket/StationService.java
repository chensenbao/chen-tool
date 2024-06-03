package chen.chentool.trainTicket;

import chen.chentool.trainTicket.dao.StationDao;
import chen.chentool.trainTicket.entity.StationEntity;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 火车站站点信息
 * @author: sen
 */
@Service
public class StationService {
    @Autowired
    private StationDao stationDao;

    /**
     * 获取站点信息
     */
    @Transactional
    public void getSiteInfo(String url){
        if (StrUtil.isBlank(url)) {
            url="https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
        }
        String response = HttpUtil.get(url);
        if (response==null){
//             出错
            return;
        }
        response=StrUtil.replace(response,"var station_names ='","");
        response=StrUtil.replace(response,"';","");
//        @bjb|北京北|VAP|beijingbei|bjb|0|0357|北京|||@bjd|北京东|BOP|beijingdong|bjd|1|0357|北京|||
        List<String> list = StrUtil.split(response, "@", -1, true, true);
        stationDao.delete();
        list.stream().forEach(item->{
            List<String> stationInfo = StrUtil.split(item, '|');
            if (stationInfo.size()!=11){
                return;
            }
            StationEntity stationEntity = new StationEntity();
            stationEntity.setStationCode(stationInfo.get(2));
            stationEntity.setStationCity(stationInfo.get(7));
            stationEntity.setStationName(stationInfo.get(1));
            stationEntity.setStationInfo(item);
            stationEntity.setStationPinyin(stationInfo.get(3));
            stationEntity.setStationPinyinSimple(stationInfo.get(4));
            stationDao.insert(stationEntity);
        });
//        System.out.println("操作成功");
    }

}
