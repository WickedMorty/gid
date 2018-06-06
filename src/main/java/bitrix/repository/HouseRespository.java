package bitrix.repository;

import bitrix.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRespository extends JpaRepository<House, Integer> {

    @Query("SELECT h FROM House h WHERE h.PROPERTY_1135 = :residentialId ORDER BY PROPERTY_1138 DESC")
    public List<House> findDeadline(@Param("residentialId") Integer residentialId);

    @Query("SELECT h FROM House h WHERE h.PROPERTY_1135 = :residentialId ORDER BY PROPERTY_1137 DESC")
    public List<House> findFloor(@Param("residentialId") Integer residentialId);
}
