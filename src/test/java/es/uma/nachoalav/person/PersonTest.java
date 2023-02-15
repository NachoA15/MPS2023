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
 *  6. Creating a person called 'Test' then getting its name -> Should return 'Test'
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
 *  11. Calculating the mean age per gender when there are just three females on the list:
 *      one with age 19, other with age 45 and another with age 33
 *      -> female mean is 32.33
 *  12. In this case we have two males and two females.
 *      Males: one with age 18 and another with age 43 -> mean age should be 30,5
 *      Females: one with age 53 and another with age 21 -> mean age should be 37,0
 */

class PersonTest {
    private Person person1, person2, person3, person4;
    private List<Person> persons;

    @BeforeEach
    void start() {
        person1 = null;
        person2 = null;
        person3 = null;
        person4 = null;
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
            person1 = new Person("",30,"Male");
        });
    }

    /**
     * NOTE: we won't test explicitly the other getters because they are covered in other sections
     * of the code. The getName() method is actually the only one that isn't called at any point.
     * In short, this test makes the coverage of the code reach 100%.
     */
    @Test
    void getNameMethodAppliedToAPersonShouldReturnTheNameOfSaidPerson() {
        person1 = new Person("Test",30,"Female");
        String expectedString = "Test";
        String actualString = person1.getName();
        assertEquals(expectedString,actualString);
    }

    /**
     * In this case we have an empty list of persons, so the mean age for both genders should be 0
     */
    @Test
    void meanAgeShouldBeZeroForBothGendersWhenEmptyList() {
        double[] obtainedArray = Person.averageAgePerGender(persons);
        double[] expectedArray = {0.0, 0.0};
        assertArrayEquals(expectedArray,obtainedArray);
    }

    /**
     * In this case we don't have any males in the list, so the main male age should be 0
     */
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

    /**
     * In this case we don't have any females in the list, so the mean female age should be 0
     */
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

    /**
     * In this case we have persons males and females, so the mean age must be greater than 0
     * for both genders
     */
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

    /**
     * In this case we have three males: one with age 30, other with age 63 and
     * another with age 27, so the mean male age should be 40. We don't have any females.
     */
    @Test
    void maleMeanShouldBe40WhenThereAreJustThreeMalesOnListWithAges30And63And27() {
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

    /**
     * In this case we have three females: one with age 19, other with age 45 and
     * another with age 33, so the mean female age should be 32,33. We don't have any males.
     */
    @Test
    void femaleMeanShouldBe32_33WhenThereAreJustThreeFemalesOnListWithAges19And45And33() {
        person1 = new Person("P1",19,"Female");
        person2 = new Person("P2",45,"Female");
        person3 = new Person("P3",33,"Female");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        double expectedValue = 32.33;
        double[] obtainedArray = Person.averageAgePerGender(persons);

        assertEquals(expectedValue,obtainedArray[1]);
    }

    /**
     * In this case we have two males and two females.
     * Males: one with age 18 and another with age 43 -> mean age should be 30,5
     * Females: one with age 53 and another with age 21 -> mean age should be 37,0
     */
    @Test
    void averageAgePerGenderMethodGivesCorrectOutputToTheCaseDescribedAbove() {
        person1 = new Person("P1",18,"Male");
        person2 = new Person("P2",53,"Female");
        person3 = new Person("P3",21,"Female");
        person4 = new Person("P4",43,"Male");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);

        double[] expectedArray = {30.5, 37.0};
        double[] obtainedArray = Person.averageAgePerGender(persons);

        assertArrayEquals(expectedArray,obtainedArray);
    }
}