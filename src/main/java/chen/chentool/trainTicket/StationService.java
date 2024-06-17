package chen.chentool.trainTicket;

import chen.chentool.trainTicket.dao.StationDao;
import chen.chentool.trainTicket.entity.StationEntity;
import chen.chentool.trainTicket.entity.TicketDTO;
import chen.chentool.trainTicket.entity.TicketReponse;
import chen.chentool.trainTicket.entity.TrainBO;
import chen.chentool.util.GsonUtil;
import chen.chentool.util.HttpsUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 火车站站点信息
 * @author: sen
 */
@Service
public class StationService {
    private final StationDao stationDao;
    private final Logger logger;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * 获取站点信息
     */
    @Transactional
    public void getSiteInfo(String url) {
        if (StrUtil.isBlank(url)) {
            url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
        }
        String response = HttpUtil.get(url);
        if (response == null) {
            logger.warn("获取站点信息出错");
            return;
        }
        response = StrUtil.replace(response, "var station_names ='", "");
        response = StrUtil.replace(response, "';", "");
        List<String> list = StrUtil.split(response, "@", -1, true, true);
        stationDao.delete();
        list.forEach(item -> {
            List<String> stationInfo = StrUtil.split(item, '|');
            if (stationInfo.size() != 11) {
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

    /**
     * 余票查询
     * @param ticketDTO 查询条件
     */
    public void ticketList(TicketDTO ticketDTO) {
        String fromStationName = ticketDTO.getFromStationName();
        String toStationName = ticketDTO.getToStationName();
        String fromStationCode = stationDao.getStationCode(fromStationName);
        String toStationCode = stationDao.getStationCode(toStationName);
        ticketDTO.setPurposeCodes("ADULT");
        ticketDTO.setToStationCode(toStationCode);
        ticketDTO.setFromStationCode(fromStationCode);
        String url = this.getTicketUrl(ticketDTO);
        String response = HttpsUtil.httpGet(url);
        if (response == null) {
            logger.error("查询车次信息出错");
            return;
        }
        TicketReponse ticketReponse = GsonUtil.fromJson(response, TicketReponse.class);
        TicketReponse.Data data = ticketReponse.getData();
        //车站字典
        Map<String, Object> map = data.getMap();
        //车次信息列表
        List<String> result = data.getResult();
        if (result == null || result.isEmpty()) {
            logger.warn("车次信息列表为空");
            return;
        }
        List<TrainBO> trainList = this.getParseTrainInfo(result, map);

        logger.info("查询成功1");
        logger.info(response);
    }

    /**
     * 解析车次信息
     * @param trainList 解析前车次列表
     * @param stationMap 车站字典列表
     * @return 解析后的车次列表
     */
    private List<TrainBO> getParseTrainInfo(List<String> trainList, Map<String, Object> stationMap) {
        List<TrainBO> result = new ArrayList<>();
        for (String str : trainList) {
            String[] train = StrUtil.split(str, "|");
            TrainBO trainBO = new TrainBO();
            trainBO.setSecretStr(train[0]);
            trainBO.setButtonTextInfo(train[1]);
            trainBO.setTrainNo(train[2]);
            trainBO.setStationTrainCode(train[3]);
            trainBO.setStartStationTelecode(train[4]);
            trainBO.setEndStationTelecode(train[5]);
            trainBO.setFromStationTelecode(train[6]);
            trainBO.setToStationTelecode(train[7]);
            trainBO.setStartTime(train[8]);
            trainBO.setArriveTime(train[9]);
            trainBO.setLishi(train[10]);
            trainBO.setCanWebBuy(train[11]);
            trainBO.setYpInfo(train[12]);
            trainBO.setStartTrainDate(train[13]);
            trainBO.setTrainSeatFeature(train[14]);
            trainBO.setLocationCode(train[15]);
            trainBO.setFromStationNo(train[16]);
            trainBO.setToStationNo(train[17]);
            trainBO.setIsSupportCard(train[18]);
            trainBO.setControlledTrainFlag(train[19]);
            trainBO.setGgNum("".equals(train[20]) ? "--" : train[20]);
            trainBO.setGrNum("".equals(train[21]) ? "--" : train[21]);
            trainBO.setQtNum("".equals(train[22]) ? "--" : train[22]);
            trainBO.setRwNum("".equals(train[23]) ? "--" : train[23]);
            trainBO.setRzNum("".equals(train[24]) ? "--" : train[24]);
            trainBO.setTzNum("".equals(train[25]) ? "--" : train[25]);
            trainBO.setWzNum("".equals(train[26]) ? "--" : train[26]);
            trainBO.setYbNum("".equals(train[27]) ? "--" : train[27]);
            trainBO.setYwNum("".equals(train[28]) ? "--" : train[28]);
            trainBO.setYzNum("".equals(train[29]) ? "--" : train[29]);
            trainBO.setZeNum("".equals(train[30]) ? "--" : train[30]);
            trainBO.setZyNum("".equals(train[31]) ? "--" : train[31]);
            trainBO.setSwzNum("".equals(train[32]) ? "--" : train[32]);
            trainBO.setSrrbNum("".equals(train[33]) ? "--" : train[33]);
            trainBO.setYpEx(train[34]);
            trainBO.setSeatTypes(train[35]);
            trainBO.setExchangeTrainFlag(train[36]);
            trainBO.setHoubuTrainFlag(train[37]);
            trainBO.setHoubuSeatLimit(train[38]);
            trainBO.setYpInfoNew(train[39]);
            trainBO.setDwFlag(train[46]);
            trainBO.setStopcheckTime(train[48]);
            trainBO.setCountryFlag(train[49]);
            trainBO.setLocalArriveTime(train[50]);
            trainBO.setLocalStartTime(train[51]);
            trainBO.setBedLevelInfo(train[53]);
            trainBO.setSeatDiscountInfo(train[54]);
            trainBO.setSaleTime(train[55]);
            trainBO.setFromStationName(Convert.toStr(stationMap.get(train[6])));
            trainBO.setToStationName(Convert.toStr(stationMap.get(train[7])));
            result.add(trainBO);
        }
        return result;
    }

    private String getTicketUrl(TicketDTO ticketDTO) {
        String url = "https://kyfw.12306.cn/otn/leftTicket/query?";
        url += "leftTicketDTO.train_date=" + ticketDTO.getTrainDate() + "&leftTicketDTO.from_station=" + ticketDTO.getFromStationCode() + "&leftTicketDTO.to_station=" + ticketDTO.getToStationCode() + "&purpose_codes=" + ticketDTO.getPurposeCodes();
        return url;
    }

}
