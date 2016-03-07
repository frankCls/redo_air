package com.realdolmen.tickets.rs;

import com.realdolmen.tickets.domain.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonList {

    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonList() {
    }

    public PersonList(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
