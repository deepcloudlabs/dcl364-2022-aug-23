package com.example.crm.entity;

// immutable class
public final record Customer(String identity,String fullname,String email,String phone) {

}
