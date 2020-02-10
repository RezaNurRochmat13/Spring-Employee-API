package com.service.jpqldemo.module.v1.employee.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class EmployeeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long employeeId;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "age")
    private Integer employeeAge;

    @Column(name = "address")
    private String employeeAddress;

}
