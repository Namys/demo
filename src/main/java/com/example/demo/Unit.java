package com.example.demo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namys on 16.09.2017.
 */
//@JacksonXmlRootElement(localName = "unit")
public class Unit {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "employee")
    private List<Employee> employees = new ArrayList<>();
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "unit")
    private List<Unit> units = new ArrayList<>();
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    public Unit() {
    }

    public Unit(String unitName) {
        this.name = unitName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "employees=" + employees +
                ", units=" + units +
                ", name='" + name + '\'' +
                '}';
    }
}
