package com.example.demo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

        @Test
    public void contextLoads() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Employees value = new Employees();
        List<Employee> employees = Collections.singletonList(new Employee(1, "fio", "position", "email", "tel", "tel2", "1"));
        Unit o = new Unit();
        o.setName("unit");
        o.setEmployees(employees);
        value.setUnits(Collections.singletonList(o));
        xmlMapper.writeValue(new File("employees.xml"), value);
        File file = new File("employees.xml");
        assertNotNull(file);
    }

    @Test
    public void test2() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Employees value = xmlMapper.readValue(
                "<employees>\n" +
                        "\t<unit name=\"Руководство\">\n" +
                        "\t\t<employee id=\"17\" fio=\"Иванов Иван Иванович\" position=\"Генеральный директор\" email=\"ivanov@veterok.ru\" tel=\"+7 (495) 111-22-33\" tel2=\"+7 (910) 222-33-44\" room=\"23-К\"/>\n" +
                        "\t\t<employee id=\"18\" fio=\"Петров Петр Петрович\" position=\"Заместитель Генерального директора\" email=\"petrov@veterok.ru\" tel=\"+7 (495) 111-22-34\" room=\"24-К\"/>\n" +
                        "\t\t<employee id=\"19\" fio=\"Сидоров Сидор Сидорович\" position=\"Заместитель Генерального директора\" email=\"sidorov@veterok.ru\" tel=\"+7 (495) 111-22-35\" tel2=\"+7 (903) 777-88-99\" room=\"25-К\"/>\n" +
                        "\t<unit name=\"Департамент продаж\">\n" +
                        "\t\t<employee id=\"20\" fio=\"Николаев Николай Николаевич\" position=\"Директор департамента\" email=\"nikolaev@veterok.ru\" tel=\"+7 (495) 111-22-36\" room=\"26-К\"/>\n" +
                        "\t\t\n" +
                        "\t</unit>\n" +
                        "\t</unit>\n" +
                        "</employees>", Employees.class);
        assertNotNull(value);
    }
}
