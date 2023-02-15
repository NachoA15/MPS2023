package es.uma.nachoalav.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test Cases
 *
 *  1. Creating a person with age -1 -> Raise InvalidPersonArgumentException
 *  2. Creating a person with age 0 -> Raise InvalidPersonArgumentException
 *  3. Creating a person with age 131 -> Raise InvalidPersonArgumentException
 *  4. Creating a person with gender 'hello' -> Raise InvalidPersonArgumentException
 */

class PersonTest {
    private Person person;
    private List<Person> persons;

    @BeforeEach
    void start() {
        person = null;
        persons = new ArrayList<>();
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

    @Test
    void meanAgeShouldBeZeroForBothGendersWhenEmptyList() {
        double[] obtainedArray = Person.averageAgePerGender(persons);
        double[] expectedArray = {0.0, 0.0};
        assertArrayEquals(expectedArray,obtainedArray);
    }

    @Test
    void meanMaleAgeShouldBeZeroWhenPersonsOnListAreAllFemale() {
        Person p1 = new Person("P1",30,"Female");
        Person p2 = new Person("P2",31,"Female");
        Person p3 = new Person("P3",32,"Female");

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        double[] obtainedArray = Person.averageAgePerGender(persons);
        double expectedValue = 0.0;

        assertEquals(expectedValue, obtainedArray[0]);
    }

    @Test
    void meanFemaleAgeShouldBeZeroWhenPersonsOnListAreAllMale() {
        Person p1 = new Person("P1",30,"Male");
        Person p2 = new Person("P2",31,"Male");
        Person p3 = new Person("P3",32,"Male");

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        double[] obtainedArray = Person.averageAgePerGender(persons);
        double expectedValue = 0.0;

        assertEquals(expectedValue, obtainedArray[1]);
    }


}