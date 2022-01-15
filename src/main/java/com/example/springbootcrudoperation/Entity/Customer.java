package com.example.springbootcrudoperation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table
public class Customer{


    @Id
    private  int customerId;
    @Column
    private String Fname;
    @Column
    private String Lname;
    @Column
    private String Gender;
    @Column
    private long phone;
    @Column
    private String email;
    @Column
    private boolean Is_Active;

    public Customer(int customerId, String fname, String lname, String gender, long phone, String email, boolean is_Active, Set<Address> addresses) {
        this.customerId = customerId;
        Fname = fname;
        Lname = lname;
        Gender = gender;
        this.phone = phone;
        this.email = email;
        Is_Active = is_Active;
        this.addresses = addresses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    private Set<Address> addresses = new HashSet<>();


    public Customer() {
        super();
    }

    public Customer(int customerId, String fname, String lname, String gender, long phone, String email, boolean is_Active) {
        this.customerId = customerId;
        Fname = fname;
        Lname = lname;
        Gender = gender;
        this.phone = phone;
        this.email = email;
        Is_Active = is_Active;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getGender() {
        return Gender;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIs_Active() {
        return Is_Active;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        for(Address b : addresses) {
            b.setCustomer(this);
        }
    }
}
