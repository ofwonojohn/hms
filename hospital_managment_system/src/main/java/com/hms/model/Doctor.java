package main.java.com.hms.model;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private int departmentId;

    public Doctor() {}

    public Doctor(int id, String name, String specialization, int departmentId) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.departmentId = departmentId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
}
