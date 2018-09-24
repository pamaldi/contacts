package com.abcbank.model;

import java.time.LocalDate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Contact {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String secondName;
        private LocalDate birthDate;
        private String homeAddress;
        private String workAddress;
        private String workPhone;
        private String homePhone;

    public Contact(String firstName, String secondName, LocalDate birthDate, String homeAddress, String workAddress, String workPhone, String homePhone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
    }

	
}
