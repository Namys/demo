package com.example.demo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Namys on 16.09.2017.
 */
@JacksonXmlRootElement(localName = "employees")
public final class Employees {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "unit")
    private List<Unit> units;

    public Employees() {
    }

    public Employees(List<Unit> units) {
        this.units = units;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "units=" + units +
                '}';
    }
}
