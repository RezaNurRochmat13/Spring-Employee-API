package com.service.jpqldemo.module.v1.employee.repository;

import com.service.jpqldemo.module.v1.employee.dao.EmployeeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long> {
}
