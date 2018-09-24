package com.abcbank.repository;

import com.abcbank.model.Contact;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    
    @Query("SELECT c FROM Contact c WHERE LOWER(c.firstName) = LOWER(:firstName) and LOWER(c.secondName) = LOWER(:secondName) ")
    public List<Contact> find(@Param("firstName") String firstName, @Param("secondName") String secondName);
}
