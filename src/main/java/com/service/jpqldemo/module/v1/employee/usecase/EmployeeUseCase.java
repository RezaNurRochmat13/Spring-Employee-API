package com.service.jpqldemo.module.v1.employee.usecase;

import com.service.jpqldemo.module.v1.employee.dao.EmployeeDao;

import java.util.List;
import java.util.Optional;

public interface EmployeeUseCase {
    List<EmployeeDao> findAllEmployee();
    Long countAllEmployees();
    Optional<EmployeeDao> findByEmployeeId(Long id);
    EmployeeDao createNewEmployee(EmployeeDao employeeDaoPayload);
    EmployeeDao updateEmployee(Long id, EmployeeDao employeeDaoPayload);
    EmployeeDao deleteEmployee(Long id);
}
