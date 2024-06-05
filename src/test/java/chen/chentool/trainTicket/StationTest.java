package chen.chentool.trainTicket;

import chen.chentool.trainTicket.entity.TicketDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description: 车站地理信息
 * @author: sen
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StationTest {
    @Autowired
    private StationService stationService;

    @Test
    public void getSiteInfo() {
        stationService.getSiteInfo("");
    }
    @Test
    public void ticketList(){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTrainDate("2024-06-07");
        ticketDTO.setTrainStartTime("13:00");
        ticketDTO.setFromStationName("福州");
        ticketDTO.setToStationName("诏安");
        ticketDTO.setPurposeCodes("ADULT");
        stationService.ticketList(ticketDTO);
    }
}