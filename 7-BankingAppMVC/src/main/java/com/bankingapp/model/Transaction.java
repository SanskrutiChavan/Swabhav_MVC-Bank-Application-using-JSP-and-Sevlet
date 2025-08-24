package com.bankingapp.model;

//import java.util.Date;
//
//public class Transaction {
//    private int id;
//    private String fromAccount;
//    private String toAccount;
//    private String type; // CREDIT or TRANSFER
//    private double amount;
//    private Date timestamp;
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getFromAccount() { return fromAccount; }
//    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }
//
//    public String getToAccount() { return toAccount; }
//    public void setToAccount(String toAccount) { this.toAccount = toAccount; }
//
//    public String getType() { return type; }
//    public void setType(String type) { this.type = type; }
//
//    public double getAmount() { return amount; }
//    public void setAmount(double amount) { this.amount = amount; }
//
//    public Date getTimestamp() { return timestamp; }
//    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
//}



//----with description -------


import java.sql.Timestamp;

public class Transaction {
    private int id;
    private String fromAccount;
    private String toAccount;
    private String type;
    private double amount;
    private String description;
    private Timestamp timestamp;

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
