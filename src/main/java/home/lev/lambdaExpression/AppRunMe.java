package home.lev.lambdaExpression;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class AppRunMe {
    ArrayList<Person> persons = new ArrayList<>();

    private void addPersons(){
        for (int i = 0; i < 10; i++) {
            persons.add(new Person(10+i, Person.Gender.MALE,"Petka-"+i));
        }
        for (int i = 0; i < 10; i++) {
            persons.add(new Person(10*i, Person.Gender.FEMALE,"Masha-"+i));
        }
    }
    private void printAll(){
        for (Person person : persons) {
            System.out.println(person);
        }
    }
    private void printV1(){
        System.out.println("--------Print V1--------");
        for (Person person : persons) {
            if(person.getAge()<20)
                System.out.println(person);
        }
    }
    private void printwithPredivcate(Predicate<Person> predicate){
        for (Person person : persons) {
            if(predicate.test(person))
                System.out.println(person);
        }
    }
    private void printWithPredicateAndConsumes(Predicate<Person>predicate,Consumer<Person>consumer){
        for (Person person : persons) {
            if (predicate.test(person))
                consumer.accept(person);
        }
    }

    private void printWithPredicateAndConsumesFunction(

            Predicate<Person>predicate,
            Function<Person,String> name,
            Consumer<String>consumer
            ){
        System.out.println("--------printWithPredicateAndConsumesFunction------------");
        for (Person person : persons) {
            if(predicate.test(person))
                consumer.accept(name.apply(person));
        }
    }


    public static void main(String[] args) {
        AppRunMe appRunMe = new AppRunMe();
        appRunMe.addPersons();
//        appRunMe.printAll();
//        appRunMe.printV1();
        final int  i=15;

        appRunMe.printwithPredivcate((p) -> p.getAge() == i);
        appRunMe.printwithPredivcate((p) -> p.getAge() == i + 1);
        appRunMe.printwithPredivcate((p) -> p.getAge() == i + 4);

        appRunMe.printWithPredicateAndConsumes(
                (p) -> p.getGender() == Person.Gender.FEMALE,
                (p) -> {
                    System.out.println("---------CONSUMER---------");
                    System.out.println(p);
                }
        );
        appRunMe.printWithPredicateAndConsumesFunction(
                p -> p.getAge() > 21,
                p ->p.getName(),
                email -> System.out.println(email)
        );
    }
}
