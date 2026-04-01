package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getOnePerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found."));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Long id, Person person) {
        Person personToUpdate = this.getOnePerson(id);

        if(person.getName() != null) {
            personToUpdate.setName(person.getName());
        }
        if(person.getSurname() != null) {
            personToUpdate.setSurname(person.getSurname());
        }
    //TODO: is this safe to do? To be able to change your email just like that?
        if(person.getEmail() != null) {
            personToUpdate.setEmail(person.getEmail());
        }

        return personRepository.save(personToUpdate);
    }

    public void deleteOnePerson(Long id) {
        if(!personRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personRepository.deleteById(id);
    }

}
