package bitrix.repository.param;

import bitrix.entity.param.Securities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuritiesRepository extends JpaRepository<Securities, Integer> {
}
