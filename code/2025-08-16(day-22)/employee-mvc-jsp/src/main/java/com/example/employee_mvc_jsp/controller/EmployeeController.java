package com.example.employee_mvc_jsp.controller;

import com.example.employee_mvc_jsp.entity.Address;
import com.example.employee_mvc_jsp.entity.Employee;
import com.example.employee_mvc_jsp.service.EmployeeService;
// import jakarta.persistence.EntityNotFoundException;
// import org.springframework.http.HttpStatus;
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

    // ---------- JSP Views ----------
    @GetMapping("/")
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

/*************  ✨ Windsurf Command ⭐  *************/
    /**
     * Handles the form submission for adding a new employee.
     *
     * @param name the name of the employee
     * @param type the type of the employee
     * @param departmentName the name of the department (optional)
     * @param street the street address of the employee's address (optional)
     * @param city the city of the employee's address (optional)
     * @param state the state of the employee's address (optional)
     * @param zip the zip code of the employee's address (optional)
     * @return a redirect response to the index page
     */
/*******  e248c1db-362f-4aa8-85d3-66606760c67a  *******/    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String type,
                      @RequestParam(required = false, name="dept") String departmentName,
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
                         @RequestParam(required = false, name="dept") String departmentName,
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

    // ---------- REST APIs (ResponseEntity) ----------
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
