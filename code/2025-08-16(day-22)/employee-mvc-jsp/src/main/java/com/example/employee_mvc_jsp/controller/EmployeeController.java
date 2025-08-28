package com.example.employee_mvc_jsp.controller;

import com.example.employee_mvc_jsp.entity.Address;
import com.example.employee_mvc_jsp.entity.Employee;
import com.example.employee_mvc_jsp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    @RequestMapping("/")
    public String index(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Employee> data = (q == null || q.isBlank()) ? service.findAll() : service.searchByName(q);
        model.addAttribute("employees", data);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("emp", service.findById(id));
        return "edit";
    }


    @PostMapping("/add")
    public String add(@RequestParam String name,
            @RequestParam String type,
            @RequestParam(required = false, name = "dept") String departmentName,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String zip) {

        Address a = new Address();
        a.setStreet(street);
        a.setCity(city);
        a.setState(state);
        a.setZip(zip);

        service.create(name, type, departmentName, a);
        return "redirect:/employees/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam(required = false, name = "dept") String departmentName,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String zip) {

        Address a = new Address();
        a.setStreet(street);
        a.setCity(city);
        a.setState(state);
        a.setZip(zip);

        service.update(id, name, type, departmentName, a);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/employees/";
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/api/search")
    @ResponseBody
    public ResponseEntity<List<Employee>> search(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
}
