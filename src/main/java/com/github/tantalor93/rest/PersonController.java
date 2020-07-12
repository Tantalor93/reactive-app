package com.github.tantalor93.rest;

import java.util.concurrent.TimeUnit;

import com.github.tantalor93.model.Person;
import com.github.tantalor93.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@Api(description = "Person operations")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

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

    @GetMapping("/benky")
    public Mono<String> get() {
        return Mono.just("ahoj")
                .map(String::toUpperCase)
                .flatMap(e -> sleep(e));
    }

    private Mono<String> sleep(String s) {
        return Mono.just(s)
                .map(e -> {
                    try {
                         TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    LOGGER.info("sleep done '{}'", s);
                    return s + "1";
                }).subscribeOn(Schedulers.parallel());
    }
}
