package com.example.demo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Namys on 17.09.2017.
 */
@Service
public class ManagerEmployees {
    private Employees employees;
    private Map<String, Unit> unitMap;
    private Map<Integer, Employee> employeeMap;
    private Map<Integer, String> employeeRelUnitMap;
    private Map<String, String> unitRelUnitMap;
    private XmlMapper xmlMapper = new XmlMapper();
    private boolean hasGeneral;

    @PostConstruct
    public void init() throws IOException, ValidationException {
        unitMap = new HashMap<>();
        employeeMap = new HashMap<>();
        employeeRelUnitMap = new HashMap<>();
        unitRelUnitMap = new HashMap<>();
        File file = new File("employees.xml");
        if (!file.exists()) {
            String employees_file = System.getProperty("employees_File");
            file = new File(employees_file);
        }
        employees = xmlMapper.readValue(file, Employees.class);
        setMaps(employees.getUnits(), null);
        if (!hasGeneral) {
            throw new ValidationException("Нет генерального");
        }
    }

    private void setMaps(List<Unit> units, String unitName) throws ValidationException {
        for (Unit unit : units) {
            String name = unit.getName();
            if (unitMap.get(name) != null) {
                throw new ValidationException("повторяющееся подразделение");
            }
            unitMap.put(name, unit);
            for (Employee employee : unit.getEmployees()) {
                if (employee.getPosition().equals("Генеральный директор")) {
                    if (!name.equals("Руководство")) {
                        throw new ValidationException("Не назначено руководство");
                    }
                    if (hasGeneral) {
                        throw new ValidationException("Невозможно иметь несколько Ген.директоров");
                    }
                    hasGeneral = true;
                }
                validEmployee(employee);
                int id = employee.getId();
                if (employeeMap.get(id) != null) {
                    throw new ValidationException("повторяющийся id employee");
                }
                employeeMap.put(id, employee);
                employeeRelUnitMap.put(id, name);
            }
            unitRelUnitMap.put(name, unitName);
            List<Unit> list = unit.getUnits();
            if (list != null)
                setMaps(list, name);
        }
    }

    private void validEmployee(Employee employee) throws ValidationException {
        if (employee.getEmail() == null ||
                employee.getPosition() == null ||
                employee.getFio() == null ||
                employee.getId() == 0 ||
                employee.getRoom() == null ||
                employee.getTel() == null) {
            throw new ValidationException("незаполнены обязательные поля");
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void save() {
        try {
            xmlMapper.writeValue(new File("employees.xml"), employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee, String unitName) throws ValidationException {
        int id = employee.getId();
        if (employeeMap.get(id) != null) {
            throw new ValidationException("данный пользователь уже есть");
        }
        validEmployee(employee);
        Unit unit = unitMap.get(unitName);
        if (unit != null) {
            unit.getEmployees().add(employee);
            employeeMap.put(id, employee);
            employeeRelUnitMap.put(id, unitName);
        } else {
            throw new ValidationException("подразделение не найдено");
        }
    }

    public void updateEmployee(Employee employee) throws ValidationException {
        Employee oldEmployee = employeeMap.get(employee.getId());
        if (oldEmployee == null) {
            throw new ValidationException("пользователь не найден");
        }
        validEmployee(employee);
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setFio(employee.getFio());
        oldEmployee.setPosition(employee.getPosition());
        oldEmployee.setRoom(employee.getRoom());
        oldEmployee.setTel(employee.getTel());
        oldEmployee.setTel2(employee.getTel2());
    }

    public Employee getEmployee(int id) throws ValidationException {
        Employee employee = employeeMap.get(id);
        if (employee == null) {
            throw new ValidationException("пользователь не найден");
        }
        return employee;
    }

    public void delEmployee(int id) throws ValidationException {
        Employee employee = employeeMap.get(id);
        if (employee == null) {
            throw new ValidationException("пользователь не найден");
        }
        String unitName = employeeRelUnitMap.get(id);
        if (employee.getPosition().equals("Генеральный директор") && unitName.equals("Руководство")) {
            throw new ValidationException("Руководство нельзя удалить");
        }
        if (unitName == null) {
            throw new ValidationException("база не консистентна");
        }
        Unit unit = unitMap.get(unitName);
        if (unit != null) {
            unit.getEmployees().remove(employee);
            employeeRelUnitMap.remove(id);
            employeeMap.remove(id);
        } else {
            throw new ValidationException("подразделение не найдено");
        }
    }

    public void addUnit(String masterUnitName, String unitName) throws ValidationException {
        Unit e = new Unit(unitName);
        if (masterUnitName.isEmpty()) {
            employees.getUnits().add(e);
            unitRelUnitMap.put(unitName, null);
            unitMap.put(unitName, e);
            return;
        }
        if (unitMap.get(unitName) != null) {
            throw new ValidationException("подразделение уже существует");
        }
        Unit unit = unitMap.get(masterUnitName);
        if (unit != null) {
            List<Unit> units = unit.getUnits();
            units.add(e);
            unitRelUnitMap.put(unitName, masterUnitName);
            unitMap.put(unitName, e);
        }
    }

    public void delUnit(String unitName) throws ValidationException {
        Unit unit = unitMap.get(unitName);
        if (unit != null) {
            if (unitName.equals("Руководство")) {
                throw new ValidationException("Руководство нельзя удалить");
            }
            if (unit.getUnits().size() != 0 && unit.getEmployees().size() != 0) {
                throw new ValidationException("подразделение нельзя удалить");
            }
            if (!unitRelUnitMap.containsKey(unitName)) {
                throw new ValidationException("база не консистентна");
            }
            String s = unitRelUnitMap.get(unitName);
            if (s == null) {
                employees.getUnits().remove(unit);
            } else {
                unitMap.get(s).getUnits().remove(unit);
            }
            unitRelUnitMap.remove(unitName);
            unitMap.remove(unitName);
        } else {
            throw new ValidationException("подразделение не найдено");
        }
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public Set<String> getAllUnits() {
        return unitMap.keySet();
    }
}
