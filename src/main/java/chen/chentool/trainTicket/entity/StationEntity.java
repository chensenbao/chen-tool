package chen.chentool.trainTicket.entity;

import lombok.Data;

/**
 * @description: 车站实体
 * 例子：@bjb|北京北|VAP|beijingbei|bjb|0|0357|北京
 * @author: sen
 */
@Data
public class StationEntity {
    private Integer id;
    /**
     * 车站名称
     */
    private String stationName;
    /**
     * 车站编码
     */
    private String stationCode;
    /**
     * 车站拼音
     */
    private String stationPinyin;
    /**
     * 车站拼音缩写
     */
    private String stationPinyinSimple;
    /**
     * 车站所属城市
     */
    private String stationCity;
    /**
     * 车站原始信息
     */
    private String stationInfo;
    /**
     * 创建时间
     */
    private String createTime;
}
