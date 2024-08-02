package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Department;
import org.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        logger.info("Creating department: {}", department);
        Department createdDepartment = departmentRepository.save(department);
        logger.info("Department created with ID: {}", createdDepartment.getDepartmentId());
        return createdDepartment;
    }

    @Override
    public List<Department> fetchDeparmentList() {
        logger.info("Fetching list of all departments");
        List<Department> departments = departmentRepository.findAll();
        logger.debug("Fetched {} departments", departments.size());
        return departments;
    }


    @Override
    public Department updateDepartment(Department department, int id) {
        logger.info("Updating department with ID: {}", id);
        Optional<Department> existingDepartment = departmentRepository.findById(id);

        if (existingDepartment.isPresent()) {
            Department updatedDepartment = existingDepartment.get();
            updatedDepartment.updateFrom(department);
            departmentRepository.save(updatedDepartment);
            logger.info("Department with ID {} updated successfully", id);
            return updatedDepartment;
        } else {
            logger.error("No department found with ID: {}", id);
            throw new RuntimeException("Department not found");
        }
    }

    @Override
    public void deleteDepartmentById(int id) {
        logger.info("Deleting department with ID: {}", id);
        departmentRepository.deleteById(id);
        logger.info("Department with ID {} deleted successfully", id);
    }
}