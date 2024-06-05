package chen.chentool.trainTicket.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: 余票查询结果
 * @author: sen
 */
@Data
public class TicketReponse {
    private Integer httpstatus;
    private String message;
    private Boolean status;
    private Data data;
    @lombok.Data
    public static class Data{
        private String flag;
        private String level;
        private String sametlc;
        private List<String> result;
        private Map<String,Object> map;
    }
}
