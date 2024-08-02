package org.example.service;

import org.example.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

     Department createDepartment(Department department);
     List<Department> fetchDeparmentList();

    Department updateDepartment(Department department, int id);
     void deleteDepartmentById(int id);

}
