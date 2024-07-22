package chen.chentool.trainTicket.controller;

import chen.chentool.trainTicket.service.StationService;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 火车票信息
 * @author: sen
 */
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("updateSiteInfo")
    public String updateSiteInfo(@RequestParam Map<String,Object> param){
        String url = Convert.toStr(param.get("url"));
        String siteInfo = stationService.updateSiteInfo(url);
        return siteInfo;
    }
}
