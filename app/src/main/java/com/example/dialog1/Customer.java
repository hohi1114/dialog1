package com.example.dialog1;

public class Customer {
    String name;
    String phone;
    String gender;

    Customer(String name, String phone, String gender){
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public String getPhone(){
        return phone;
    }

    public void setName(String Name){
        name = Name;
    }
    public void setGender(String Gender){
        gender = Gender;
    }
    public void setPhone(String Phone){
        phone = Phone;
    }
}
