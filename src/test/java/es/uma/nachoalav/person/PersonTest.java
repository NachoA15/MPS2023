package es.uma.nachoalav.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test Cases
 *
 *  1. Creating a person with age -1 -> Raise InvalidPersonArgumentException
 *  2. Creating a person with age 0 -> Raise InvalidPersonArgumentException
 *  3. Creating a person with age 131 -> Raise InvalidPersonArgumentException
 *  4. Creating a person with gender 'hello' -> Raise InvalidPersonArgumentException
 *  5. Creating a person with an empty name -> Raise InvalidPersonArgumentException
 *  6. Calculating the mean age per gender when there are no persons on the list
 *      -> mean is 0.0 for both genders
 *  7. Calculating the mean age per gender when there are only males on the list
 *      -> mean is 0.0 for females
 *  8. Calculating the mean age per gender when there are only females on the list
 *      -> mean is 0.0 for males
 *  9. Calculating the mean age per gender when there are males and females on the list
 *      -> mean is not 0.0 for any gender
 *  10. Calculating the mean age per gender when there are just three males on the list:
 *      one with age 30, other with age 63 and another with age 27
 *      -> male mean is 40.0
 */

class PersonTest {
    private Person person1, person2, person3;
    private List<Person> persons;

    @BeforeEach
    void start() {
        person1 = null;
        person2 = null;
        person3 = null;
        persons = new ArrayList<>();
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIsMinusOne() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person1 = new Person("Name",-1,"Male");
        });
    }
    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIs0() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person1 = new Person("Name",0,"Male");
        });
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenAgeIs131() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person1 = new Person("Name",131,"Female");
        });
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenGenderIsHello() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person1 = new Person("Name",30,"Hello");
        });
    }

    @Test
    void shouldRaiseExceptionConstructorOfPersonWhenNameIsEmpty() {
        assertThrows(InvalidPersonArgumentException.class, () -> {
            person1 = new Person("",30,"Hello");
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
        person1 = new Person("P1",30,"Female");
        person2 = new Person("P2",31,"Female");
        person3 = new Person("P3",32,"Female");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        double[] obtainedArray = Person.averageAgePerGender(persons);
        double expectedValue = 0.0;

        assertEquals(expectedValue, obtainedArray[0]);
    }

    @Test
    void meanFemaleAgeShouldBeZeroWhenPersonsOnListAreAllMale() {
        person1 = new Person("P1",30,"Male");
        person2 = new Person("P2",31,"Male");
        person3 = new Person("P3",32,"Male");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        double[] obtainedArray = Person.averageAgePerGender(persons);
        double expectedValue = 0.0;

        assertEquals(expectedValue, obtainedArray[1]);
    }

    @Test
    void meanShouldNotBeZeroForAnyGenderWhenThereAreMalesAndFemalesOnList() {
        person1 = new Person("P1",30,"Male");
        person2 = new Person("P2",49,"Female");
        person3 = new Person("P3",32,"Male");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        double[] obtainedArray = Person.averageAgePerGender(persons);
        double unexpectedValue = 0.0;

        assertNotEquals(unexpectedValue, obtainedArray[0]);
        assertNotEquals(unexpectedValue, obtainedArray[1]);
    }

    @Test
    void maleMeanShouldBe40WhenThereAreMalesOnListWithAges30And63And27() {
        person1 = new Person("P1",30,"Male");
        person2 = new Person("P2",63,"Male");
        person3 = new Person("P3",27,"Male");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        double expectedValue = 40.0;
        double[] obtainedArray = Person.averageAgePerGender(persons);

        assertEquals(expectedValue,obtainedArray[0]);
    }
}