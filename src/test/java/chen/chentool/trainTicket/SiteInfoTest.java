package chen.chentool.trainTicket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @description: 车站地理信息
 * @author: sen
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SiteInfoTest {
    @Autowired
    private SiteInfo siteInfo;

    @Test
    public void getSiteInfo() {
        siteInfo.getSiteInfo("");
    }
}