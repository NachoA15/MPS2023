package es.uma.nachoalav.person;

/**
 * Class representing a person with a name, age and gender.
 *
 * @author Nacho Alba
 */
public class Person {
    private final String name;
    private final int age;
    private final String gender;

    /**
     * Constructs a person with a name, age and gender.
     *
     * @param name
     * @param age
     * @param gender
     */
    public Person(String name, int age, String gender) {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    /**
     * Computes the average age of male and female persons in a list and returns the result in
     * an array of two elements (the first element is the male mean age and the second one is the
     * female mean age)
     *
     * @param persons
     * @return
     */
    public double[] averageAgePerGender(List<Person> persons) {
        return null;
    }
}
