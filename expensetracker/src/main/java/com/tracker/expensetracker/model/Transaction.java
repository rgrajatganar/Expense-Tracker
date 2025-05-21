package com.tracker.expensetracker.model;


import java.time.LocalDate;


public class Transaction {
	
	 
	
    private LocalDate date;
    private String type;
    private String category;
    private double amount;
    
 // Getters & Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }


    public Transaction() {
    	this.date = LocalDate.now();
    }

    public Transaction(LocalDate date, String type, String category, double amount) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

    
    @Override
    public String toString() {
        return date + "," + type + "," + category + "," + amount;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        return new Transaction(
            LocalDate.parse(parts[0]),
            parts[1],
            parts[2],
            Double.parseDouble(parts[3])
        );
    }
}


