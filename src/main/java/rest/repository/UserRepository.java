package rest.repository;

import rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE md5(CONCAT(LOWER(u.login), LOWER(u.password))) = :mdsumm")
    public User findByMd5(@Param("mdsumm") String mdsumm);

}
