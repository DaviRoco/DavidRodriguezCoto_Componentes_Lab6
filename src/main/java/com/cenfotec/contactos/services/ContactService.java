package com.cenfotec.contactos.services;

import com.cenfotec.contactos.domain.Contact;
import com.cenfotec.contactos.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ContactService {

    public List<Contact> getAll();
    public Optional<Contact> findById(long id);
    public  Optional<Contact> save(Contact contact);
    public  Optional<Contact> update(Contact contact);
    public boolean delete(Long id);

}
