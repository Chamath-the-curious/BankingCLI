package org.chamath.model;

import org.apache.commons.validator.routines.EmailValidator;
import org.chamath.exception.InvalidEmailException;

import java.util.concurrent.atomic.AtomicLong;

public class Customer {
    private final String customerId;
    private String name;
    private String email;
    private String phone;

    private static final AtomicLong counter = new AtomicLong(0);

    public Customer(String nicNumber, String name, String email, String phone) {
        this.customerId = nicNumber;
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (EmailValidator.getInstance().isValid(email)) {
            this.email = email;
        } else {
            throw new InvalidEmailException();
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "\nCustomer ID   : " + customerId +
                "\nName         : " + name +
                "\nEmail        : " + email +
                "\nPhone        : " + phone;
    }
}
