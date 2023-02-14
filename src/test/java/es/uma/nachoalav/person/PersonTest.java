package es.uma.nachoalav.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;

    @BeforeEach
    void start() {
        person = null;
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIsMinusOne() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person = new Person("Name",-1,"Male");
        });
    }
    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIs0() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person = new Person("Name",0,"Male");
        });
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIs131() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person = new Person("Name",131,"Female");
        });
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenGenderIsHello() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person = new Person("Name",30,"Hello");
        });
    }
}