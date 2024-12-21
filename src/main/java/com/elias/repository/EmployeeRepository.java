package com.elias.repository;

import com.elias.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //select * from table where name = "{name}";
    List<Employee> findByName(String name);

    // select * from table where name = "{name}" and location="{location}"
    List<Employee> findByNameAndLocation(String name, String location);

    //select * from table where name like "%name%";
    List<Employee> findByNameContaining(String keyword, Sort sort);

    //we can do the same, but theres a catch
    // the porcentages must be wrapped before passed to the method variables
    List<Employee> findByNameLike(String keyword);

    @Query("FROM Employee WHERE name=:name AND location=:location")
    List<Employee> getEmployeesByNameAndLocaiton(@Param("name") String name, @Param("location") String location);

    @Modifying //this should be use for creating,udpating and deleting
    @Transactional// and also this
    @Query("DELETE FROM Employee where id=:id")
    Integer deleteEmployeeById(@Param("id") Long id);


}
