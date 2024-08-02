package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Department;
import org.example.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        logger.info("Attempting to save department: {}", department);
        try {
            Department createdDepartment = departmentService.createDepartment(department);
            logger.info("Department saved successfully with ID: {}", createdDepartment.getDepartmentId());
            return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to save department: {}", department, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> fetchAllDepartments() {
        logger.info("Fetching all departments");
        List<Department> departments = departmentService.fetchDeparmentList();
        logger.info("Successfully fetched {} departments", departments.size());
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @PathVariable int id) {
        try {
            logger.info("Updating department with ID {}: {}", id, department);
            Department updatedDepartment = departmentService.updateDepartment(department, id);
            return ResponseEntity.ok(updatedDepartment);
        } catch (Exception e) {
            logger.error("Failed to update department with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating department: " + e.getMessage());
        }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable int id) {
        try {
            logger.info("Deleting department with ID {}", id);
            departmentService.deleteDepartmentById(id);
            return ResponseEntity.ok("Department deleted successfully");
        } catch (Exception e) {
            logger.error("Failed to delete department with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting department: " + e.getMessage());
        }
    }
}