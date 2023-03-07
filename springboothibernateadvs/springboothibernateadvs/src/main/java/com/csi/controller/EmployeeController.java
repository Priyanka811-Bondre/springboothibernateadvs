package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Done Successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {
        employeeServiceImpl.saveBulkOfData(employees);
        return ResponseEntity.ok("Bulk of Data Saved Successfully");
    }

    @GetMapping("/getdatabyusinganyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByUsingAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByUsingAnyInput(input));
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpName().equals(empName)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<List<Employee>> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpContactNumber() == empContactNumber).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<List<Employee>> getDatabyEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()));

    }

    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity<List<Employee>> getDataByDOB(@PathVariable Date empDOB) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpDOB().equals(empDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyname")
    public ResponseEntity<List<Employee>> sortDataByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyid")
    public ResponseEntity<List<Employee>> sortDataById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingLong(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabysalary")
    public ResponseEntity<List<Employee>> sortDataBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary() >= empSalary).collect(Collectors.toList()));

    }

    @PutMapping("/updatedata")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {
        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("Data Updated Successfully");
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}