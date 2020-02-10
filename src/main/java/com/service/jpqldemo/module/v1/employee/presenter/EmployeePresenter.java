package com.service.jpqldemo.module.v1.employee.presenter;

import com.service.jpqldemo.module.v1.employee.dao.EmployeeDao;
import com.service.jpqldemo.module.v1.employee.usecase.EmployeeUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class EmployeePresenter {
    @Autowired
    private EmployeeUseCaseImpl employeeUseCase;

    @GetMapping("employees")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAllEmployeesHandler() {
        Map<String, Object> map = new HashMap<>();
        List<EmployeeDao> employeeDaoList = employeeUseCase.findAllEmployee();
        Long countAllEmployee = employeeUseCase.countAllEmployees();
        map.put("count", employeeDaoList.size());
        map.put("total", countAllEmployee);
        map.put("data", employeeDaoList);
        return map;
    }

    @GetMapping("employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getSingleEmployeeHandler(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<EmployeeDao> employeeDaoFindById = employeeUseCase.findByEmployeeId(id);
        map.put("data", employeeDaoFindById);
        return map;
    }

    @PostMapping("employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createNewEmployeeHandler(@RequestBody EmployeeDao employeeDaoPayload) {
        Map<String, Object> map = new HashMap<>();
        EmployeeDao employeeDao = employeeUseCase.createNewEmployee(employeeDaoPayload);
        map.put("message", "Employee created successfully");
        map.put("created_data", employeeDao);
        return map;
    }

    @PutMapping("employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateEmployeeHandler(@PathVariable Long id,
                                                     @RequestBody EmployeeDao employeeDaoPayload) {
        Map<String, Object> map = new HashMap<>();
        EmployeeDao employeeDao = employeeUseCase.updateEmployee(id, employeeDaoPayload);
        map.put("message", "Employee update successfully");
        map.put("updated_data", employeeDao);
        return map;
    }

    @DeleteMapping("employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> deleteEmployeeHandler(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        EmployeeDao employeeDao = employeeUseCase.deleteEmployee(id);
        map.put("message", "Employee deleted successfully");
        map.put("deleted_data", employeeDao);
        return map;
    }
}
