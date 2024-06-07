package chen.chentool.trainTicket.entity;

import lombok.Data;

/**
 * @description: 车次信息
 * @author: sen
 */
@Data
public class TrainBO {
    private String secretStr;
    private String buttonTextInfo;
    /**
     * 车次编码，用于查询车次信息
     */
    private String trainNo;
    /**
     * 车次
     */
    private String stationTrainCode;
    private String startStationTelecode;
    private String endStationTelecode;
    private String fromStationTelecode;
    private String toStationTelecode;
    /**
     * 出发时间
     * HH:mm
     */
    private String startTime;
    /**
     * 到达时间
     * HH:mm
     */
    private String arriveTime;
    /**
     * 历时
     * HH:mm
     */
    private String lishi;
    private String canWebBuy;
    private String ypInfo;
    /**
     * 出发日期
     */
    private String startTrainDate;
    private String trainSeatFeature;
    private String locationCode;
    private String fromStationNo;
    private String toStationNo;
    private String isSupportCard;
    private String controlledTrainFlag;
    private String ggNum;
    private String grNum;
    private String qtNum;
    private String rwNum;
    private String rzNum;
    private String tzNum;
    /**
     * 无座
     */
    private String wzNum;
    private String ybNum;
    private String ywNum;
    /**
     * 一等座
     */
    private String yzNum;
    private String zyNum;
    /**
     * 二等座
     */
    private String zeNum;
    private String swzNum;
    private String srrbNum;
    private String ypEx;
    private String seatTypes;
    private String exchangeTrainFlag;
    private String houbuTrainFlag;
    private String houbuSeatLimit;
    private String ypInfoNew;
    private String dwFlag;
    private String stopcheckTime;
    private String countryFlag;
    private String localArriveTime;
    private String localStartTime;
    private String bedLevelInfo;
    private String seatDiscountInfo;
    private String saleTime;
    /**
     * 出发站
     */
    private String fromStationName;
    /**
     * 到达站
     */
    private String toStationName;
}
