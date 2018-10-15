package com.github.tantalor93.rest;

import com.github.tantalor93.model.Person;
import com.github.tantalor93.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Api(description = "Person operations")
public class PersonController {
    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "list created person objects")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "persons successfully listed",
                    response = Person.class,
                    responseContainer = "List")
    })
    @GetMapping("/persons")
    public Flux<Person> findAll() {
        return personService.findAll();
    }

    @ApiOperation(value = "create new person object")
    @ApiResponses({
            @ApiResponse(code = 200, message = "person object successfully created", response = Person.class)
    })
    @PostMapping("/persons")
    public Mono<Person> createPerson(@ApiParam(value = "person object to create", required = true) @RequestBody final Person person) {
        return personService.save(person);
    }
}
