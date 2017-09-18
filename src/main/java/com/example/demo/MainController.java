package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;

/**
 * Created by Namys on 17.09.2017.
 */
@Controller
public class MainController {
    @Autowired
    private ManagerEmployees managerEmployees;

    @GetMapping("/employeeList")
    public ModelAndView employeeList(ModelAndView modelAndView) {
        modelAndView.addObject("employees", managerEmployees.getEmployees());
        modelAndView.addObject("allUnits", managerEmployees.getAllUnits());
        modelAndView.setViewName("employeeList");
        return modelAndView;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public ModelAndView addEmployee(ModelAndView modelAndView, @RequestParam String unitName) {
        modelAndView.addObject("employee", new Employee());
        modelAndView.setViewName("employeePage");
        modelAndView.addObject("unitName", unitName);
        return modelAndView;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee, String unitName) throws ValidationException {
        managerEmployees.addEmployee(employee, unitName);
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
    public ModelAndView updateEmployee(ModelAndView modelAndView, @RequestParam int id) throws ValidationException {
        modelAndView.addObject("employee", managerEmployees.getEmployee(id));
        modelAndView.setViewName("employeeUpdatePage");
        return modelAndView;
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("employee") Employee employee) throws ValidationException {
        managerEmployees.updateEmployee(employee);
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam int id) throws ValidationException {
        managerEmployees.delEmployee(id);
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/addUnit", method = RequestMethod.GET)
    public String addUnit(@RequestParam String unitMasterName, @RequestParam String unitName) throws ValidationException {
        managerEmployees.addUnit(unitMasterName, unitName);
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/delUnit", method = RequestMethod.GET)
    public String delUnit(@RequestParam String unitName) throws ValidationException {
        managerEmployees.delUnit(unitName);
        return "redirect:/employeeList";
    }
}
