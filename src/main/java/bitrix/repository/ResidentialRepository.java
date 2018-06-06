package bitrix.repository;

import bitrix.entity.Residential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentialRepository extends JpaRepository<Residential, Integer> {
}
