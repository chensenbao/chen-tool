package chen.chentool.trainTicket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 火车站站点信息
 * @author: sen
 */
@Component
public class SiteInfo {

    /**
     * 获取站点信息
     */
    public void getSiteInfo(String url){
        if (StrUtil.isBlank(url)) {
            url="https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
        }
        String response = HttpUtil.get(url);
        if (response==null){
//            TODO 出错
            return;
        }
        response=StrUtil.replace(response,"var station_names ='","");
        response=StrUtil.replace(response,"'","");
//        @bjb|北京北|VAP|beijingbei|bjb|0|0357|北京|||@bjd|北京东|BOP|beijingdong|bjd|1|0357|北京|||

        System.out.println(response);

    }

}
