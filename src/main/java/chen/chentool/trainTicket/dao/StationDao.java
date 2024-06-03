package chen.chentool.trainTicket.dao;

import chen.chentool.trainTicket.entity.StationEntity;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: sen
 */
@Repository
public interface StationDao {


    void delete();

    void insert(StationEntity stationEntity);
}
