package com.cenfotec.contactos.services;


import com.cenfotec.contactos.domain.Contact;
import com.cenfotec.contactos.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepo;

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Optional<Contact> findById(long id) {
        return contactRepo.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public  Optional<Contact> save(Contact contact) {
        return Optional.of(contactRepo.save(contact));
    }

    @Override
    public  Optional<Contact> update(Contact contact) {
        Optional<Contact> record = contactRepo.findById(contact.getId());
        if (record.isPresent()) {
            Contact data = record.get();
            data.setName(contact.getName());
            data.setEmail(contact.getEmail());
            data.setPhone(contact.getPhone());
            return Optional.of(contactRepo.save(data));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Contact> result = contactRepo.findById(id);
        if (result.isPresent()){
            contactRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
