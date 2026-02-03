package main.java.com.hms.model;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String name;
    private String gender;
    private LocalDate dob;
    private String phone;
    private String address;

    public Patient() {}

    public Patient(int id, String name, String gender, LocalDate dob, String phone, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
