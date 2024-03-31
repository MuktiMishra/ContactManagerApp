package com.example.contactmanagerapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {

    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    //This creates the db column
    // as this name if you'll not specify toh variable ke name jaisa bn jaega
    private int Contact_Id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;

    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Contacts(){};

    public int getContact_Id() {
        return Contact_Id;
    }

    public void setContact_Id(int contact_Id) {
        Contact_Id = contact_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
