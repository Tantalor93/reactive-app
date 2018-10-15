package com.github.tantalor93.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@ApiModel(value = "Person", description = "representation of person")
public class Person {

    @Id
    private String id;

    @ApiModelProperty( value = "name of the person", required = true )
    private String name;

    @ApiModelProperty( value = "email of the person", required = true)
    private String email;

    public Person(final String id, final String name, final String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    protected Person() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
