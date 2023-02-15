package es.uma.nachoalav.person;

import java.util.List;

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
     * @param name the name of the person
     * @param age the age of the person
     * @param gender the gender of the person
     */
    public Person(String name, int age, String gender) {
        if (age <= 0 || age > 130) {
            throw new InvalidPersonArgumentException("Age must be between 1 and 130");
        }
        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            throw new InvalidPersonArgumentException("Gender must be 'Male' of 'Female'");
        }
        if (name.length() == 0) {
            throw new InvalidPersonArgumentException("Name can't be an empty string");
        }

        this.name = name;
        this.age = age;
        this.gender = gender.equalsIgnoreCase("Male")? "Male" : "Female";
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
    public static double[] averageAgePerGender(List<Person> persons) {
        double[] averageAge = new double[2];
        int maleCount = 0, femaleCount = 0;
        double totalMaleAge = 0.0, totalFemaleAge = 0.0;
        for (Person p : persons) {
            if (p.getGender().equals("Male")) {
                maleCount++;
                totalMaleAge += p.getAge();
            } else {
                femaleCount++;
                totalFemaleAge += p.getAge();
            }
        }
        averageAge[0] = maleCount > 0? Math.round(totalMaleAge/maleCount*100.0)/100.0 : 0.0;
        averageAge[1] = femaleCount > 0? Math.round(totalFemaleAge/femaleCount*100.0)/100.0 : 0.0;

        return averageAge;
    }
}
