package com.service.jpqldemo.module.v1.employee.usecase;

import com.service.jpqldemo.exception.ResourceNotFound;
import com.service.jpqldemo.module.v1.employee.dao.EmployeeDao;
import com.service.jpqldemo.module.v1.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeUseCaseImpl implements EmployeeUseCase {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDao> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Long countAllEmployees() {
        return employeeRepository.count();
    }

    @Override
    public Optional<EmployeeDao> findByEmployeeId(Long id) {
        Optional<EmployeeDao> employeeDaoById = Optional.ofNullable(employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee not found with id " + id)));
        return employeeDaoById;
    }

    @Override
    public EmployeeDao createNewEmployee(EmployeeDao employeeDaoPayload) {
        return employeeRepository.save(employeeDaoPayload);
    }

    @Override
    public EmployeeDao updateEmployee(Long id, EmployeeDao employeeDaoPayload) {
        EmployeeDao employeeDaoFindById = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee not found with id " + id));

        employeeDaoFindById.setEmployeeName(employeeDaoPayload.getEmployeeName());
        employeeDaoFindById.setEmployeeAge(employeeDaoPayload.getEmployeeAge());
        employeeDaoFindById.setEmployeeAddress(employeeDaoPayload.getEmployeeAddress());
        employeeRepository.save(employeeDaoFindById);

        return employeeDaoFindById;
    }

    @Override
    public EmployeeDao deleteEmployee(Long id) {
        EmployeeDao employeeDaoFindById = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Employee not found with id " + id));

        employeeRepository.delete(employeeDaoFindById);

        return employeeDaoFindById;
    }
}
