package home.lev.lambdaExpression;

/**
 * Created by pc-users on 7/15/2015.
 */
public class Person {

    private int age;
    private String name;
    private Gender gender;

    public Person(int age, Gender gender, String name) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    @Override
    public String toString() {
        return "age="+age+" gender="+gender+" name="+name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public enum Gender{
        MALE,FEMALE,NONE;
    }

}
