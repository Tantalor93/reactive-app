package com.github.tantalor93.service;

import com.github.tantalor93.model.Person;
import com.github.tantalor93.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> save(final Person person) {
        return personRepository.save(person);
    }

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }
}
