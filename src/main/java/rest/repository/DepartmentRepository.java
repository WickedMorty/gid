package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT d FROM Department d WHERE d.id = :id")
    public Department findById(@Param("id") Integer id);
}
