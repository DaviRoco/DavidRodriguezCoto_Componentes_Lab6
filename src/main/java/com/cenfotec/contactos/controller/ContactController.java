package com.cenfotec.contactos.controller;

import com.cenfotec.contactos.domain.Contact;
import com.cenfotec.contactos.repositories.ContactRepository;
import com.cenfotec.contactos.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List getAll(){
        return contactService.getAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        Optional<Contact> result = contactService.findById(id);
        if (result.isPresent()){
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return contactService.save(contact).get();
    }

//    @PutMapping(value="/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                          @RequestBody Contact contact){
        contact.setId(id);
        Optional<Contact> result = contactService.update(contact);
        if (result.isPresent()){
            return ResponseEntity.ok().body(result.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (contactService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
//Preguntas:
//    a. Cuál es la diferencia en la práctica entre @Controller y @RestController?
//          @Controller es usada para establecer una clase como Controller en SpringMVC mientras @RestController es un controller especial en RESTful y equivale a la suma de @Controller y @ResponseBody.
//    b. Cuál es mejor usar en qué casos?
//          @RestController es mejor usarlo cuando se requiere de la metodología REST mientras que @Controller indica que la clase es un controlador web
//    c. Por qué en el findAll no se especifica el URI para la operación?
//        En findAll no se especifica el URI porque esa es la manera default, no se utilizan recursos.
//    d. Qué obtiene si trata de borrar 2 veces un recurso?
//        Nada, la pantalla sale en negro.
//    e. Comente la anotación de PutMapping del método que hace el update. Reinicie la aplicación. Observe que pasa si lo trata de hacer.
//        El método no sirve ya que el request no existe dentro del Controller.
//    f. Rehabilite la anotación en PutMapping. Pero ahora cambie el URL de POSTMAN para que apunte directamente a la colección /contacts. ¿Qué error recibe?
//        Error 405

