package main.java.com.hms.model;

import java.time.LocalDate;

public class Bill {
    private int id;
    private int patientId;
    private double amount;
    private boolean paid;
    private LocalDate date;

    public Bill() {}

    public Bill(int id, int patientId, double amount, boolean paid, LocalDate date) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.paid = paid;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
