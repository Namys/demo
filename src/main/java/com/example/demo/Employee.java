package com.example.demo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by Namys on 16.09.2017.
 */
public final class Employee {
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private int id;
    @JacksonXmlProperty(localName = "fio", isAttribute = true)
    private String fio;
    @JacksonXmlProperty(localName = "position", isAttribute = true)
    private String position;
    @JacksonXmlProperty(localName = "email", isAttribute = true)
    private String email;
    @JacksonXmlProperty(localName = "tel", isAttribute = true)
    private String tel;
    @JacksonXmlProperty(localName = "tel2", isAttribute = true)
    private String tel2;
    @JacksonXmlProperty(localName = "room", isAttribute = true)
    private String room;

    public Employee() {
    }

    public Employee(int id, String fio, String position, String email, String tel, String tel2, String room) {
        this.id = id;
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.tel2 = tel2;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", position='" + position + '\'' +
                ", email=" + email +
                ", tel=" + tel +
                ", tel2=" + tel2 +
                ", room=" + room +
                '}';
    }
}
